package Solution;

class Vehicle {
    String type = "4W";
    int maxSpeed = 100;

    Vehicle(){} // 내가 넣은 것

    Vehicle(String type, int maxSpeed){
        this.type = type;
        this.maxSpeed = maxSpeed;
    }
}

class Car extends Vehicle {
    String trans;

    Car(String trans){ // super class의 연결되는 생성자가 없어서 오류
        super(); // 내가 넣은 것
        this.trans = trans;
    }

    Car(String type, int maxSpeed, String trans){
        super(type, maxSpeed);
        // this(trans);
    }
}

public class quiz3 {
    
}
