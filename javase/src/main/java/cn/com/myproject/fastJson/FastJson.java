package cn.com.myproject.fastJson;

import com.alibaba.fastjson.JSONObject;
/**
 * @author 94946
 * @date 2019/2/13
 * @DESC
 */
public class FastJson {
    public static void main(String[] args) {
        Person person = new Person();
        person.setAge("12");
        person.setName("wang");
        String s = JSONObject.toJSONString(person);
        System.out.println(s);
        JSONObject object = JSONObject.parseObject(s);
        System.out.println(object.toString());
    }
}
