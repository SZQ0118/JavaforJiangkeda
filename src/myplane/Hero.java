package myplane;

import javax.swing.*;
import java.awt.*;

public class Hero extends FlyObject{
    private Integer life;//生命值
    private Integer doubleFire;//火力值
    private Image[] images;

    //英雄机跟随键盘移动
    public void heromove(int x1,int y1){

        x+= x1;
        y+=y1;
    }
    @Override
    public void step() {
        System.out.println("跟随鼠标移动");
    }

    //英雄机火力值减少2
    public void subDoublefire() {
        this.doubleFire -= 1;
    }
    //英雄机加1命
    public void addLife() {
        this.life += 1;
    }
    //英雄机得双倍火力
    public void addDoubleFire() {
        this.doubleFire += 40;
    }
    //英雄机减1命
    public void subLife() {
        this.life -= 1;
    }
    //检测英雄机与敌人是否碰撞
    //以英雄机 中心点作为碰撞依据
    public boolean hit(FlyObject enemy) {
        int xCenter = x+width/2;//英雄机中心点x坐标
        int yCenter = y+height/2;//英雄机中心点y坐标
        int x1 = enemy.getX()-width/2;//敌人的x-hero宽度/2
        int x2 = enemy.getX()+enemy.getWidth()+width/2;//敌人的x+敌人的宽度+hero宽度/2
        int y1 = enemy.getY()-height/2 ;//敌人的y-hero高度/2
        int y2 = enemy.getY()+enemy.getHeight()+height/2;//敌人的y+敌人的高度+hero高度/2
        return xCenter>x1 && xCenter<x2 && yCenter>y1 && yCenter<y2;
    }
    private int index = 0;//切换图片
    @Override
    public Image getImage() {
        //活着的时候显示第1和2图片来回切换
        //10ms 第一张图片 20ms第二个图片 30ms第一张图片
        if(isLife()){
            return images[index++%2];
        }
       return null;
    }

    public Hero(){
        //初始位置是固定的
        super(97,124,150,400);
        this.life = 10;
        this.doubleFire = 0;
        this.images = new Image[6];
        for (int i = 0; i < images.length; i++) {
            images[i] = new ImageIcon("images/hero"+i+".png").getImage();

        }
    }

    public void setLife(Integer life) {
        this.life = life;
    }

    public void setDoubleFire(Integer doubleFire) {
        this.doubleFire = doubleFire;
    }

    public Integer getLife() {
        return life;
    }

    public Integer getDoubleFire() {
        return doubleFire;
    }

}
