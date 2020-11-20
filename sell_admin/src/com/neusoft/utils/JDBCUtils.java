package com.neusoft.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;


import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
/*
 *
 *
 *
 */
public class JDBCUtils{
    //定义成员变量DataSource
    private static DataSource ds;
    //初始化静态成员变量
    static {
        try {
            //加载properties文件
            Properties pro = new Properties();
            InputStream in = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            pro.load(in);
            //给ds进行初始化
            ds = DruidDataSourceFactory.createDataSource(pro);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 获取数据库连接
     * @return 连接对象
     * @throws Exception
     */
    public static Connection getConnection()throws Exception{
        return ds.getConnection();
    }
    public static void close(Statement stmt,Connection conn){
        close(null,stmt,conn);
    }

    /**
     * 关闭连接
     * @param rs
     * @param stmt
     * @param conn
     */
    public static void close(ResultSet rs,Statement stmt,Connection conn){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
