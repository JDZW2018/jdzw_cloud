package cn.com.myproject.java8;

/**
 * Created by liyang-macbook on 2016/12/17.
 */
public class AppleGreenColorPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return "green".equals(apple.getColor());
    }
}
