package quiz.quiz120;

public class CharToStr {
    public static void main(String[] args) {
        String str1 = "Java";
        char str2[] = { 'J', 'a', 'v', 'a'};
        String str3 = null; // null 값도 아스키 코드 값 0 이 존재하므로 있는 것으로 인식
        for (char c : str2){
            str3 += c;
        }
        if (str1.equals(str3)){
            System.out.println("Successful");
        }
        else System.out.println("Unsuccessful");
    }
}
