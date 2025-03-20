import java.util.Arrays;

class Solution {

    public static String twentysix(long n) {
        if (n%26 == 1) return "a";
        else if (n%26 == 2) return "b";
        else if (n%26 == 3) return "c";
        else if (n%26 == 4) return "d";
        else if (n%26 == 5) return "e";
        else if (n%26 == 6) return "f";
        else if (n%26 == 7) return "g";
        else if (n%26 == 8) return "h";
        else if (n%26 == 9) return "i";
        else if (n%26 == 10) return "j";
        else if (n%26 == 11) return "k";
        else if (n%26 == 12) return "l";
        else if (n%26 == 13) return "m";
        else if (n%26 == 14) return "n";
        else if (n%26 == 15) return "o";
        else if (n%26 == 16) return "p";
        else if (n%26 == 17) return "q";
        else if (n%26 == 18) return "r";
        else if (n%26 == 19) return "s";
        else if (n%26 == 20) return "t";
        else if (n%26 == 21) return "u";
        else if (n%26 == 22) return "v";
        else if (n%26 == 23) return "w";
        else if (n%26 == 24) return "x";
        else if (n%26 == 25) return "y";
        else return "z";
    }

    public static int twentysixTrans(String n) {
        if (n.equals("a")) return 1;
        else if (n.equals("b")) return 2;
        else if (n.equals("c")) return 3;
        else if (n.equals("d")) return 4;
        else if (n.equals("e")) return 5;
        else if (n.equals("f")) return 6;
        else if (n.equals("g")) return 7;
        else if (n.equals("h")) return 8;
        else if (n.equals("i")) return 9;
        else if (n.equals("j")) return 10;
        else if (n.equals("k")) return 11;
        else if (n.equals("l")) return 12;
        else if (n.equals("m")) return 13;
        else if (n.equals("n")) return 14;
        else if (n.equals("o")) return 15;
        else if (n.equals("p")) return 16;
        else if (n.equals("q")) return 17;
        else if (n.equals("r")) return 18;
        else if (n.equals("s")) return 19;
        else if (n.equals("t")) return 20;
        else if (n.equals("u")) return 21;
        else if (n.equals("v")) return 22;
        else if (n.equals("w")) return 23;
        else if (n.equals("x")) return 24;
        else if (n.equals("y")) return 25;
        else return 26;
    }

    public static String strong(long n, StringBuilder result) {
        long doCalcValue = n / 26; // 몫
        long free = n % 26; // 나머지
        
        result.insert(0, twentysix(free));
        if (doCalcValue < 26) {
            if (n < 26) {
                return result.toString();
            }
            result.insert(0, twentysix(doCalcValue));
            return result.toString();
        }

        return strong(doCalcValue, result);
    }
    public static long strong2(String ban) {
        long result = 0;
        String[] strArray = new String[ban.length()];

        for (int i = 0; i<ban.length(); i++) {
            strArray[i] = String.valueOf(ban.charAt(i));
        }

        int paralx = 0;
        for (int i = strArray.length-1; i >= 0; i--) {
            long val = twentysixTrans(strArray[paralx++]);
            long val2 =  val * (long)Math.pow(26, i);

            result += val2;
        }
        return result;
    }

    public static String sample(long n, String[] bans) {

        String sb = strong(n, new StringBuilder());
        int length = sb.length();
        // n / 26 , n % 26
        int banCount = 0;
        for (String ban : bans) {
            if (ban.length() < length) {
                banCount++;
            } else if (ban.length() == length) {
                long val = strong2(ban);
                if (n > val) {
                    banCount++;
                } else {
                    break;
                }
            }
        }

        return strong(n + banCount, new StringBuilder());
    }

    public String solution(long n, String[] bans) {
        
        Arrays.sort(bans);

        String answer = null;
        answer = sample(n, bans);

        return answer;
    }
}
public class Main {
    
