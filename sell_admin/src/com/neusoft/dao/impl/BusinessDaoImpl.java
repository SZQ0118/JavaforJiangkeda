package com.neusoft.dao.impl;

import com.neusoft.dao.BusinessDao;
import com.neusoft.domain.Admin;
import com.neusoft.domain.Business;
import com.neusoft.utils.JDBCUtils;
import com.sun.org.apache.bcel.internal.generic.ARETURN;

import javax.sql.rowset.JdbcRowSet;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class BusinessDaoImpl implements BusinessDao {
    Connection conn = null;
    PreparedStatement pst  = null;
    ResultSet rs = null;
    @Override
    public List<Business> listBusiness(String businessName,String businessAddress) {
        ArrayList<Business> list= new ArrayList<>();
        StringBuffer sql = new StringBuffer("select * from business where 1=1");
        if(businessName != null && !businessName.equals("")){
            sql.append(" and  businessName LIKE '%"+businessName+"%'");
        }
        if(businessAddress !=null && !businessAddress.equals("")){
            sql.append(" and businessAddress like '%"+businessAddress+"%'");
        }
        try {
            conn = JDBCUtils.getConnection();
            pst = conn.prepareStatement(sql.toString());
            rs = pst.executeQuery();
            while(rs.next()){
                Business business = new Business();
                int businessId = rs.getInt(1);
                String password = rs.getString(2);
                String businessName1 = rs.getString(3);
                String businessAddress1 = rs.getString(4);
                String businessExplain =rs.getString(5);
                Double startPrice = rs.getDouble(6);
                Double deliveryPrice = rs.getDouble(7);
                business.setBusinessId(businessId);
                business.setPassword(password);
                business.setBusinessName(businessName1);
                business.setBusinessAddress(businessAddress1);
                business.setBusinessExplain(businessExplain);
                business.setStartPrice(startPrice);
                business.setDeliveryPrice(deliveryPrice);
                list.add(business);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,pst,conn);
        }

        return list;
    }

    @Override
    public int saveBusiness(String businessName) {
        int Id = 0;
        String sql="insert into business(businessName,password) values (?,'123')";
        try {
            conn = JDBCUtils.getConnection();
            //返回自增长的键
            pst = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1,businessName);
            pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
            if(rs.next()){
                Id = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,pst,conn);
        }
        return Id;
    }

    /**
     * 删除商家
     * @param businessId 商家id
     * @return 0 代表删除失败，1代表删除成功
     */
    @Override
    public int removeBusiness(int businessId) {
        int result = 0;
        String sql="delete from business where businessId =?";
        try {
            conn = JDBCUtils.getConnection();
            //开启事务
            conn.setAutoCommit(false);
            pst = conn.prepareStatement(sql);
            pst.setInt(1,businessId);
            result = pst.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            result = 0;
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            JDBCUtils.close(pst,conn);
        }
        return result;
    }

    @Override
    public int updateBusiness(Business business) {

        String sql = "update business set businessName=?,businessAddress=?,businessExplain=?,starPrice=?,deliveryPrice=? where businessId=?";
        int result = 0;
        try {
            conn = JDBCUtils.getConnection();
            pst = conn.prepareStatement(sql);
            pst.setString(1,business.getBusinessName());
            pst.setString(2,business.getBusinessAddress());
            pst.setString(3,business.getBusinessExplain());
            pst.setDouble(4,business.getStartPrice());
            pst.setDouble(5,business.getDeliveryPrice());
            pst.setInt(6,business.getBusinessId());
            result = pst.executeUpdate();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,pst,conn);
        }
        return result;
    }

    @Override
    public Business getBusinessById(Integer businessId) {
        String sql ="select * from business where businessId=?";
        Business business =null;
        try {
            conn = JDBCUtils.getConnection();
            pst = conn.prepareStatement(sql);
            pst.setInt(1,businessId);
            rs = pst.executeQuery();
            while(rs.next()){
                business = new Business();
                business.setBusinessName(rs.getString("businessName"));
                business.setBusinessId(rs.getInt("businessId"));
                business.setBusinessAddress(rs.getString("businessAddress"));
                business.setBusinessExplain(rs.getString("businessExplain"));
                business.setStartPrice(rs.getDouble("starPrice"));
                business.setDeliveryPrice(rs.getDouble("deliveryPrice"));
                business.setPassword(rs.getString("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            JDBCUtils.close(rs,pst,conn);
        }
      return business;
    }

    @Override
    public Business getBusinessByIdAndPassword(Integer businessId, String password) {
        Business business = null;
        String sql = "select * from business where businessId=? and password=?";
        try {
            conn = JDBCUtils.getConnection();
            pst = conn.prepareStatement(sql);
            pst.setInt(1,businessId);
            pst.setString(2,password);
            rs = pst.executeQuery();
            while(rs.next()){
                business = new Business(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getNString(4),rs.getString(5),rs.getDouble(6),rs.getDouble(7));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,pst,conn);
        }
        return business;
    }

    @Override
    public int updateBusinessPassword(Integer businessId,String password) {
        int res = 0;
        String sql = "update business set password = ? where businessId = ?";
        try {
            conn = JDBCUtils.getConnection();
            pst = conn.prepareStatement(sql);
            pst.setString(1, password);
            pst.setInt(2, businessId);
            res = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs, pst, conn);
        }
        return res;
    }
}
