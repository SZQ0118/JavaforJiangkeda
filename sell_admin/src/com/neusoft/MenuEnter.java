package com.neusoft;

import java.util.Scanner;

public class MenuEnter {
    public static void main(String[] args) {
        run();
    }
    public static void run(){
        Scanner input = new Scanner(System.in);
        System.out.println("---------------------------------");
        System.out.println("-------欢迎进入饿了么管理系统--------");
        System.out.println("---------------------------------");
        int menu = 0;
        while(menu!=3){
            System.out.println("1.饿了么工作人员登陆入口\n2.饿了么商家登陆入口\n3.退出");
            System.out.println("请输入你要选择的序号:");
            menu = input.nextInt();
            switch (menu){
                case 1:
                    ElmAdminEntry.run();
                    break;
                case 2:
                    BusinessAdminEntry.run();
                    break;
                case 3:
                    System.out.println("再见");
                    break;
            }
        }

    }
}
