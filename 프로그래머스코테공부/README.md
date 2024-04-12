``` java
// Stack/Queue -> 올바른 괄호

import java.util.Stack;

class Solution {
    boolean solution(String s) {
        boolean answer = true;

        Stack stack = new Stack<>();

        if (s.endsWith("(") || s.startsWith(")")) {
            answer = false;
            return answer;
        }

        // Stack에 
        for (char s1 : s.toCharArray()) {
            if (s1 == '(') {
                stack.push(s1);
            } else {
                if (!stack.isEmpty()) {
                    stack.pop();
                    continue;
                } else {
                    answer = false;
                    break;
                }
            }
        }
        if (!stack.isEmpty()) {
            answer = false;
        }

        return answer;
    }
}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // 테스트 케이스 추가하면서 테스트 진행
        System.out.println(solution.solution("(())"));
        System.out.println(solution.solution(")"));
        
    }
}

```

``` java
// 2017 팁스타운 -> 예상 대진표

class Solution {
    public int solution(int n, int a, int b) {
        
        // 전체 라운드 구하기
        int round = (int)Math.sqrt(n);
        // 0부터 시작으로 변경
        int boxA = a-1;
        int boxB = b-1;
        // 1라운드 부터 시작
        int result = 1;
        
        for (int i=0; i<round; i++) {
            // 2를 나눠서 값이 같으면, 현재 라운드에서 승부 보는 것
            if (boxA/2 == boxB/2) {
                return result;
            } else {
                // 다음라운드 승부를 볼 예정이므로 1라운드 더해 줌
                result += 1;
                boxA = boxA/2;
                boxB = boxB/2;
            }
        }
        return result;
    }
}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // 테스트 케이스 추가하면서 테스트 진행
    }
}

```

``` java
// 연습문제 -> 최고의 집합

class Solution {
    public int[] solution(int n, int s) {
        int[] answer = {};

        // 최대의 값을 반환하기 위해서는 한군데 쏠린 것보다
        // 중앙에 잇는 값들이 모인 게 가장 크다
        // ex. n = 3, s = 8
        // (1,1,6) (1,2,5), (1,3,4), (2,2,4), (2,3,3) -> 2,3,3이 가장 큼
        // ex. n = 4, s = 10
        // (1,1,1,7) (1,1,2,6) (1,1,3,5) (1,1,4,4) (1,2,2,5) (1,2,3,4) (2,2,2,4) (2,2,3,3) -> 2,2,3,3이 가장 큼
        
        // ex. n = 3, s = 9
        // (1,1,7) (1,2,6) (1,3,5) (1,4,4) (2,2,5) (2,3,4) (3,3,3)

        // 만약 s가 n으로 나누어 떨어지면, (s/n, s/n, s/n)이 가장 큰 값
        // 그렇지 않을 때, 규칙성을 찾아본다면,
        // 나머지 개수가 s/n + 1, s/n + 1이고 (n - 나머지 개수)가 s/n 이다.
        // 그리고 최대값을 찾을 수 없는 경우는
        // n이 s보다 큰 경우이다. 따라서 나타내면

        if (n > s) {
            answer = new int[]{-1};
            return answer;
        } else {
            if (s % n == 0) {
                answer = new int[n];
                for (int i=0; i<n; i++) {
                    answer[i] = s/n;
                }
                return answer;
            } else {
                answer = new int[n];
                for (int i=0; i<(n-s%n); i++) {
                    answer[i] = s/n;
                }
                for (int i=n-s%n; i<n; i++) {
                    answer[i] = s/n+1;
                }
                return answer;
            }
        }
    }
}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // 테스트 케이스 추가하면서 테스트 진행
    }
}

```

``` java
// 연습문제 -> 숫자 짝꿍



```