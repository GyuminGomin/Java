package quiz.quiz042;

import java.util.ArrayList;
import java.util.List;

class Alpha{
    public String dostuff(String msg){
        return msg;
    }
}

class Beta extends Alpha{
    public String dostuff(String msg){
        return msg.replace('a', 'e');
    }
}

class Gamma extends Beta{
    public String dostuff(String msg){
        return msg.substring(2);
    }
}

public class Test {
    public static void main(String[] args) {
        List<Alpha> strs = new ArrayList<Alpha>();
        strs.add(new Alpha());
        strs.add(new Beta());
        strs.add(new Gamma());
        for(Alpha t : strs){
            System.out.println(t.dostuff("Java"));
        }
    }
}
