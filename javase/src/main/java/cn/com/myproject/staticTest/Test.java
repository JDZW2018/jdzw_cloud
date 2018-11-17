package cn.com.myproject.staticTest;

public class Test {

    public static void main(String[] args) {
        Student.setName("nihao");
        Student student1 = new Student();
        student1.setAge("123");
        System.out.println(Student.getName());
    }
}
