class Solution {

    public int solution(int[] nums) {
        int answer = 0;
        int count = 0;

        // 3개 이상 50개 이하이므로 이렇게 돌려도 될듯..
        for (int i=0; i<nums.length; i++) {
            for (int j=i+1; j<nums.length; j++) {
                for (int k=j+1; k<nums.length; k++) {
                    count = nums[i] + nums[j] + nums[k];

                    // 소수 구하는 것
                    for (int l=2; l<=count/2; l++) {
                        if (count % l == 0) {
                            // 소수가 아님
                            break;
                        } else {
                            if (l == count/2) answer++;
                            continue;
                        }
                    }
                }
            }
        }

        return answer;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // 테스트 케이스 추가하면서 테스트 진행

        System.out.println(solution.solution(new int[]{1,2,7,6,4}));
        
    }
}