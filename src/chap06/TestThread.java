package chap06;

public class TestThread {
    public static void main(String[] args) {
        MyThread m = new MyThread();
        MyThread m2 = new MyThread();
        m.start();
        m2.start();
    }
}
