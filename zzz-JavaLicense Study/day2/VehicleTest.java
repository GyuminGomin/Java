package day2;

public class VehicleTest {
    public static void main(String[] args) {
        // Car c1 = new Car();
        // System.out.println(c1.count);
        // c1.openDoor();
        Vehicle v1 = new Car();
        System.out.println(v1.count); // 인스턴스 변수는 참조변수 타입, 메서드는 만드는 쪽(객체)을 따라간다. 
        v1.openDoor(); // 메서드는 자식쪽
        Car c3 = (Car)v1; // 참조변수만 부모쪽에 넣었다가 자기형태로 캐스팅하는 것
        System.out.println(c3.count);
        c3.openDoor();
        // Car c2 = (Car)new Vehicle(); // 캐스팅하면 가능 그러나 ClassCastException이 생김
        // System.out.println(c2.count);
        // c2.openDoor();
    }
}
