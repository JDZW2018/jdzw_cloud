package javase.collection;

import cn.com.myproject.redis.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.SortedSet;
import java.util.TreeSet;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TreeSetTest {

    /**
     * 按顺序存储
     */
    @Test
    public void hellWorld(){
        SortedSet<String> sortedSet = new TreeSet<String>();
        sortedSet.add("adc");
        sortedSet.add("bac");
        sortedSet.add("abc");
        sortedSet.add("ABC");
        for(String s : sortedSet){
            System.out.println(s);
        }
    }

    public void mainTest(){

    }
}