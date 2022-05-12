import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) throws Exception {
		final int INF = 1000000000;
		int a, b, c;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 회원의 수
		
		int[][] floyd = new int[n+1][n+1];
		
		// 비정상값인 경우를 거르기 위해 INF로 초기화
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i == j) floyd[i][j] = 0;
				else floyd[i][j] = INF;
			}
		}
		
		while(true) {
			StringTokenizer token = new StringTokenizer(br.readLine());
      
			a = Integer.parseInt(token.nextToken());
			b = Integer.parseInt(token.nextToken());

      			if (a == -1 && b == -1) { // 마지막 줄에서 -1을 만나면 break
				break;
			} else { // a와 b는 친구
				floyd[a][b] = floyd[b][a] = 1;
			}
		}

    		// 어떤 두 회원이 친구사이이면서 동시에 친구의 친구사이이면, 이 두사람은 친구사이
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				for (int k = 1; k <= n; k++) {
					floyd[j][k] = floyd[j][k] < floyd[j][i] + floyd[i][k] ? floyd[j][k] : floyd[j][i] + floyd[i][k];
				}
			}
		}
		
		int[] result = new int[n+1]; // 각 후보 별 최대 점수를 저장할 배열
		
		for (int i = 1; i <= n; i++) {
			result[i] = Arrays.stream(floyd[i]).max().orElse(-1); // Stream API
		} // end for, result = {3, 2, 2, 2, 3}

		// 첫째 줄에는 회장 후보의 점수와 후보의 수를 출력
		// 둘째 줄에는 회장 후보를 오름차순으로 모두 출력
		int minResult = result[1];
		for (int i = 1; i <= n; i++) {
			if (minResult > result[i]) {
				minResult = result[i]; // 2
			}
		}
		
		ArrayList<Integer> list = new ArrayList<>(); // 회장 후보의 index 번호를 담을 ArrayList
		
		for (int i = 1; i <= n; i++) {
			if (minResult == result[i]) { // 오름차순으로 list에 저장
				list.add(i); // list : [2, 3, 4]
			}
		}
		
		System.out.println(minResult + " " + list.size()); // 회장 후보의 점수와 수 출력, 2 3
		
		String str = list.stream().map(String::valueOf).collect(Collectors.joining(" ")); // Stream API
		System.out.println(str); // 2 3 4
	}
}
