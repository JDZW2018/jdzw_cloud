package cn.com.myproject.staticTest;

public class Student {
    private static String name;
    private String age;

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Student.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
