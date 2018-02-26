package cn.com.myproject.java8;

/**
 * Created by liyang-macbook on 2016/12/17.
 */
public class AppleHeavyWeightPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 150;
    }
}
