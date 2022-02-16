package day2;

public class Box2<T> { // 여러개 사용 가능 <T,M> T = kind, M = model
    private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
    
}
