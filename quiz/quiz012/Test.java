package quiz.quiz012;

import java.util.ArrayList;
import java.util.List;

class Patient{
    String name;
    public Patient(String name){
        this.name = name;
    }
}

public class Test {
    public static void main(String[] args) {
        List ps = new ArrayList();
        Patient p2 = new Patient("Mike");
        ps.add(p2);
        
        int f = ps.indexOf(p2); // 이 객체가 존재하면 0을 리턴,,, 존재하지 않으면 -1을 리턴

        if (f>=0){
            System.out.println("Mike Found");
        }
    }
}
