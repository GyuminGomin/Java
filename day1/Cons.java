package day1;

public class Cons {
    int i;
    int x;
    public Cons() {
        this(2,5);
        System.out.println("기본");
    }
    
    public Cons(int i) {
        this(i,4);
        System.out.println("i입력");
    }

    public Cons(int i, int x) {
        this.i = i;
        this.x = x;
        System.out.println("둘 다 입력");
    }
    public static void main(String[] args) {
        Cons c = new Cons();
        Cons c1 = new Cons(1);
        Cons c2 = new Cons(1,2);
    }
}
