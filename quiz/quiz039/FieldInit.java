package quiz.quiz039;

public class FieldInit {
    char c;
    boolean b;
    float f;
    void printAll(){
        System.out.println("c = "+ c);
        System.out.println("b = "+ b);
        System.out.println("f = "+ f);
    }
    public static void main(String[] args) {
        FieldInit f = new FieldInit(); f.printAll();
    }
}
