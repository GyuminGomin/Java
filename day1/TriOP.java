package day1;

public class TriOP {
    public static void main(String[] args) {
        int x = -10;
        int y = 50;
        int absX = x>=0 ? x : -x;
        char grade = y>=90 ? 'A' : (y>=80 ? 'B' : 'C'); // 중첩이 가능하지만 가시성이 떨어지므로 괄호 넣어주자
        System.out.println(grade);
    }
}
