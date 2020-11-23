package com.neusoft.View.impl;

import com.neusoft.View.FoodView;
import com.neusoft.dao.BusinessDao;
import com.neusoft.dao.impl.BusinessDaoImpl;
import com.neusoft.dao.impl.FoodDaoImpl;
import com.neusoft.domain.Food;
import com.neusoft.utils.JDBCUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class FoodViewImpl implements FoodView {
    private Scanner input = new Scanner(System.in);

    @Override
    public List<Food> showFoodList(Integer businessId) {
        FoodDaoImpl dao = new FoodDaoImpl();
        List<Food> list = dao.listFoodByBuinessId(businessId);
        for (Food f:list){
            System.out.println(f);
        }
        return list;

    }

    @Override
    public void saveFood(Integer businessId) {
        Food food = new Food();
        String str= null;
        Double d = 0.;
        System.out.println("请输入食品名称:");
        str = input.next();
        food.setFoodName(str);

        System.out.println("请输入食品说明:");
        str = input.next();
        food.setFoodExplain(str);

        System.out.println("请输入食品价格:");
        d= input.nextDouble();
        food.setFoodPrice(d);

        food.setBusinessId(businessId);
        FoodDaoImpl dao = new FoodDaoImpl();
        int i = dao.saveFood(food);
        if(i>0){
            System.out.println("食品新建成功");
            Food foo1 = dao.getFoodById(i);
            System.out.println(foo1);
        }else {
            System.out.println("新建食品失败");
        }
    }

    @Override
    public void updateFood(Integer businessId) {
        System.out.println("请输入你要修改的食品编号:");
        int foodid = input.nextInt();
        FoodDaoImpl dao = new FoodDaoImpl();
        Food food = dao.getFoodById(foodid);
        int menu = 0;
        String str = null;
        Double pirce = 0.;
        int rs = 0;
        while(menu!=6){
            System.out.println(">>> 三级菜单 \n1.食品名称"+"\t"+"2.食品说明"+"\t"+"3.食品价格"+"\t"+"4.返回上一层");
            System.out.println("请输入要修改的选项:");
            menu = input.nextInt();
            switch (menu){
                case 1:
                    System.out.println("请输入新的食品名称:");
                    str = input.next();
                    food.setFoodName(str);
                    rs = dao.updateFood(food);
                    break;
                case 2:
                    System.out.println("请输入新的食品说明:");
                    str = input.next();
                    food.setFoodExplain(str);
                    rs = dao.updateFood(food);
                    break;
                case 3:
                    System.out.println("请输入新的食品价格:");
                    pirce = input.nextDouble();
                    food.setFoodPrice(pirce);
                    rs = dao.updateFood(food);
                    break;
                case 4:
                    menu = 6;
                    rs = 3;
                    break;
                default:
                    System.out.println("输入有误，请重新输入");
            }
            if(rs==1){
                System.out.println("修改成功!");
            }else if(rs==0){
                System.out.println("修改失败");
            }
        }
    }

    @Override
    public void removeeFood(Integer businessId) {
        System.out.println("请输入你要修改的食品编号:");
        int foodid = input.nextInt();
        FoodDaoImpl dao = new FoodDaoImpl();
        System.out.println("确定要删除吗(y/n)");
        if(input.next().equals("y")){
            int i = dao.removeFood(foodid);
            if(i>0){
                System.out.println("删除成功");
            }else {
                System.out.println("删除失败");
            }
        }
    }
}
