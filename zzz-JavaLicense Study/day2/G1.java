package day2;

import java.util.ArrayList;
import java.util.List;

public class G1 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("hello");
        // list.add(1);
        String str = (String)list.get(0);
        System.out.println(str);
        // int i = (int)list.get(1);
        // System.out.println(i);
    }
}
