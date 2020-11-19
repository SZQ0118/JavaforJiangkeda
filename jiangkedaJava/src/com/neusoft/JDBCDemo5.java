package com.neusoft;

import com.neusoft.domain.Emp;

import java.sql.*;

/*
查询emp表中的所有数据
 */
public class JDBCDemo5 extends Exception{
    public static void main(String[] args) throws Exception{

        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jiangkeda","root","root");
        Statement stmt = conn.createStatement();
        String sql = "select * from emp";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()){
            int id = rs.getInt("EMPNO");
            String ENAME = rs.getString("ENAME");
            String JOB = rs.getString(3);
            int mgr = rs.getInt("MGR");
            Date HIREDATE = rs.getDate(5);
            int SAL = rs.getInt(6);
            int COMM = rs.getInt(7);
            int DEPTNO = rs.getInt(8);
            Emp emp = new Emp(id,ENAME,JOB,mgr,HIREDATE,SAL,COMM,DEPTNO);
            System.out.println(emp);
        }
        stmt.close();
        conn.close();
    }
}
