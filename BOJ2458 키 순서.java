import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            final int INF = 99999;
            int A, B;
			
            int n, m = 0;
            n = sc.nextInt();
            m = sc.nextInt();
			
            int answer = 0;
            int[][] floyd = new int[n+1][n+1];
			
            // 비정상값인 경우를 거르기 위해 99999로 초기화
            for (int i = 1; i <= n; i++) {
                Arrays.fill(floyd[i], INF);
            }
			
            for (int i = 0; i < m; i++) {
                A = sc.nextInt();
                B = sc.nextInt();

                floyd[A][B] = 1; // 단방향 그래프, A번 학생이 B번 학생보다 키가 작다.
            }
			
            for (int i = 1; i <= n; i++) { // 경유지
                for (int j = 1; j <= n; j++) { // 출발지
                    for (int k = 1; k <= n; k++) { // 목적지
                      // 최솟값을 floyd[j][k]에 저장
                        floyd[j][k] = floyd[j][k] <= floyd[j][i] + floyd[i][k] ? floyd[j][k] : floyd[j][i] + floyd[i][k];
                    }
                }
            }
			
            for (int i = 1; i <= n; i++) {
                int cnt = 0;

                for (int j = 1; j <= n; j++) {
                    if (floyd[i][j] != INF || floyd[j][i] != INF) cnt++;
                }

                // 자신을 제외한 나머지 학생들과 모두 연결된 학생 수를 count
                // 예제 입력 1의 경우 4번 학생만 자신의 키가 몇 번째인지 알 수 있다.
                if (cnt == n - 1) answer++;
            }
			
            // 자신의 키가 몇 번째인지 알 수 있는 학생이 모두 몇 명인지 출력
            System.out.println(answer);
	}
    }
}
