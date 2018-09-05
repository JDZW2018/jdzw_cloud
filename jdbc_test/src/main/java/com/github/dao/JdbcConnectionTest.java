package com.github.dao;

import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Auther: tianfusheng
 * @Date: 2018/9/6 01:22
 * @Description: jdbc的helloWorld代码
 */
public class JdbcConnectionTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        //获取连接的语句
        Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_test","root","root");
        //获取Statement对象
        Statement stmt = con.createStatement();
        String s = null;
        for(int i=1;i<=10;i++){
            s = "INSERT INTO `user` VALUES('"+i+"','小萝莉','女','12')";
            stmt.executeUpdate(s);
        }
        con.close();
    }
}
