package com.neusoft;

import java.sql.*;

/*
查询操作
 */
public class JDBCDemo4 extends Exception{
    public static void main(String[] args)throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jiangkeda","root","root");
        String sql = "select * from account";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
//       while (rs.next()){
//           int id = rs.getInt(1);
//           String name = rs.getString(2);
//           double balance = rs.getDouble(3);
//           System.out.println(id+"------"+name+"------"+balance);
//       }

        while (rs.next()){
            int id = rs.getInt("id");
            String name = rs.getString("name");
            double balance = rs.getDouble("balance");
            System.out.println(id+"------"+name+"------"+balance);
        }

        stmt.close();
        conn.close();
    }
}
