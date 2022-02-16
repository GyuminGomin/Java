package day1;
// 하나의 파일에서는 public 제어함수가 하나만 존재 가능
/* public  */class Card{
    String kind;
    int number;
    static int width = 200;
    static int height = 350;
}

class Box{
    int width, length, height;
    public int getVol(){
        int volume;
        volume = width*length*height;
        return volume;
    }
    // volume = 500; 오류 발생
}


public class CardTest {
    public static void main(String[] args) {
        System.out.println(Card.width + " : "+Card.height);
        Card.width = 100;
        Card.height = 200;
        System.out.println(Card.width + " : "+Card.height);
        Card c = new Card();
        Card d = new Card();
        c.kind = "Heart";
        c.number = 9;

        
    }
}
