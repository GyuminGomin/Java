class Solution {
    
    public int solution(int n) {
        int answer = 0;

        // n을 이진수로 변환 시키자
        String binaryNumber = Integer.toBinaryString(n);

        while (true) {
            n += 1;
            String binaryNextNumber = Integer.toBinaryString(n);
            int count = 0;
            for (int i=0; i<binaryNumber.length(); i++) {
                if (binaryNumber.charAt(i) == '1') {
                    count++;
                }
            }
            for (int i=0; i<binaryNextNumber.length(); i++) {
                if (binaryNextNumber.charAt(i) == '1') {
                    count--;
                }
            }
            if (count == 0) {
                answer = n;
                break;
            }
        }

        return answer;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // 테스트 케이스 추가하면서 테스트 진행

        System.out.println(solution.solution(15));
        
    }
}