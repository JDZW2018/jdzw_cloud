package com.github.dao;

import com.mysql.jdbc.Connection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.*;

/**
 * @Auther: Administrator
 * @Date: 2018/9/6 01:48
 * @Description: jdbc的crud
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class JdbcConnectionTestTest {
    private Connection con;

    @Before
    public void init() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        //获取连接的语句
        con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_test", "root", "root");
    }

    @Test
    public void insert() throws SQLException {
        Statement stmt = con.createStatement();
        String s = "INSERT INTO `user` VALUES('99','小萝莉','女','12')";
        stmt.executeUpdate(s);
        stmt.close();
        con.close();
    }
    @Test
    public void select() throws SQLException {
        Statement stmt = con.createStatement();
        String s = "SELECT * FROM `user`";
        ResultSet resultSet = stmt.executeQuery(s);
        while (resultSet.next()){
            String name = resultSet.getString(2);
            String sex = resultSet.getString(3);
            String age = resultSet.getString(4);
            System.out.println("name="+name+"; sex="+sex+"; age="+age);
        }
        stmt.close();
        con.close();
    }

    @Test
    public void delete() throws SQLException {
        Statement stmt = con.createStatement();
        String s = "delete FROM `user` where name = '小萝莉'";
        int resultSet = stmt.executeUpdate(s);
        System.out.println(resultSet);
        stmt.close();
        con.close();
    }

    @Test
    public void update() throws SQLException {
        Statement stmt = con.createStatement();
        String s = "update user set age='20' where name = '小萝莉'";
        int resultSet = stmt.executeUpdate(s);
        System.out.println(resultSet);
        stmt.close();
        con.close();
    }

}

