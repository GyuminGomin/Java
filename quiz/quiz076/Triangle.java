package quiz.quiz076;

public class Triangle {
    static double area;
    static double b, h, p;
    public static void main(String[] args) {
        if (area == 0){
            b = 3;
            h = 4;
            p = 0.5;
        }
        area = p*b*h;
        System.out.println("Area is " + area);
    }
}
