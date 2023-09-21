package Solution;

public class quiz2 {
    public static void main(String[] args) {
        Short s1 = 200;
        Integer s2 = 400;
        Long s3 = (long) s1 + s2;
        // String s4 = (String) (s3*s2); long에서 String으로 캐스팅 불가 (상속관계가 아니므로)
        // System.out.println("Sum is " + s4);
    }
}
