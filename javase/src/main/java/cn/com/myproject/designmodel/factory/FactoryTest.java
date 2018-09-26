package cn.com.myproject.designmodel.factory;

/**
 * @author Tianfusheng
 * @date 2018/9/26
 */
public class FactoryTest {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
       Human human =  HumanFactory.makeHuman(Man.class);
        human.say();
        Human woman = HumanFactory.makeHuman(Woman.class);
        woman.say();
    }
}
