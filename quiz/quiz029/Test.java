package quiz.quiz029;

import java.io.IOException;

class X{
    public void printFileContent() throws IOException{
        throw new IOException();
    }
}

class Test {
    public static void main(String[] args) {
        X xobj = new X();
        try {
            xobj.printFileContent();   
        } catch (Exception e) {
            
        }
    }
}
