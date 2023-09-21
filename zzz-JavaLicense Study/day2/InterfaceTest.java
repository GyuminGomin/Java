package day2;

interface A{
    static final int A = 500; // static final은 생략가능
    void draw();
}

interface C{
    void check();
}

class B implements A,C{
    int A; // 여기서 만들어도 생성한 참조변수의 타입에 따라 다른 값이 나온다.
    @Override
    public void draw(){
        System.out.println("B");
    }
    @Override
    public void check() {
        System.out.println("C");
    }
}

public class InterfaceTest {
    public static void main(String[] args) {
        A a = new B();
        System.out.println(a.A);
        // a.A=3;
        a.draw();
    }
}
