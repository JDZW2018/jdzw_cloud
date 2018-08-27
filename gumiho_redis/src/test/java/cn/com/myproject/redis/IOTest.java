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

    /**
     * 文件输出流
     *
     * @throws IOException
     */
    @Test
    public void fileReader() throws IOException {
        FileWriter fw = new FileWriter("D:\\IOTest.txt");// FileNotFoundException
        FileReader fr = new FileReader("D:\\workspace\\jdzw_cloud\\gumiho_redis\\src\\test\\java\\cn\\com\\myproject\\redis\\IOTest.java");

        char[] buf = new char[1024];
        int len = 0;
        while ((len = fr.read(buf)) != -1) {//readLine方法返回的时候是不带换行符的。
            System.out.println(new String(buf, 0, len));
        }
        fw.close();
        fr.close();
    }

    @Test
    public void BufferedIO() throws IOException {
        FileWriter fw = new FileWriter("D:\\IOTest.txt");// FileNotFoundException
        FileReader fr = new FileReader("D:\\workspace\\jdzw_cloud\\gumiho_redis\\src\\test\\java\\cn\\com\\myproject\\redis\\IOTest.java");
        BufferedReader bufr = new BufferedReader(fr);
        BufferedWriter bufw = new BufferedWriter(fw);
        String len = null;
        while ((len = bufr.readLine()) != null) {
            System.out.println(len);
            bufw.write(len);
            bufw.newLine();
            bufw.flush();
        }
        bufr.close();
        bufw.close();
    }

    @Test
    public void systemOutIn() throws IOException {
        BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufw = new BufferedWriter(new OutputStreamWriter(System.out));
        String line = null;
        while ((line = bufr.readLine()) != null) {
            if ("over".equals(line))
                break;
            bufw.write(line.toUpperCase());//将输入的字符转成大写字符输出
            bufw.newLine();
            bufw.flush();
        }
        bufw.close();
        bufr.close();
    }

}
