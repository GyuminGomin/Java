package day1;

public class Casting {
    public static void main(String[] args) {
        float f = 1.6f;
        int i = (int)f;
        System.out.println(i);
        byte b = (byte)128; // (byte의 특이형)범위안에서는 자동형변환을 해준다. 그러나 넘어가면 불가능
        System.out.println(b);
        // int i1 = 12d; // 나머지는 큰 데이터값을 작은 데이터 값에 절대 넣을 수 없다.
        byte b1 = 65;
        char c1 = (char)65;
        System.out.println(c1);
        int i2 = 'A';
        System.out.println(i2);
    }
}
