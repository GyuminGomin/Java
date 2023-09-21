package quiz.quiz077;

class Star {
    public void doStuff(){
        System.out.println("Twinkling Star");
    }
}
interface Universe {
    public void doStuff();
}
class Sun extends Star implements Universe{
    public void doStuff(){
        System.out.println("Shining Sun");
    }
}

public class Bob {
    public static void main(String[] args) {
        Sun obj2 = new Sun();
        Star obj3 = obj2;
        ((Sun) obj3).doStuff();
        ((Star) obj2).doStuff();
        ((Universe) obj2).doStuff(); // 하나의 예시를 들어보자면, int a = 3; 변수 a를 캐스팅 시킨다고 생각하면 된다.
    }
}
