package quiz.quiz116;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        int b = 3;
        if (!(b >3)) System.out.println("Square");
        { System.out.println("Circle");}
        System.out.println("...");
        
        List<Character> a = new ArrayList<>();
        List<Character> a_1 = new ArrayList<>();
        a_1.add('b');
        a.add('c');
        a.add('c');
        a.add('c');
        a.add('c');
        a.removeAll(a_1);
        System.out.println(a);
    }
}
