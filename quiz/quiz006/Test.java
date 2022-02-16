package quiz.quiz006;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Test {
    public static void main(String[] args) {
    LocalDate date1 = LocalDate.now(); // 현재 날짜하고 시간을 리턴
    LocalDate date2 = LocalDate.of(2014, 6, 20); //
    LocalDate date3 = LocalDate.parse("2014-06-20", DateTimeFormatter.ISO_DATE); // ISO.DATE는 날짜 , ISO.DATE.TIME 은 시간까지
    System.out.println("date1 = " +date1);
    System.out.println("date1 = " +date2);
    System.out.println("date1 = " +date3);
    }
}
