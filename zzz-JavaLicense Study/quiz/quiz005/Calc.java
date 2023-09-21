package quiz.quiz005;

class Mid{
    public int findMid(int n1, int n2){
        return(n1 + n2) / 2;
    }
}

public class Calc extends Mid{
    public static void main(String[] args) {
        int n1 = 22, n2 = 2;
        // insert code here
        /* Calc c = new Calc();
        int n3 = c.findMid(n1, n2); */ // A
        Mid m = new Calc();
        int n3 = m.findMid(n1, n2); // D
        System.out.print(n3);
    }
}
