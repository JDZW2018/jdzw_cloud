package cn.com.myproject.designmodel.singleton;

/**
 * @author Tianfusheng
 * @date 2018/9/26
 * 懒汉模式
 */
public class Single2 {
    private Single2(){}
    private static Single2 s = null;
    public static Single2 getInstance(){
        if(s==null){
            s = new Single2();
        }
        return s;
    }
}
