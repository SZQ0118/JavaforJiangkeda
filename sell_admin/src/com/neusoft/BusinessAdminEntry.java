package com.neusoft;

import com.neusoft.View.BusinessView;
import com.neusoft.View.FoodView;
import com.neusoft.View.impl.BusinessViewImpl;
import com.neusoft.View.impl.FoodViewImpl;
import com.neusoft.domain.Business;

import java.util.Scanner;

/**
 * 入驻商家管理入口程序
 */
public class BusinessAdminEntry {

    public static void main(String[] args) {
            run();
    }
    public static void run(){
        System.out.println("---------------------------------");
        System.out.println("-------饿了么商家自主管理系统--------");
        System.out.println("---------------------------------");
        //商家登陆
        Scanner input = new Scanner(System.in);
        BusinessView businessdao = new BusinessViewImpl();
        Business business = businessdao.login();
        int menu = 0;
        if(business!=null){
            System.out.println("登陆成功，欢迎"+business.getBusinessName()+"回来");
            while(menu!=5){
                System.out.println(">>> 一级菜单 \n1.查看商家信息\n2.修改商家信息\n3.修改密码\n4.所属商品管理\n5.退出系统");
                System.out.println("请输入你要选择的序号");
                menu = input.nextInt();
                switch (menu){
                    case 1:
                        businessdao.showBusiness(business.getBusinessId());
                        break;
                    case 2:
                        businessdao.updateBusiness(business);
                        break;
                    case 3:
                        businessdao.updatePassword(business.getBusinessId());
                        break;
                    case 4:
                        foodManger(business.getBusinessId());
                        break;
                    case 5:
                        System.out.println("欢迎下次光临");
                        break;
                    default:
                        System.out.println("没有这个选项");
                        break;
                }
            }
        }else {
            System.out.println("登陆失败,用户密码错误");
        }
    }

    public static void foodManger(Integer businessId){
        Scanner input = new Scanner(System.in);
        FoodView foodView = new FoodViewImpl();
        int menu = 0;
        while(menu!=5){
            System.out.println(">>> 二级菜单 \n1.查看食品信息\n2.修改食品信息\n3.增加食品信息\n4.删除食品信息\n5.返回上一级菜单");
            System.out.println("请输入你要选择的序号");
            menu = input.nextInt();
            switch (menu){
                case 1:
                foodView.showFoodList(businessId);
                    break;
                case 2:
                foodView.updateFood(businessId);
                    break;
                case 3:
                foodView.saveFood(businessId);
                    break;
                case 4:
                foodView.removeeFood(businessId);
                    break;
                case 5:
                    break;
                default:
                    System.out.println("没有这个选项");
                    break;
            }
        }

    }
}
