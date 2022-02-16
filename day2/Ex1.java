package day2;

import java.io.IOException;

class Ex2{
    public void Read() throws IOException{ // 이것을 호출하는 애에게 넘겨주겠다.
        // System.in.read();
        throw new IOException(); // 예외를 생성해서 던져주기
    }
}

public class Ex1 {
    public static void main(String[] args) {
        Ex2 ex = new Ex2();
        try{
            ex.Read();
        } catch(IOException e){
            e.printStackTrace();
        } System.out.println("확인");
    }
}
