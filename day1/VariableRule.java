package day1;

public class VariableRule {
    public static void main(String[] args) {
        int x = 20;
        // int i = 123.456; // 실수는 무조건 정수보다 큰 자료형
        // int i2 = 20l;
        long l1 = 20L;
        float f = 123.456f;
        double d = 123.456d;
        // int x1 = 2147483649;
        char ch1 = 'A';
        // char ch2 = 'ABC';
        String str = "ABC def";
        // int 1_a = 3;
        byte b = 127;
        b += 1;
        System.out.println(b); // 128이 나와야하지만, -128이 나옴 overflow현상
    }
}
