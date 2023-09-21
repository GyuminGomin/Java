package day1;

public class Overflow {
    public static void main(String[] args) {
        byte a = 127;
        byte b = -128;
        /* a += 1;
        b -= 1;
        System.out.println(a);
        System.out.println(b);
         */
        a = (byte)(a+1);//a = a+1; // 이것은 인트형값을 더하는 거라고 해석되어서 안됨
    }
}
