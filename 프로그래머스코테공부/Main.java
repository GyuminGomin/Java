import java.util.*;
import java.util.stream.Collectors;

class Solution {

    public int solution(int[] scoville, int k) {
        int answer = 0;

        Queue<Integer> minHeap = new PriorityQueue<>();

        minHeap.addAll(Arrays.stream(scoville).boxed().collect(Collectors.toList()));

        while (!minHeap.isEmpty()) {
            int val = minHeap.poll();
            int curRemainCount = minHeap.size();
            if (val < k) {
                if (curRemainCount == 0) {
                    answer = -1;
                    break;
                }
                minHeap.add(minHeap.poll()*2 + val);
                answer++;
            } else {
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

        System.out.println(solution.solution(new int[]{1,2,3,9,10,12}, 7));
    }
}