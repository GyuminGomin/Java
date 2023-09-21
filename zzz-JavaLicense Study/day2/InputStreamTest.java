package day2;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class InputStreamTest {
    public static void main(String[] args) throws IOException{
        InputStream is = new FileInputStream("C:\\test\\day2\\download.jpg");
        int readByteNo;
        byte[] readBytes = new byte[100]; // 파일을 100byte단위로 읽어오도록
        while((readByteNo = is.read(readBytes))!=-1){ 
            for(byte b:readBytes){
                System.out.println((char)b);
            }
        }
    }
}
