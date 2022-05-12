import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		final int INF = 1000000000;
		int a, b, c;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 도시의 개수
		int m = Integer.parseInt(br.readLine()); // 버스의 개수
		
		int[][] floyd = new int[n+1][n+1];
		
		// 비정상값인 경우를 거르기 위해 INF로 초기화
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i == j) floyd[i][j] = 0;
				else floyd[i][j] = INF;
			}
		}
		
		for (int i = 0; i < m; i++) {
			StringTokenizer token = new StringTokenizer(br.readLine());
			
		    a = Integer.parseInt(token.nextToken()); // 버스의 시작 도시
		    b = Integer.parseInt(token.nextToken()); // 버스의 도착 도시
		    c = Integer.parseInt(token.nextToken()); // 한 번 타는데 필요한 최소 비용
		    
		    floyd[a][b] = Math.min(floyd[a][b], c);
		}
		
		for (int i = 1; i <= n; i++) { // 경유지
		    for (int j = 1; j <= n; j++) { // 출발지
		        for (int k = 1; k <= n; k++) { // 목적지
		            floyd[j][k] = Math.min(floyd[j][i] + floyd[i][k], floyd[j][k]); // 최단 경로 초기화 (최솟값을 저장)
		        }
		    }
		}
		
		for (int i = 1; i <= n; i++) {
			StringBuilder sb = new StringBuilder();
			
		    for (int j = 1; j <= n; j++) {
		    	if (floyd[i][j] >= INF) { // i에서 j로 갈 수 없는 경우에는 그 자리에 0을 출력
		    		floyd[i][j] = 0;
		    	}
		    	
		    	sb.append(floyd[i][j]).append(" ");
		    }
		    
		    String result = sb.toString().trim();
		    System.out.println(result);
		}
	}
}