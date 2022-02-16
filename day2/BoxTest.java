package day2;

public class BoxTest {
    public static void main(String[] args) {
        Box1 b = new Box1();
        b.setObj("obj");
        String s = (String)b.getObj();
        System.out.println(s);

        Box2<String> b2 = new Box2<String>(); // 뭘 넣고싶다'를 정할 수 있다. 뒤에 String을 빼도 됨
        
        b2.setT("t");
        // b2.setT(111); // 숫자를 넣으면 오류가 생긴다.
        String i = b2.getT();
        System.out.println(i); // 뺄때도 캐스팅 할 필요가 없어진다!!! 제네릭으로 설정을 미리 해주었기 때문에
    }
}
