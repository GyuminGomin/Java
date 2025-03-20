import java.util.*;

class Solution {

    private static int resultN = Integer.MAX_VALUE; // 충분히 큰 값으로 초기화
    private static Map<String, Integer> memo = new HashMap<>(); // 메모이제이션용 Map

    private static void dps(int depth, int n, int m, int[][] info, int result) {
        String key = depth + "," + n + "," + m;  // 현재 상태를 문자열로 변환 (depth, n, m)
        if (memo.containsKey(key) && memo.get(key) <= result) {
            return; // 이미 더 좋은 결과가 있으므로 탐색 중단
        }
        memo.put(key, result); // 현재 결과 저장

        int[] currentInfo = info[depth];
        int thief_x = currentInfo[0];
        int thief_y = currentInfo[1];

        // n 감소 시도
        if (n > thief_x) {
            int n_tmp = n - thief_x;
            int result_tmp = result + thief_x;
            if (depth + 1 < info.length) {
                if (result_tmp < resultN) {
                    dps(depth + 1, n_tmp, m, info, result_tmp);
                }
            } else {
                resultN = Math.min(resultN, result_tmp);
            }
        }

        // m 감소 시도
        if (m > thief_y) {
            int m_tmp = m - thief_y;
            if (depth + 1 < info.length) {
                if (result < resultN) {
                    dps(depth + 1, n, m_tmp, info, result);
                }
            } else {
                resultN = Math.min(resultN, result);
            }
        }
    }

    public int solution(int[][] info, int n, int m) {
        resultN = Integer.MAX_VALUE;
        memo.clear(); // 새로운 입력마다 초기화

        dps(0, n, m, info, 0);

        return resultN == Integer.MAX_VALUE ? -1 : resultN;
    }
}
public class Main {
    
    public static void main(String[] args) {
        Solution solution = new Solution();
        // 테스트 케이스 추가하면서 테스트 진행
        System.out.println(solution.solution(new int[][]{{1,2},{2,3},{2,1}}, 4, 4)); 
    }
}