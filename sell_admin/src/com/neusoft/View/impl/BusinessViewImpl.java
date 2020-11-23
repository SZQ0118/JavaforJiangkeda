package com.neusoft.View.impl;

import com.neusoft.View.BusinessView;
import com.neusoft.dao.BusinessDao;
import com.neusoft.dao.impl.BusinessDaoImpl;
import com.neusoft.domain.Business;

import java.util.List;
import java.util.Scanner;

public class BusinessViewImpl implements BusinessView {
   private Scanner input = new Scanner(System.in);
    @Override
    public void listAllBusinesses(){
        BusinessDao dao = new BusinessDaoImpl();
        List<Business> list = dao.listBusiness(null, null);
        System.out.println("商家编号"+"\t"+"商家名称"+"\t"+"商家地址"+"\t"+"商家备注"+"\t"+"商家配送费"+"\t"+"商家起送费");
        for(Business b:list){
            System.out.println(b.getBusinessId()+"\t"+b.getBusinessName()+"\t"+b.getBusinessAddress()+"\t"+b.getBusinessExplain()+"\t"+b.getDeliveryPrice()+"\t"+b.getStartPrice());
        }
    }

    @Override
    public void selectBusiness() {
        String inputStr = "";
        String businessName = "";
        String businessAddress ="";
        System.out.println("请输入是否输入商家名称关键词(y/n)");
        inputStr = input.next();
        if(inputStr.equals("y")){
            System.out.println("请输入商家名称关键词:");
            businessName = input.next();
        }
        System.out.println("请输入是否输入商家地址关键词(y/n)");
        inputStr = input.next();
        if(inputStr.equals("y")){
            System.out.println("请输入商家地址关键词:");
            businessAddress = input.next();
        }
        BusinessDao dao = new BusinessDaoImpl();
        List<Business> list = dao.listBusiness(businessName, businessAddress);
        System.out.println("商家编号"+"\t"+"商家名称"+"\t"+"商家地址"+"\t"+"商家备注"+"\t"+"商家配送费"+"\t"+"商家起送费");
        for(Business b:list){
            System.out.println(b.getBusinessId()+"\t"+b.getBusinessName()+"\t"+b.getBusinessAddress()+"\t"+b.getBusinessExplain()+"\t"+b.getDeliveryPrice()+"\t"+b.getStartPrice());
        }
    }

    @Override
    public void saveBusiness() {
        String businessName = null;
        System.out.println("请输入新商家的名称");
        businessName = input.next();
        BusinessDao dao = new BusinessDaoImpl();
        int businessId = dao.saveBusiness(businessName);
        //根据id进行查询，然后进行回显
        if(businessId>0){
            System.out.println("保存成功");
            Business business = dao.getBusinessById(businessId);
            System.out.println(business);
        }else {
            System.out.println("新建商家失败");
        }
    }

    @Override
    public void removeBusiness() {
        Integer businessId = null;
        System.out.println("请输入你要删除的商家ID");
        businessId = input.nextInt();
        BusinessDao dao = new BusinessDaoImpl();
        System.out.println("确认是否删除(y/n)");
        if(input.next().equals("y")){
            int i = dao.removeBusiness(businessId);
            if(i==1){
            System.out.println("删除成功");
            }else {
                System.out.println("删除失败");
            }
        }
    }

    @Override
    public Business login() {
        System.out.println("请输入商家编号:");
        Integer businessId = input.nextInt();

        System.out.println("请输入密码:");
        String password = input.next();

        BusinessDao dao = new BusinessDaoImpl();
        Business business = dao.getBusinessByIdAndPassword(businessId, password);
        return business;
    }

    @Override
    public void showBusiness(Integer businessId) {
        BusinessDao dao = new BusinessDaoImpl();
        Business business = dao.getBusinessById(businessId);
        System.out.println("商家编号"+"\t"+"商家名称"+"\t"+"商家地址"+"\t"+"商家备注"+"\t"+"商家配送费"+"\t"+"商家起送费");
        System.out.println(business.getBusinessId()+"\t"+business.getBusinessName()+"\t"+business.getBusinessAddress()+"\t"+business.getBusinessExplain()+"\t"+business.getDeliveryPrice()+"\t"+business.getStartPrice());
    }

    @Override
    public void updatePassword(Integer businessId) {
        BusinessDao dao = new BusinessDaoImpl();
        Business bs = dao.getBusinessById(businessId);

        System.out.println("请输入旧密码");
        String oldPass = input.next();
        System.out.println("请输入新密码");
        String newPass = input.next();
        System.out.println("请再次输入新密码");
        String beginNewPass = input.next();
        // 进行密码校验
        if (!bs.getPassword().equals(oldPass)){
            System.out.println("你的密码蒙错了，请重新输入");
        }else if (!newPass.equals(beginNewPass)){
            System.out.println("两次密码不一致请重新输入");
        }else {
            int res = dao.updateBusinessPassword(businessId,newPass);
            if (res>0){
                System.out.println("修改密码成功！");
            }else {
                System.out.println("修改密码失败！");
            }
        }
    }

    @Override
    public void updateBusiness(Business business) {
        BusinessDao dao = new BusinessDaoImpl();
        int menu = 0;
        String str = null;
        Double pirce = 0.;
        int rs = 0;
        while(menu!=6){
            System.out.println(">>> 二级菜单 \n1.商家名称"+"\t"+"2.商家地址"+"\t"+"3.商家备注"+"\t"+"4.商家配送费"+"\t"+"5.商家起送费"+"\t"+"6.返回上一层");
            System.out.println("请输入要修改的选项:");
            menu = input.nextInt();
            switch (menu){
                case 1:
                    System.out.println("请输入新的商家名称:");
                    str = input.next();
                    business.setBusinessName(str);
                    rs = dao.updateBusiness(business);
                    break;
                case 2:
                    System.out.println("请输入新的商家地址:");
                    str = input.next();
                    business.setBusinessAddress(str);
                    rs = dao.updateBusiness(business);
                    break;
                case 3:
                    System.out.println("请输入新的商家备注:");
                    str = input.next();
                    business.setBusinessExplain(str);
                    rs = dao.updateBusiness(business);
                    break;
                case 4:
                    System.out.println("请输入新的商家配送费:");
                    pirce = input.nextDouble();
                    business.setDeliveryPrice(pirce);
                    rs = dao.updateBusiness(business);
                    break;
                case 5:
                    System.out.println("请输入新的商家起送费:");
                    pirce = input.nextDouble();
                    business.setStartPrice(pirce);
                    rs = dao.updateBusiness(business);
                    break;
                case 6:
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
}
