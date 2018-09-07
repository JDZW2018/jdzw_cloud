package com.github.dao;

import com.github.JdbcTestApplication;
import com.mysql.jdbc.Connection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.*;


/**
 * @author Tianfusheng
 * @date 2018/9/7
 */
@SpringBootTest(classes = JdbcTestApplication.class)
@RunWith(SpringRunner.class)
public class DateTest {
    private Connection con;
    @Before
    public void init() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        //获取连接的语句
        con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_test", "root", "root");
    }
    @Test
    public void dateInsert() throws SQLException {
        String sql = "insert into dt value(?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        java.util.Date d = new java.util.Date();
        pstmt.setDate(1, new java.sql.Date(d.getTime()));
        pstmt.setTime(2, new Time(d.getTime()));
        pstmt.setTimestamp(3, new Timestamp(d.getTime()));
        pstmt.executeUpdate();
    }

    @Test
    public void dateSelect() throws SQLException {
        String sql = "select * from dt";
        PreparedStatement pstmt = con.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        java.util.Date d1 = rs.getDate(1);
        java.util.Date d2 = rs.getTime(2);
        java.util.Date d3 = rs.getTimestamp(3);
        System.out.println(d1);
        System.out.println(d2);
        System.out.println(d3);
    }


}
