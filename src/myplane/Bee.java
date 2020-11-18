package myplane;

import javax.swing.*;
import java.awt.*;

public class Bee extends FlyObject {
    private Integer speedX;//x方向速度
    private Integer speedY;//y方向速度
    private Integer awardType;//奖励类型  0表示加1命 1表示加40火力值
    private Image[] images;


    @Override
    public void step() {
        this.x+=speedX;
        this.y+=speedY;
        //到达窗口右边框 向左下移动
        if(this.x>=400-width){
            speedX = -1;
        }
        //到达窗口左边框，向右上移动
        if(this.x<=0){
            speedX = 1;
        }

    }
    private int index = 1;
    @Override
    public Image getImage() {
         //小蜜蜂活着的状态下 显示的是bee0.png
        if(isLife()){
            return images[0];
        }
        if(isBomb()){
            Image image = images[index++];
            if(index == images.length)
            {state = DEAD;}
            return image;
        }
        return null;
    }

    public Bee(){
        super(60,50,(int)(Math.random() * 400),-50);
        this.speedX=1;
        this.speedY=2;
        this.awardType = (int)(Math.random() * 2)+1;
        this.images = new Image[5];
        for (int i = 0; i < images.length; i++) {
            images[i] = new ImageIcon("images/bee"+i+".png").getImage();
        }
    }

    public void setSpeedX(Integer speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(Integer speedY) {
        this.speedY = speedY;
    }

    public void setAwardType(Integer awardType) {
        this.awardType = awardType;
    }

    public Integer getSpeedX() {
        return speedX;
    }

    public Integer getSpeedY() {
        return speedY;
    }

    public Integer getAwardType() {
        return awardType;
    }
}
