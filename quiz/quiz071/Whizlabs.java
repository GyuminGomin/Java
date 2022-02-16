package quiz.quiz071;

import java.time.LocalDate;
import java.time.Period;

public class Whizlabs {
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2015,3,26);
        Period p = Period.ofYears(1);
        System.out.println(date.plus(p));
    }
}
