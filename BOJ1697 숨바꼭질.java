import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static int N, K;
    public static PriorityQueue<Position> queue = new PriorityQueue<>();
    public static int[] isVisited = {};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer nmToken = new StringTokenizer(br.readLine());

        N = Integer.parseInt(nmToken.nextToken()); // 수빈이가 있는 위치
        K = Integer.parseInt(nmToken.nextToken()); // 동생이 있는 위치

        isVisited = new int[100001];

        int answer = bfs();

        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }

    // bfs function
    public static int bfs() {
        queue.add(new Position(N, 0));
        isVisited[N] = 1;

        while (!queue.isEmpty()) {
            Position pos = queue.poll();
            int position = pos.position;
            int time = pos.time;

            isVisited[position] = 1; // 방문 처리

            if (position == K) {
                return time;
            }

            time++;

            if (isValid(position-1)) {
                queue.add(new Position(position-1, time));
            }

            if (isValid(position+1)) {
                queue.add(new Position(position+1, time));
            }

            if (isValid(position*2)) {
                queue.add(new Position(position*2, time));
            }
        }

        return 0;
    }

    public static boolean isValid(int n) {
        if (n < 0 || n > 100000 || isVisited[n] == 1)
            return false;
        return true;
    }

    public static class Position implements Comparable<Position> {
        int position, time;

        public Position(int position, int time) {
            this.position = position;
            this.time = time;
        }

        @Override
        public int compareTo(Main.Position o) { // 우선순위 기준
            return this.time - o.time;
        }
    }
}
