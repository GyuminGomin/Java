package Solution;

public class quiz6 {
    public static void main(String[] args) {
        String[] arr = {"A", "B", "C", "D"};
        for(int i = 0; i <arr.length; i++){
            System.out.println(arr[i] + " "); // 주소값을 반환해주는 게 아니고 주소에 있는 값을 반환시켜준다.
            if (arr[i].equals("C")){
                continue;
            }
            System.out.println("Work done");
            break;
        }
    }
}
