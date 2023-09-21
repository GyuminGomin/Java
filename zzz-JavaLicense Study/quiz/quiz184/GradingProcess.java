package quiz.quiz184;

class MarksOutOfBoundsException extends IndexOutOfBoundsException{}

public class GradingProcess {
    void verify(int marks) throws IndexOutOfBoundsException{
        if(marks > 100){
            throw new MarksOutOfBoundsException();
        }
        if(marks >50){
            System.out.println("Pass");
        } else { System.out.println("Fail");}
    }
    public static void main(String[] args) {
        int marks = Integer.parseInt(args[2]);
        try{
            new GradingProcess().verify(marks);
        } catch(Exception e){ System.out.println(e.getClass());}
    }
}

