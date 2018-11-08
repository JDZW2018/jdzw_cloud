package cn.com.myproject.FieldAndMethodTest;

public class FieldAndMethodTest {
    public static void main(String[] args) {
        Sub sub = new Sub();
        Mid mid =sub;
        Base base = sub;
        System.out.println(sub.count+"  "+sub.getCount());
        System.out.println(mid.count+"  "+mid.getCount());
        System.out.println(base.count+"  "+base.getCount());
    }
}

class Base {
    int count = 2;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

class Mid extends Base {
    int count = 20;

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public void setCount(int count) {
        this.count = count;
    }
}

class Sub extends Mid {
    int count = 200;

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public void setCount(int count) {
        this.count = count;
    }
}
