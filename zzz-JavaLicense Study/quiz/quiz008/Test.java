package quiz.quiz008;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Test {
    public static void main(String[] args) {
        String date = LocalDate.parse("2014-05-04").format(DateTimeFormatter.ISO_DATE); // ISO_DATE_TIME 하면 안됨
        System.out.println(date);
    }
}
