import java.util.*;

class Solution {

    // result의 최대값을 구해야 함
    private static int result = 0;

    private static void dfs(int[][] dungeons, boolean[] isWent , int k, int res) {

        // 깊이 마다 한번 던전리스트를 반복해서 방문했는지 여부 체크
        for (int i=0; i<isWent.length; i++) {
            if (!isWent[i] && dungeons[i][0] <= k) {
                isWent[i] = true;
                dfs(dungeons, isWent, k - dungeons[i][1], res + 1);
                isWent[i] = false;
            }
        }

        // 모든 던전을 확인했으면 현재 결과 갱신
        if (result < res) result = res;
    }

    public int solution(int k, int[][] dungeons) {
        int answer = -1;

        // 최소 필요 피로도
        // 소모 피로도
        boolean[] isWent = new boolean[dungeons.length];
        Arrays.fill(isWent, false);

        // depth는 1부터 시작
        dfs(dungeons,isWent,k,0);
        
        answer = result;
        return answer;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // 테스트 케이스 추가하면서 테스트 진행

        System.out.println(solution.solution(80, new int[][]{{80,20},{50,40},{30,10}}));
    }
}