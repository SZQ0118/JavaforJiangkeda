package myplane;

import javax.swing.*;
import java.awt.*;

/*
*  必看操作说明
* 由原先的鼠标控制游戏操作改为用键盘操作
* 键盘上下左右，控制英雄机的移动
* 键盘ESC，暂停游戏
* 键盘空格释放核弹，秒杀敌人
*
*/

public class PlaneFrame {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 600;
    public static void main(String[] args) {
        //创建窗口
        JFrame frame = new JFrame("飞机大战");
        //设置窗口大小
        frame.setSize(400,600);
        //设置窗口得位置居中
        frame.setLocationRelativeTo(null);
        //设置关闭操作
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //创建画布jpanel
        PlanePanel pan = new PlanePanel();
        pan.setBackground(Color.yellow);
        //将画布添加到窗口类
        frame.add(pan);

        //多线程调用
        pan.move();
        frame.addMouseMotionListener(pan);
        frame.addMouseListener(pan);
        frame.addKeyListener(pan);
        //设置窗口可见
        frame.setVisible(true);
        //设置窗口JAVAlogo
        Image image = new ImageIcon("images/plane_1.png").getImage();
        frame.setIconImage(image);
    }
}
