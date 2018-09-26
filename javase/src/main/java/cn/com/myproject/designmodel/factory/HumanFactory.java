package cn.com.myproject.designmodel.factory;

/**
 * @author Tianfusheng
 * @date 2018/9/26
 */
public class HumanFactory {
    public static Human makeHuman(Class clazz) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Human human = (Human) Class.forName(clazz.getName()).newInstance();
        return human;
    }
}
