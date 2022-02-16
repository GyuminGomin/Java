package quiz.quiz002;

public class Case {
    public static void main(String[] args) {
        String product = "Pen";
        String result = product.toLowerCase(); // toLowerCase()는 String값을 리턴한다. 그래서 리턴값을 넣어주지 않으면 아무런 의미가 없다.
        result = product.concat(" BOX".toLowerCase()); // concat()도 String값을 리턴
        System.out.println(result);
        System.out.println(product);
        System.out.println(product.substring(4,6));
    }
}
