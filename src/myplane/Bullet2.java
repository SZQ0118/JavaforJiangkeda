package myplane;

import javax.swing.*;
import java.awt.*;

//Boss机的子弹类
public class Bullet2 extends FlyObject{
    private  Integer speed;//速度
    private Image image;

    @Override
    public void step() {
        this.y+=speed;
    }

    @Override
    public Image getImage() {
        //活着的时候显示bullet.png 其他状态下消失
        if(isLife()){
            return image;
        }
        return null;//没有图片
    }


    public Bullet2(int x,int y){
        //子弹的x和y无法确定,要看英雄机
        super(18,59,x,y);
        this.speed = 10;
        this.image = new ImageIcon("images/bullet_8.png").getImage();

    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getSpeed() {
        return speed;
    }
}
