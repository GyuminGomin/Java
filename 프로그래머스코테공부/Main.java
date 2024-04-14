import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class Solution {
    public int[] solution(int k, int[] score) {
        int[] answer = {};
        
        // 10, 100, 20, 150, 1, 100, 200
        // k = 3
        /*
         * 10 100 100 150 150 150 200
         *     10  20 100 100 100 150
         *         10  20  20 100 100
         */        
        // 10, 10, 10, 20, 20, 100, 100

        // 큐는 아니다.
        
        List<Integer> list = new LinkedList<>();
        // 채워져 있는지 확인 변수
        // 하나씩 채워 질 때마다 -1;
        int box = k;

        answer = new int[score.length];
        for (int i=0; i<score.length; i++) {
            if (box != 0) {
                list.add(score[i]);
                // 크기 순서대로 저장되지는 않는다.
                box--;

                Collections.sort(list); // 오름 차순
                answer[i] = list.get(0);
            } else {
                // 이제 꽉 채워 지면
                // 리스트에 값을 추가하고
                // 다시 정렬 후
                // 가장 작은 크기를 비운다.
                list.add(score[i]);
                Collections.sort(list);
                list.remove(0);
                answer[i] = list.get(0);
            }
        }

        return answer;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // 테스트 케이스 추가하면서 테스트 진행
        System.out.println(Arrays.toString(solution.solution(3, new int[]{10, 100,20, 150, 1, 100, 200}))); 
    }
}