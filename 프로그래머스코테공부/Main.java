import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {
    
    public int solution(int[] priorities, int location) {
        int answer = 0;

        // 큐에서 대기중인 프로세스 하나를 꺼내기
        // 큐에 대기중인 프로세스 중 우선순위가 
        // 더 높은 프로세스가 있다면 방금 꺼낸
        // 프로세스를 다시 큐에 넣기
        // 만약 그런 프로세스가 없다면 방금 꺼낸 프로세스 실행
        // 한 번 실행한 프로세스는 다시 큐에 넣지 않고 그대로 종료

        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

        for (int p : priorities) {
            pq.add(p);
        }

        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }

        return answer;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // 테스트 케이스 추가하면서 테스트 진행

        System.out.println(solution.solution(new int[]{2,1,3,2}, 2));
        
    }
}