package day2;

import java.io.FileReader;
import java.io.Reader;

public class ReaderTest {
    public static void main(String[] args) throws Exception{
        Reader reader = new FileReader("C:\\test\\day2\\test.txt");
        int readData; // 변환된 파일은 4바이트짜리 인트이므로
        while((readData=reader.read()) != -1){
            char charData = (char)readData; // 읽어온 데이터 값을 char로 변환해서 출력해준다.
            System.out.println(charData);
        }
    }
}
