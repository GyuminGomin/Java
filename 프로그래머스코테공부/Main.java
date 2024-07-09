import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

    public int solution(String str1, String str2) {
        int answer = 0;

        // 자카드 유사도
        // 97 ~ 122 -> 소문자
        // 65 ~ 90 -> 대문자

        char[] charList = str1.toCharArray();

        // 이제 2개씩 끊어서 새로운 리스트에 저장
        List<String> answerList1 = new ArrayList<>();
        for (int i=0; i<charList.length-1; i++) {
            if (i == charList.length-1) break;

            if ( ((charList[i] >= 97 && charList[i] <=122) || (charList[i] >= 65 && charList[i] <= 90))
            && ((charList[i+1] >= 97 && charList[i+1] <=122) || (charList[i+1] >= 65 && charList[i+1] <= 90)) ) {
                answerList1.add(Character.toString(charList[i]).toLowerCase() + Character.toString(charList[i+1]).toLowerCase());
            }
        }

        charList = str2.toCharArray();

        List<String> answerList2 = new ArrayList<>();

        for (int i=0; i<charList.length-1; i++) {
            if (i == charList.length-1) break;

            if ( ((charList[i] >= 97 && charList[i] <=122) || (charList[i] >= 65 && charList[i] <= 90))
            && ((charList[i+1] >= 97 && charList[i+1] <=122) || (charList[i+1] >= 65 && charList[i+1] <= 90)) ) {
                answerList2.add(Character.toString(charList[i]).toLowerCase() + Character.toString(charList[i+1]).toLowerCase());
            }
        }

        double result = 0;
        if (answerList1.size() == 0 && answerList2.size() == 0) result = 1;
        else {
            Map<String, Integer> map1 = new HashMap<>();
            Map<String, Integer> map2 = new HashMap<>();

            for (int i=0; i<answerList1.size(); i++) {
                map1.put(answerList1.get(i), map1.getOrDefault(answerList1.get(i), 0)+1);   
            }

            for (int i=0; i<answerList2.size(); i++) {
                map2.put(answerList2.get(i), map2.getOrDefault(answerList2.get(i), 0)+1);
            }
            
            int intersector = 0;
            // 교집합 개수 구하기
            for (String key : map1.keySet()) {
                if (map2.containsKey(key)) {
                    int eqval = map1.get(key);
                    int eqval2 = map2.get(key);

                    intersector += Math.min(eqval, eqval2);
                }
            }

            int union = 0;
            // 합집합 개수 구하기
            for (String key : map1.keySet()) {
                if (map2.containsKey(key)) {
                    int eqval = map1.get(key);
                    int eqval2 = map2.get(key);

                    union += Math.max(eqval, eqval2);
                } else {
                    union += map1.get(key);
                }
            }

            for (String key : map2.keySet()) {
                if (!map1.containsKey(key)) {
                    union += map2.get(key);
                }
            }

            // 교집합/합집합
            result = (double)intersector/union;
        }

        result *= 65536;
        answer = (int)Math.floor(result);

        return answer;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // 테스트 케이스 추가하면서 테스트 진행

        //System.out.println(solution.solution("FRANCE", "french"));
        //System.out.println(solution.solution("E=M*C^2", "e=m*c^2"));
        //System.out.println(solution.solution("handshake", "shake hands"));
        //System.out.println(solution.solution("aa1+aa2", "AAAA12"));
        System.out.println(solution.solution("AAbbaa_AAbb", "BBB"));
    }
}