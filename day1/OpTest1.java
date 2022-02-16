package day1;

public class OpTest1 {
    public static void main(String[] args) {
        String s = "aa";
        String s1 = "aa"; // 한개만 만들고 다 같이 공유 메모리 낭비를 막기 위해
        String s2 = new String("aa"); // 새로운 객체를 생성해서 다름
        String s3 = new String("aa");
        /* if (s2 == s3){
            System.out.println("같음");
        }
        else{
            System.out.println("다름");
        } */
        if (s2.equals(s3)){
            System.out.println("같음");
        }
        else{
            System.out.println("다름");
        }
    }
}