    public static void main(String[] args) {
        Solution solution = new Solution();
        // 테스트 케이스 추가하면서 테스트 진행
        System.out.println(solution.solution(30, new String[]{"d", "e", "bb", "aa", "ae"}));

        System.out.println(solution.strong(30, new StringBuilder()));
    }
}

/*
어느 날, 전설 속에 전해 내려오는 비밀 주문서가 세상에 다시 모습을 드러냈습니다. 이 주문서에는 마법 세계에서 사용되는 모든 주문이 적혀 있는데, 각 주문은 알파벳 소문자 11글자 이하로 구성되어 있습니다. 주문서에는 실제로 마법적 효과를 지니지 않는 의미 없는 주문들 즉, 알파벳 소문자 11글자 이하로 쓸 수 있는 모든 문자열이 고대의 규칙에 따라 아래와 같이 정렬되어 있습니다.

글자 수가 적은 주문부터 먼저 기록된다.
글자 수가 같다면, 사전 순서대로 기록된다.
예를 들어, 주문서의 시작 부분은 다음과 같이 구성됩니다.

"a"→"b"→"c"→"d"→"e"→"f"→...→"z"
→"aa"→"ab"→...→"az"→"ba"→...→"by"→"bz"→"ca"→...→"zz"
→"aaa"→"aab"→...→"aaz"→"aba"→...→"azz"→"baa"→...→"zzz"
→"aaaa"→...→"aazz"→"abaa"→...→"czzz"→"daaa"→...→"zzzz"
→"aaaaa"→...
하지만 이 주문서에는 오래전 봉인된 저주받은 주문들이 숨겨져 있었고, 이를 악용하려는 자들을 막기 위해 마법사들이 몇몇 주문을 주문서에서 삭제했습니다. 당신은 삭제가 완료된 주문서에서 n번째 주문을 찾아내야 합니다.

예를 들어, 주문서에서 "d", "e", "bb", "aa", "ae" 5개의 주문을 지웠을 때, 주문서에서 30번째 주문을 찾으려고 합니다.

1~3번째 주문은 "a", "b", "c" 입니다.
"d"와 "e"는 삭제됐으므로 4~24번째 주문은 "f" ~ "z"입니다.
"aa"는 삭제됐으므로 25~27번째 주문은 "ab", "ac", "ad"입니다.
"ae"는 삭제됐으므로 28~30번째 주문은 "af", "ag", "ah"입니다.
따라서 30번째 주문은 "ah"가 됩니다. 삭제된 주문 중 “bb”와 같이 n번째 주문보다 뒤에 위치해 있어서 n번째 주문을 찾는 데 영향을 주지 않는 주문도 존재할 수 있습니다.

정수 n과 삭제된 주문들을 담은 1차원 문자열 배열 bans가 매개변수로 주어질 때, 삭제가 완료된 주문서의 n번째 주문을 return 하도록 solution 함수를 완성해 주세요.


제한사항
1 ≤ n ≤ 1015
1 ≤ bans의 길이 ≤ 300,000
bans의 원소는 알파벳 소문자로만 이루어진 길이가 1 이상 11 이하인 문자열입니다.
bans의 원소는 중복되지 않습니다.
입출력 예
n	bans	result
30	["d", "e", "bb", "aa", "ae"]	"ah"
7388	["gqk", "kdn", "jxj", "jxi", "fug", "jxg", "ewq", "len", "bhc"]	"jxk"

테스트 케이스 구성 안내
아래는 테스트 케이스 구성을 나타냅니다. 각 그룹 내의 테스트 케이스를 모두 통과하면 해당 그룹에 할당된 점수를 획득할 수 있습니다.

그룹	총점	추가 제한 사항
#1	15%	n ≤ 1,000, bans의 길이 ≤ 100
#2	15%	n ≤ 1,000,000
#3	70%	추가 제한 사항 없음
입출력 예 설명
입출력 예 #1

문제 예시와 같습니다.

입출력 예 #2

주어진 주문을 지운 후 주문서의 7,388 번째 주문은 "jxk"입니다.
따라서 "jxk"를 return 합니다.
 */