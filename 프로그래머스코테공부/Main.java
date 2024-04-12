import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public String solution(String X, String Y) {
        String answer = "";

        Map<Character, Integer> countX = new HashMap<>();
        Map<Character, Integer> countY = new HashMap<>();

        // X 문자열의 각 문자의 등장 횟수 세기
        for (char c : X.toCharArray()) {
            // 존재하지 않으면 0을 반환 하는 메서드
            countX.put(c, countX.getOrDefault(c, 0) + 1);
        }

        // Y 문자열의 각 문자의 등장 횟수 세기
        for (char c : Y.toCharArray()) {
            countY.put(c, countX.getOrDefault(c, 0) + 1);
        }

        // 리스트에 저장할 형태로 사용
        List<Character> list = new ArrayList<>();

        // countX와 countY를 비교해 작은 개수에 맞춰 리스트에 저장
        for (Map.Entry<Character, Integer> entry : countX.entrySet()) {
            char key = entry.getKey();
            int countXVal = entry.getValue();
            int countYVal = countY.getOrDefault(key, 0);

            int minCount = Math.min(countXVal, countYVal);
            for (int i=0; i<minCount; i++) {
                list.add(key);
            }
        }

        if (list.isEmpty()) {
            // list 가 비어있다면
            answer = "-1";
            return answer;
        }

        if (list.stream().allMatch(num -> num == '0')) {
            // list가 0으로만 이루어져 있다면
            return "0";
        }

        // 마지막 list에 저장된 값을 answer에 저장하는데, 가장 큰 내림차순으로 정렬
        Collections.sort(list);
        Collections.reverse(list);

        StringBuilder sb = new StringBuilder();

        for (char c : list) {
            sb.append(c);
        }
        answer = sb.toString();
        return answer;
    }
}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // 테스트 케이스 추가하면서 테스트 진행

        System.out.println(solution.solution("100", "2345"));
        System.out.println(solution.solution("100", "203045"));
        System.out.println(solution.solution("100", "123450"));
    }
}