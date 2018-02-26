package cn.com.myproject.java8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by liyang-macbook on 2016/12/17.
 */
public class AppleMain {


    public static List<Apple> filterApples(List<Apple> inventory,ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static void main(String[] args){
        System.out.println("+++++++++");
        List<Apple> result = new ArrayList<>();
        Apple apple = null;
        for(int i=0;i<10;i++){
            apple = new Apple();
            apple.setWeight(i*100);
            apple.setColor("green");
            result.add(apple);
        }
        AppleMain.filterApples(result,new AppleGreenColorPredicate());
        AppleMain.filterApples(result,(Apple apple1) -> "red".equals(apple1.getColor()) );
        result.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight()-o2.getWeight();
            }
        });
        result.sort((o1, o2) -> o1.getWeight()-o2.getWeight());
    }

}
