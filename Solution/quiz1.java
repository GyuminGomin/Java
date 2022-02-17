package Solution;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class quiz1 {
    public static void main(String[] args) {
        String date = LocalDateTime.parse("2014-05-04T10:15:30").format(DateTimeFormatter.ISO_DATE_TIME);
        System.out.println(date);
    }
}
