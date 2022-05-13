import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static int n, m;
    public static int[][] miro = {};
    public static int[][] isVisited = {};

    // 상하좌우로 이동하면서 벽이 있는지를 탐색해야하므로 탐색을 위한 dx, dy 정의
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static PriorityQueue<Point> pq = new PriorityQueue<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer nmToken = new StringTokenizer(br.readLine());

        m = Integer.parseInt(nmToken.nextToken()); // 미로의 크기를 나타내는 가로 크기 (열)
        n = Integer.parseInt(nmToken.nextToken()); // 미로의 크기를 나타내는 세로 크기 (행)

        miro = new int[n][m];
        isVisited = new int[n][m];

        // 알고스팟과 관련된 입력값 저장
        // 0은 빈 방을 의미하고, 1은 벽을 의미
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                miro[i][j] = str.charAt(j) - '0'; // 문자열을 숫자열로 바꿔주기 위해 48을 뺀다. ('0'의 아스키코드는 48)
            }
        }

        int answer = bfs();

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    // bfs function
    public static int bfs() {
        pq.offer(new Point(0, 0, 0));
        isVisited[0][0] = 1; // 방문 처리

        while (!pq.isEmpty()) {
            Point p = pq.poll();

            if (p.x == n-1 && p.y == m-1) {
                return p.count;
            }

            for (int i = 0; i < 4; i++){
                int nX = p.x + dx[i];
                int nY = p.y + dy[i];

                if (nX >= 0 && nY >= 0 && nX < n && nY < m && isVisited[nX][nY] == 0) {
                    isVisited[nX][nY] = 1; // 방문 처리

                    if (miro[nX][nY] == 1) { // 방문한 Point가 벽인 경우
                        pq.offer(new Point(nX, nY, p.count+1)); // 벽 부수기
                    }

                    if (miro[nX][nY] == 0) { // 방문한 Point가 빈 방인 경우
                        pq.offer(new Point(nX, nY, p.count));
                    }
                }
            }
        }

        return 0;
    }

    // Point class
    public static class Point implements Comparable<Point> {
        int x, y, count;

        public Point(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }

        @Override
        public int compareTo(Main.Point o) { // 우선순위 기준
            return this.count - o.count;
        }
    }
}
