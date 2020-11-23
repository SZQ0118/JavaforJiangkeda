package com.neusoft.dao.impl;

import com.neusoft.dao.FoodDao;
import com.neusoft.domain.Food;
import com.neusoft.utils.JDBCUtils;

import javax.sql.rowset.JdbcRowSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodDaoImpl implements FoodDao {
    Connection conn = null;
    PreparedStatement pst  = null;
    ResultSet rs = null;
    @Override
    public List<Food> listFoodByBuinessId(Integer BusinessId) {
       String sql = "select *from food where businessId=?";
        ArrayList<Food> list = new ArrayList<>();
        try {
            conn = JDBCUtils.getConnection();
            pst = conn.prepareStatement(sql);
            pst.setInt(1,BusinessId);
            rs = pst.executeQuery();
            while(rs.next()){
                Food food = new Food(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getInt(5));
                list.add(food);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,pst,conn);
        }
        return list;
    }
    @Override
    public int saveFood(Food food) {
        String sql = "insert into food(foodName,foodExplain,foodPrice,businessId) values (?,?,?,?)";
        int Id = 0;
        try {
            conn = JDBCUtils.getConnection();
            pst = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1,food.getFoodName());
            pst.setString(2,food.getFoodExplain());
            pst.setDouble(3,food.getFoodPrice());
            pst.setInt(4,food.getBusinessId());
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

    @Override
    public int updateFood(Food food) {
        String sql = "update food set foodName=?,foodExplain=?,foodPrice=?,businessId=? where foodId=?";
        int result = 0;
        try {
            conn = JDBCUtils.getConnection();
            pst = conn.prepareStatement(sql);
            pst.setString(1,food.getFoodName());
            pst.setString(2,food.getFoodExplain());
            pst.setDouble(3,food.getFoodPrice());
            pst.setInt(4,food.getBusinessId());
            pst.setInt(5,food.getFoodId());
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
    public int removeFood(Integer foodId) {
        int rs = 0;
        String sql = "delete from food where foodId=?";
        try {
            conn = JDBCUtils.getConnection();
            conn.setAutoCommit(false);
            pst = conn.prepareStatement(sql);
            pst.setInt(1,foodId);
            rs = pst.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            JDBCUtils.close(pst,conn);
        }
        return rs;
    }

    @Override
    public Food getFoodById(Integer foodId) {
        Food food = null;
        String sql="select * from food where foodId=?";
        try {
            conn = JDBCUtils.getConnection();
            pst = conn.prepareStatement(sql);
            pst.setInt(1,foodId);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                food = new Food();
                food.setFoodId(rs.getInt(1));
                food.setFoodName(rs.getString(2));
                food.setFoodExplain(rs.getString(3));
                food.setFoodPrice(rs.getDouble(4));
                food.setBusinessId(rs.getInt(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,pst,conn);
        }
        return food;
    }
}
