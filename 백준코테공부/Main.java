import java.io.*;
import java.util.*;

public class Main {

    // 다음은 피보나치 수열 for로 구현한 함수이며,
    static int fibonacciNum1(int number) {
        int a = 0;
        int b = 1;
        int fib = 0;

        if (number == 0) {
            return a;
        } else if (number == 1) {
            return b;
        }
        for (int i=2; i<=number; i++) {
            fib = a + b;
            a = b;
            b = fib;
        }
        return fib;
    }

    // 예시로 4를 넣으면
    /*
     * 4 -> 3(*) 2(*)
     * 3 -> 2(*) 1
     * 2 -> 1 0
     * 2 -> 1 0
     * 즉 1은 3번 0은 2번이 나온다.
     * 
     * 5 -> 4(1은 3번 0은 2번) 3(1은 2번 0은 1번)
     * 즉 1은 5번 0은 3번이 나온다.
     * 
     * 6 -> 5(1은 5번 0은 3번) 4(1은 3번 0은 2번)
     * 즉 1은 8번 0은 5번이 나온다.
     * 
     * 7 -> 6(1:8,0:5) 5(1:5,0:3)
     * 즉 1은 13번 0은 8번이 나오는데,
     * 
     * 즉 나오는 개수도 피보나치 수열처럼 커진다.
     * 
     * 1은 기존 피보나치 수열 1 1 2 3 5 ...
     * 0은 기존보다 한칸 아래 수열 1 1 1 2 3 5 ...
     * 
     * 따라서 for로 구한 피보나치 수열을 이용해서 적용
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String n = br.readLine();
        int num = Integer.parseInt(n);
        StringTokenizer st = null;

        for (int i=0; i < num; i++) {
            st = new StringTokenizer(br.readLine());

            int number = Integer.parseInt(st.nextToken());

            int result0 = 0;
            int result1 = 0;

            // 입력되는 값이 0이면, 1,0은 고정, 1이면 0,1은 고정
            if (number == 0) {
                result0 = 1;
                result1 = 0;
            } else if (number == 1) {
                result0 = 0;
                result1 = 1;
            } else {
                // 즉 0이 나오는 개수는 기존 피보나치 수보다 -1의 값 이고
                result0 = fibonacciNum1(number-1);
                // 1이 나오는 개수는 기존 피보나치 number의 값
                result1 = fibonacciNum1(number);
            }
            bw.write(String.valueOf(result0)+" "+ String.valueOf(result1)+"\n");
        }

        bw.flush();
        bw.close();
    }
}