package day2;

public class Car extends Vehicle{
    int count = 5;

    @Override
    void openDoor() {
        System.out.println("문열리미 서브 호출딨다.");
    }
    
}
