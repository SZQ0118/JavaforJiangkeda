package myplane;

import javax.swing.*;
import java.awt.*;

//boss机
public class Boss extends FlyObject{
    private Integer life;//生命值
    private Image[] images;//图片数组
    private int speedX;//X轴速度
    private int speedY;//y轴速度

    @Override
    public void  step() {
        this.y += speedY;//向下移动一段距离停止  然后开始左右移动
        if(y>8){
            speedY=0;
            //到达窗口右边框的时候 向左移动
            if(x>PlaneFrame.WIDTH-this.width) {
                speedX = -2;
            }
            //到达窗口左边框  向右移动
            if(x<0) {
                speedX = 2;
            }
            this.x += speedX;
        }

    }
    private int index = 1;
    @Override
    public Image getImage() {
        //boss机活着的状态下 显示的是boss0.png
        if(isLife()){
            return images[0];
        }
        if(isBomb()){
            Image img = images[index++];
            if(index == images.length)
            {state = DEAD;}
            return img;
        }
        return null;
    }

    public Boss() {
       super(169,258,150,-258);
       this.life = 15;
        this.speedX = 5;
        this.speedY=5;
       this.images = new Image[7];
        for (int i = 0; i < images.length; i++) {
            images[i] = new ImageIcon("images/boss"+i+".png").getImage();
        }


    }
    //被命中life-1 火力增加
    public void shoted(){
        this.life--;
    }

    public Integer getLife() {
        return life;
    }





    public int getIndex() {
        return index;
    }

    public void setLife(Integer life) {
        this.life = life;
    }


    public int getSpeedX() {
        return speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }
}
