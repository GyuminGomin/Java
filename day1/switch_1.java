package day1;

public class switch_1 {
    public static void main(String[] args) {
        int score = 95;
        char grade;
        switch(score/10){
            case 10: // 값이 문자나 문자열이 올 수도 있다.
            case 9:
            grade ='A';
            break;
            case 8:
            grade ='B';
            break;
            case 7:
            grade ='C';
            break;
            case 6:
            grade ='D';
            break;
            default:
            grade ='F';
        }
        System.out.println("grade : "+grade);
    }
}
