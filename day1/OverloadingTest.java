package day1;

public class OverloadingTest {
    public static void main(String[] args) {
        OverloadingTest a = new OverloadingTest();
        System.out.println(a.add());
        System.out.println(a.add(1,2));
        System.out.println(a.add("3","5"));
        System.out.println(a.add(3.14f, 0.001592f));
        // System.out.println(a.add(3.5,7.9)); 이건 안됨
    }
    public int add(){
        return 0;
    }

    public int add(int i, int x){
        return i + x;
    }

    public int add(String i, String x){
        return Integer.parseInt(i)+Integer.parseInt(x);
    }

    public float add(float i, float x){
        return i + x;
    }
}
