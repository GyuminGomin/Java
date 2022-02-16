package day1;

public class OpTest {
    public static void main(String[] args) {
        int i = 1;
        i++;
        System.out.println(i);
        int num1 = 10, num2 = 10;
        int a,b;
        a = ++num1;
        System.out.println(a+ " " + num1);
        b = num2++;
        System.out.println(b+ " " + num2);
        byte b1 = 10, b2 = 20;
        // byte c = b1 + b2; // 타입에러가 뜸, byte + byte 는 int로 자동 형변환 됨
        // 이항 연산자의 특징 : 연산을 수행하기 전에 피연산자의 타입을 일치시킨다. -> (byte, char, short) --> (int)

        System.out.println("0.1d == 0.1f의 결과: " + (0.1d == 0.1f));
        System.out.println("(float)0.1d == 0.1f의 결과: " + ((float)0.1d == 0.1f)); // 비교하려면 double을 float으로 변환해야한다. 무조건

    }
}
