package javase.T;

import cn.com.myproject.redis.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author tianfusheng
 * @desc 泛型测试学习
 * @Date 2018.09.03
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class Ttest {

    /**
     * 泛型与反射结合，获取泛型注入的对象person
     */
    @Test
    public void demo1() throws InstantiationException, IllegalAccessException {
        demo1<Person> demo1 = new demo1<Person>(Person.class);
        Person person = demo1.getEntry();
        person.setName("Tom");
        System.out.println(person.toString());
    }

    class demo1<T> {
        private T t;
        private Class clazz;

        public demo1(Class<T> clazz) {
            this.clazz = clazz;
        }

        public T getEntry() throws IllegalAccessException, InstantiationException {
            this.t = (T) clazz.newInstance();
            return t;
        }
    }
}