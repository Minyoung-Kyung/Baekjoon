import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		int answer = 0;
		boolean isPrime;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer nToken = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(nToken.nextToken()); // 입력할 숫자의 개수
		int[] arr = new int[n];
		
		StringTokenizer token = new StringTokenizer(br.readLine());
		
		int i = 0;
		while (token.hasMoreTokens()) {
			arr[i] = Integer.parseInt(token.nextToken());
			i++;
		}
        
		for (int j = 0; j < arr.length; j++) {
			isPrime = true; // isPrime은 j가 증가할 때마다 true로 초기화

			if (arr[j] >= 2) {
				if (isPrime(arr[j])) answer++; // arr[j]가 소수인 경우 answer 증가
			}
		}
		
		System.out.println(answer);
	} // end main
	
	public static boolean isPrime(int n) {
		for (int k = 2; k <= (int) Math.sqrt(n); k++) {
			if (n % k == 0) {
				return false;
			}
		}
		
		return true;
	} // end isPrime
}
