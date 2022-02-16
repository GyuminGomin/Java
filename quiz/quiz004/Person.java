package quiz.quiz004;

public class Person {
    String name;
    int age = 25;
    
    public Person(){} // 내가 넣은 값

    public Person(String name){
        this(); // line n1 --> 기본 생성자 안만듬
        setName(name);
    }
    public Person(String name, int age){
        this(name); // 내가 바꾼 값 // Person(name); // line n2 --> 생성자 호출은 this()를 사용함
        setAge(age);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String show() {
        return name + " " + age + " "; // + number;
    }
    public static void main(String[] args) {
        Person p1 = new Person("Jesse");
        Person p2 = new Person("Walter", 52);
        System.out.println(p1.show());
        System.out.println(p2.show());
    }
}
