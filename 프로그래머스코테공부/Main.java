import java.util.*;

class Solution {
    public int[] solution(int[] nodes, int[][] edges) {
        int[] answer = {};

        // nodes의 개수 1~40만
        // nodes의 원소 1 ~ 100만
        // 간선의 길이 1~ 100만

        // 정보 저장 맵
        Map<Integer, List<Integer>> edgesInfo = new HashMap<>();

        // 값 초기화
        for (int i=0; i<nodes.length; i++) {
            edgesInfo.put(nodes[i], new ArrayList<Integer>());
        }

        // 최대 100만 O(n)
        for (int i=0; i<edges.length; i++) {
            int tmp1 = edges[i][0];
            int tmp2 = edges[i][1];
            edgesInfo.get(tmp1).add(tmp2);
            edgesInfo.get(tmp2).add(tmp1);
        }

        // 메인로직 작성
        for (int i=0; i<nodes.length; i++) {
            int key = nodes[i];
            List<Integer> info_list = edgesInfo.get(nodes[i]);
            int edgesLength = info_list.size();



            if (key % 2 == 0) { // 짝수
                if (edgesLength % 2 == 1) { // 홀수
                    // 역홀짝

                } else { // 짝수
                    // 홀짝

                }
            }
            else { // 홀수
                if (edgesLength % 2 == 1) { // 홀수
                    // 홀짝

                } else { // 짝수
                    // 역홀짝

                }
            }
        }

        return answer;
    }

    private static void evenOrOdd(int key, List<Integer> list) { // 역홀짝
        if (key % 2 == 0) {
            
        }
        
    } 

    private static void sniffling() { // 홀짝

    }
}
public class Main {
    
    public static void main(String[] args) {
        Solution solution = new Solution();
        // 테스트 케이스 추가하면서 테스트 진행
    }
}