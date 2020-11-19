package com.neusoft;

import com.neusoft.domain.Emp;
import com.neusoft.utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
查询emp中所有数据
 */
public class JDBCDemo6 extends Exception{
    public static void main(String[] args)throws Exception {
        List<Emp> empList =findAll2();
        for(Emp emp:empList){
            System.out.println(emp);
        }
    }
    //List<Emp> <>代表泛型的意思，就是对存储数据类型做限制
    public static List<Emp> findAll()throws Exception{
        //多态写法：父类引用子类对象
        //声明一个list存储所有emp对象
        List<Emp> list = new ArrayList<>();
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
            list.add(emp);
        }
        stmt.close();
        conn.close();
        return list;
    }
    //调用JDBCUtils 工具类进行操作
    public static List<Emp> findAll2() throws Exception {
        List<Emp> list = new ArrayList<>();
        Connection conn = JDBCUtils.getConnection();
        String sql = "select * from emp";
        Statement stmt = conn.createStatement();
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
            list.add(emp);
        }
        JDBCUtils.close(rs,stmt,conn);
        return list;
    }
}
