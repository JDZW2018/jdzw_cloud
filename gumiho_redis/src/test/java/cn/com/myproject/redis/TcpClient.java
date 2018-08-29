package cn.com.myproject.redis;

import java.io.OutputStream;
import java.net.Socket;

public class TcpClient {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("127.0.0.1", 10002);
        OutputStream out = s.getOutputStream();//获取了socket流中的输出流对象。
        out.write("tcp演示，哥们又来了!".getBytes());
        s.close();
    }
}