package day1;

public class Test1 {
    boolean c; // boolean 1c 처럼 변수 처음에 숫자가 나오면 안됨
    int a;
    float b = 1_000_000_000.0f; // java7 부터 나왔다. (무조건)숫자사이에 _ 가능 .0을 붙이면 f를 무조건 붙여야 한다.
    public static void main(String[] args) {
        Test1 t = new Test1();
        // int aaa; // 메소드 안에 있는 지역변수는 반드시 초기값을 넣어줘야함

        System.out.println(t.a);
        System.out.println(t.b);
        System.out.println(t.c); // 부분주석은 shift alt a
        // System.out.println(aaa); // 지역변수 초기값을 안 넣으면 호출하는데에서 오류가 발생한다.
        
    }
}
