package com.github.dao;


import com.github.JdbcTestApplication;
import com.mysql.jdbc.Connection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @Author tianfusheng
 * @Date 2018年9月7日
 * @Desc  JdbcPreparedStatementTest
 */
@SpringBootTest(classes = JdbcTestApplication.class)
@RunWith(SpringRunner.class)
public class JdbcPreparedStatementTest {
    private Connection con;
    @Before
    public void init() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        //获取连接的语句
        con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_test", "root", "root");
    }

    @Test
    public void insert(){

    }
    @Test
    public void update(){

    }

    @Test
    public void select() throws SQLException {
        String sql = "select * from user where id = ?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1,"1");
        ResultSet resultSet = pstmt.executeQuery();
        this.resultSetPrint(resultSet);
        pstmt.close();
        con.close();
    }
    private void resultSetPrint(ResultSet resultSet)throws SQLException {
        while (resultSet.next()){
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String sex = resultSet.getString(3);
            String age = resultSet.getString(4);
            System.out.println("id="+id+"; name="+name+"; sex="+sex+"; age="+age);
        }
        resultSet.close();
    }

    @Test
    public void bachTest() throws SQLException {
        String sql = "INSERT INTO user VALUES (?,?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        for(int i = 0 ; i<10; i++){
            pstmt.setString(1,String.valueOf(i));
            pstmt.setString(2,"大屌小仙女"+i);
            pstmt.setString(3,"中性");
            pstmt.setString(4,"18");
            pstmt.addBatch();
        }
        pstmt.executeBatch();
        pstmt.close();
        con.close();
    }

    /**
     * 事务手动提交测试
     * @throws SQLException
     */
    @Test
    public void  transferTest() throws SQLException {

            con.setAutoCommit(false);
            String sql = "UPDATE user SET name =? WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
        try {
            pstmt.setString(1,"大屌小仙女plus");
            pstmt.setString(2,"1");
            pstmt.executeUpdate();
            con.commit();
            //throw  new SQLException();
        } catch (SQLException e) {
            if(con != null){
                try {
                    con.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            throw new RuntimeException(e);
        }finally {
            pstmt.close();
            con.close();
        }

    }

}
