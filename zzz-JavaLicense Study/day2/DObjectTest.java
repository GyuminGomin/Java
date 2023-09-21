package day2;

abstract class DObject{
    public DObject next;
    public DObject(){
        next = null;
    }
    abstract public void draw();
}
class Line extends DObject{
    public void draw(){
        System.out.println("Line");
    }
}
abstract class Rect extends DObject{}

public class DObjectTest {
    public static void main(String[] args) {
        // DObject d = new DObject();
        DObject d1 = new Line(); // 부모클래스 형태를 이렇게 작성하는 것은 가능
        // DObject d2 = new Rect();
        d1.draw();
    }
}
