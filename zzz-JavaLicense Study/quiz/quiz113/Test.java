package quiz.quiz113;

import java.util.ArrayList;

import org.omg.Messaging.SyncScopeHelper;

public class Test {
    public static void main(String[] args) {
        ArrayList myList = new ArrayList();
        String[] myArray;
        try {
            while(true){
                myList.add("My String");
            }
        } catch (RuntimeException re){
            System.out.println("Caught a RuntimeException");
        } catch (Exception e) {
            System.out.println("Caught a Exception");
        }
        System.out.println("Ready to use");
    } 
}
