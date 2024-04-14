class Solution {

    private long statistic(long count1, long count2) {

        // 반환 경우의 수
        double result = 1;

        // 전체 개수
        long totalCount = count1+count2;

        if (count2 == 0 || count1 == 0) {
            return (long)result;
        }

        // 2가 2개, 1가 4개 라고 가정하면 (8) 두번 반복 6C2
        if (count1 >= count2) {
            for (int i=0; i<count2; i++) {
                result *= (double)totalCount-i;
                result /= (double)count2-i;
            }
        } else {
            for (int i=0; i<count1; i++) {
                result *= (double)totalCount-i;
                result /= (double)count1-i;
            }
        }

        return (long)result;
    }

    public long solution(int n) {
        long answer = 0;

        // 칸의 수 n = 4
        // 1칸 또는 2칸만 뛸 수 있다.
        /*
         * [1, 1, 1, 1]
         * [1, 2, 1]
         * [2, 1, 1]
         * [1, 1, 2]
         * [2, 2]
         */
        // 방법 = 5
        // 1234567을 나눈 나머지를 리턴

        // n = 3
        /*
         * [1, 1, 1]
         * [2, 1]
         * [1, 2]
         */

        // 확률 문제인데..
        /*
         * 5C3 = 5*4*3 / 3!
         */

        long count1 = 0;
        long count2 = 0;

        for (long i=0; i<=n/2; i++) {
            count2 = i; // 0, 1개, 2개
            count1 = n - 2*i; // 5, 4, 1
            // 여기 확률을 적용해서
            // answer에 넣어준후
            answer += statistic(count1, count2);
            
        }

        answer %= 1234567;

        return answer;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // 테스트 케이스 추가하면서 테스트 진행

        System.out.println(solution.solution(500)); 
    }
}