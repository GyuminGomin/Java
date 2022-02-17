package Solution;

import java.util.ArrayList;
import java.util.List;

public class quiz5 {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Robb");
        names.add("Bran");
        names.add("Rick");
        names.add("Bran");

        if (names.remove("Bran")){
            names.remove("Jon"); // 실행을 해보면 알겠지만, 리턴값이 False만 나올 뿐, 실행되어지지 않아서 컴파일 오류가 발생하지 않는다.
        }
        System.out.println(names);
        
    }
    
}
