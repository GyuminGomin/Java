package quiz.quiz064;

import java.util.ArrayList;
import java.util.List;

public class Whizlabs {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(21);
        list.add(13);
        list.add(30);
        list.add(11);
        list.removeIf(e -> e%2 != 0); // 조건을 만족하는 것만 삭제 해주는 함수
        System.out.println(list);
    }
}
