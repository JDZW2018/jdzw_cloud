package cn.com.myproject.redis;

import com.sun.org.apache.xpath.internal.operations.String;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Auther: Administrator
 * @Date: 2018/9/14 10:42
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class NIOTest {
    /**
     * ？测试未通过，如何输出byte[]
     * @throws IOException
     */
    @Test
    public void nioRead() throws IOException {
        FileInputStream fis = new FileInputStream("D:/IOTest.txt");
        FileChannel channel = fis.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        CharBuffer c = CharBuffer.allocate(1024);
        channel.read(byteBuffer);
        System.out.println(byteBuffer.array());
        fis.close();
    }

    /**
     * nio写入操作
     * @throws IOException
     */
    @Test
    public void nioWrite() throws IOException {
        java.lang.String message = "java mybatis spring mysql springmvc springBoot springCloud";
        FileOutputStream fileOutputStream = new FileOutputStream("D:/NIOTest.txt");
        FileChannel channel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer =  ByteBuffer.allocate(1024);
        byteBuffer.put(message.getBytes());
        byteBuffer.flip();
        channel.write(byteBuffer);
        fileOutputStream.close();
    }

    @Test
    public void memoryMapTest() throws IOException {
        FileInputStream fis = new FileInputStream("D:/IOTest.txt");
        FileChannel channel = fis.getChannel();
        int len = (int) channel.size();
        MappedByteBuffer mbb = channel.map(FileChannel.MapMode.READ_ONLY,0,len);
        for(int i=0; i<len;i++){
            System.out.print((char) mbb.get(i));
        }
        fis.close();
    }
}
