package quiz.quiz001;

public class Test {
    static int count = 0;
    int i = 0;
    
    public void changeCount() {
        while( i<5 ){
            i++;
            count++;
        }
    }
    public static void main(String[] args) {
        Test check1 = new Test();
        Test check2 = new Test();
        check1.changeCount(); // count = 5, i = 5
        check2.changeCount(); // count = 10, i = 5
        System.out.println(check1.count+" : "+check2.count);
        System.out.println(Test.count);
    }
}
