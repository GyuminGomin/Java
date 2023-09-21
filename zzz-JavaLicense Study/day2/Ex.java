package day2;


public class Ex {
    public static void main(String[] args) {
        int x = 10, y =0;
        // int array[] = new int[5];
        try {
            int result = x/y;
            // array[5] = x;
            
        }catch (ArithmeticException e){
            System.out.println("0으로 나눌 수 없습니다.");
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("배열의 인덱스 범위를 넘어갑니다.");
        }catch (Exception e) {
            System.out.println(e);
        } System.out.println("종료!"); // final은 생략가능
    }
}
