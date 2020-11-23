package com.neusoft;

import com.neusoft.View.AdminView;
import com.neusoft.View.BusinessView;
import com.neusoft.View.impl.AdminViewImpl;
import com.neusoft.View.impl.BusinessViewImpl;
import com.neusoft.dao.BusinessDao;
import com.neusoft.dao.impl.BusinessDaoImpl;
import com.neusoft.domain.Admin;
import com.neusoft.domain.Business;
import java.util.List;
import java.util.Scanner;

/**
 *
 * 饿了么管理入口系统
 */
public class ElmAdminEntry {
    public static void main(String[] args) {
        run();
    }
    public static void run(){
        Scanner input = new Scanner(System.in);
        System.out.println("---------------------------------");
        System.out.println("---------饿了么后台管理系统---------");
        System.out.println("---------------------------------");
        AdminView adminView = new AdminViewImpl();
        BusinessDao businessDao = new BusinessDaoImpl();
        Admin admin = adminView.login();
        BusinessView businessdao = new BusinessViewImpl();
        int menu = 0;
        if(admin!=null){
            System.out.println("登陆成功");
            while(menu!=5){
                System.out.println("1.查看所有商家\n2.搜索商家\n3.新增商家\n4.删除商家\n5.退出系统");
                System.out.println("请输入你要选择的序号");
                menu = input.nextInt();
                switch (menu){
                    case 1:
                        businessdao.listAllBusinesses();
                        break;
                    case 2:
                        businessdao.selectBusiness();
                        break;
                    case 3:
                        businessdao.saveBusiness();
                        break;
                    case 4:
                        businessdao.removeBusiness();
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
}
