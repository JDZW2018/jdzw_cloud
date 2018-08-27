package cn.com.myproject.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IOTest {

    @Test
    public void fileWriter() throws IOException {

        FileWriter fw = new FileWriter("D:\\demo.txt", true);// FileNotFoundException
       /* fw.write("test2");
        fw.write("test3");*/
        fw.write("测试输入流\r\n");
        fw.write("换行测试\r\n");
        fw.write("换行测试");
        fw.flush();
        fw.close();
    }
    @Test
    public void fileReader() throws IOException {
        FileWriter fw = new FileWriter("D:\\IOTest.txt");// FileNotFoundException
        FileReader fr = new FileReader("D:\\workspace\\jdzw_cloud\\gumiho_redis\\src\\test\\java\\cn\\com\\myproject\\redis\\IOTest.java");
        BufferedReader bufr = new BufferedReader(fr);
        String line = null;
        while((line=bufr.readLine())!=null){//readLine方法返回的时候是不带换行符的。
            System.out.println(line);
            fw.write(line+"\r\n");
            fw.flush();
        }
        fw.close();
        bufr.close();
    }
}
