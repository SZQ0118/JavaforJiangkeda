package myplane;

import javax.swing.*;
import java.awt.*;

//子弹类
public class Bullet extends FlyObject{
    private  Integer speed;//速度
    private  Image image;

    @Override
    public void step() {
     this.y-=speed;
    }

    @Override
    public Image getImage() {
        //活着的时候显示bullet.png 其他状态下消失
       if(isLife()){
           return image;
       }
       return null;//没有图片
    }


    //检测敌人和子弹是否碰撞
    public boolean shoot(FlyObject enemy) {
        int x1 = enemy.getX()-this.width;//敌人的x坐标-子弹的宽度
        int x2 = enemy.getX()+enemy.getWidth();//敌人的x坐标+敌人的宽度
        int y1 = enemy.getY();//敌人的y坐标
        int y2 = enemy.getY()+enemy.getHeight();//敌人的y坐标+敌人的高度
        //子弹的x和y坐标 在四点范围内的 表示碰撞的
        return x>x1 && x<x2 && y>y1 && y<y2;
    }
    public Bullet(int x,int y){
        //子弹的x和y无法确定,要看英雄机
        super(8,14,x,y);
        this.speed = 10;
        this.image = new ImageIcon("images/bullet.png").getImage();

    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getSpeed() {
        return speed;
    }

}
