package cn.com.myproject.redis;

import java.io.Console;

/**
 * @Auther: Administrator
 * @Date: 2018/9/13 18:09
 * @Description:
 */
public class ConsoleDemoTest {

    public static void main(String[] args) {
        Console console  = System.console();
        if(console==null){
            System.out.println("不能使用控制台");
            return;
        }
        String username = console.readLine("请输入用户名：");
        char[] p = console.readPassword("请输入密码：");
        System.out.println("name:"+username);
        System.out.println("password:");
        for(char c :p ){
            System.out.print(c);
        }
    }
}
