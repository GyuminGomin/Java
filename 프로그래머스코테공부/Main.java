import java.util.*;

class Solution {

    public long solution(int[] weights) {
        long answer = 0;

        // 100, 180, 360, 100, 270
        for (int i=0; i<weights.length; i++) {
            for (int j=i+1; j<weights.length; j++) {
                int w1 = weights[i];
                int w2 = weights[j];
                if (w1*3 == w2*4 || w1*2 == w2*3 || w1 == w2*2
                || w1 == w2 || w1*3 == w2*2 || w1*2 == w2 || w1*4 == w2*3) {
                    answer++;
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

        System.out.println(solution.solution(new int[]{100,180,360,100,270}));
    }
}