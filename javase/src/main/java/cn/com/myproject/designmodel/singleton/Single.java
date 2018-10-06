package cn.com.myproject.designmodel.singleton;

/**
 * @author Tianfusheng
 * @date 2018/9/26
 */
public class Single {
    private Single() {
    } //私有化构造函数。

    private static Single s = new Single(); //创建私有并静态的本类对象。

    public static Single getInstance() { //定义公有并静态的方法，返回该对象。
        return s;
    }
}
