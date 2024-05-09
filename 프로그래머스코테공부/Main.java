import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    
    public int solution(String[][] clothes) {
        int answer = 0;

        // 먼저 clothes의 사이즈에 맞게 (한개씩 선택하는 개수를 더해줌)
        answer += clothes.length;

        // 그 다음 Map으로 만들어 옷의 종류에 맞는 개수를 구함
        Map<String, Integer> map = new HashMap<>();

        for (int i=0; i<clothes.length; i++) {
            // 옷의 개수에 맞게 돌려서 종류를 map에다가 기입해야 하므로
            // 한 행에 2개의 값이 들어가므로 종류는 1 index에 존재
            map.put(clothes[i][1], map.getOrDefault(clothes[i][1], 0)+1);
        }
        // 같은 이름을 가진 의상은 없다고 하였으므로
        // 옷의 종류에 맞는 개수가 구해질 거고
        // values를 뽑아서 리스트에 저장하고

        List<Integer> clothArrays = new ArrayList<>();
        for (int counts : map.values()) {
            clothArrays.add(counts);
        }

        for (int i=2; i<=clothArrays.size(); i++) {
            // i는 몇 개를 뽑아 쓸 것인지 결정
            for (int j=0; j<clothArrays.size(); j++) {
                boolean flag = true;
                int multiplex = clothArrays.get(j);
                for (int k=j+1; k<clothArrays.size(); k++) {
                    // j부터 j+i개까지 곱할 것임
                    // 만약 i를 더한게 clothArrays.size를 넘는다면 break;
                    if (clothArrays.size() < j+i) {
                        flag = false;
                        break;
                    }
                    multiplex *= clothArrays.get(k);
                }
                if (flag) {
                    answer += multiplex;
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

        System.out.println(solution.solution(new String[][]{{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}, {"a", "headgear"}, {"b", "eyewear"}, {"c", "c"}, {"d", "c"}, {"e", "c"}}));
        
    }
}