import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		int a, b;
		int answer = 0;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer nmToken = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(nmToken.nextToken()); // 무게가 모두 다른 구슬의 개수 (홀수)
		int M = Integer.parseInt(nmToken.nextToken()); // 구슬 쌍의 수
		
		int[][] floyd = new int[N+1][N+1]; // 0으로 초기화
		
		for (int i = 0; i < M; i++) {
			StringTokenizer abToken = new StringTokenizer(br.readLine());
			
		    a = Integer.parseInt(abToken.nextToken());
		    b = Integer.parseInt(abToken.nextToken());
		    
		    floyd[a][b] = 1; // a가 b보다 무거우면 1
			floyd[b][a] = -1; // b가 a보다 가벼우면 -1
		}
		
		for (int i = 1; i <= N; i++) {
		    for (int j = 1; j <= N; j++) {
		        for (int k = 1; k <= N; k++) {
		            if (floyd[j][i] == 1 && floyd[i][k] == 1) { // a > b, b > c
		            	floyd[j][k] = 1; // a > c
		            }
		            
		            if (floyd[j][i] == -1 && floyd[i][k] == -1) { // a < b, b < c
		            	floyd[j][k] = -1; // a < c
		            }
		        }
		    }
		}
		
		for (int i = 1; i <= N; i++) {
			int win = 0;
			int lose = 0;
			int middle = (N + 1) / 2; // 중간 구슬의 번호
			
		    for (int j = 1; j <= N; j++) {
		    	if (floyd[i][j] == 1) win++;
		    	else if (floyd[i][j] == -1) lose++;
		    }
		    
		    if (win >= middle || lose >= middle) answer++;
		}
		
		System.out.println(answer);
    }
}