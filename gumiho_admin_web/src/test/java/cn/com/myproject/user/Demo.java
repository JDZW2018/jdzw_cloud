package cn.com.myproject.user;

import org.json.JSONArray;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<String> list = new ArrayList<>();
        for(int i=0 ;i<1000;i++) {
            list.add(i+"");
        }
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("/Users/liyang-macbook/url/1.obj"));
        out.writeObject(list);
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("/Users/liyang-macbook/url/1.obj"));
        List<String>  obj1 = (List<String>) in.readObject();
        for (String str : obj1) {
            System.out.println(str);
        }
    }


}

