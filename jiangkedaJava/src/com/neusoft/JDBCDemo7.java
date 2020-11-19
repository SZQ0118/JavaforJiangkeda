package com.neusoft;

import com.neusoft.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCDemo7 {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入用户名:");
        String userName = input.next();
        System.out.println("请输入密码:");
        String password = input.next();
        if(login(userName,password)){
            System.out.println("登陆成功");
        }else {
            System.out.println("用户名或密码错误");
        }
    }
    public static boolean login(String userName,String password)throws Exception{
        if(userName==null || password==null){
            return false;
        }
        Connection conn = JDBCUtils.getConnection();
        Statement stmt = conn.createStatement();
        String sql="select * from user where username ='"+userName+"'and password = '"+password+"'";
        ResultSet rs = stmt.executeQuery(sql);
        return rs.next();
    }
}
