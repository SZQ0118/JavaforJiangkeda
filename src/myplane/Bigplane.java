package myplane;

import javax.swing.*;
import java.awt.*;

public class Bigplane extends FlyObject{
    private Integer speed;
    private Image[] images;
    @Override
    public void step() {
        this.y+=speed;//向下移动
    }
    private  int index = 1;
    @Override
    public Image getImage() {
        // 大敌机活着的状态下 显示的是bigplane0.png
        if(isLife()){
            return images[0];
        }
        if(isBomb()){
            Image image = images[index++];
            if(index==images.length)
            {state = DEAD;}
            return image;


        }
        return null;
    }

    public Bigplane() {
        //x坐标在[0,400]内随机正整数 400窗口宽度
        //y坐标是负高度
        super(69, 99,(int)(Math.random() * 400),-99);

        this.speed = 3;
        this.images = new Image[5];
        for (int i = 0; i < images.length; i++) {
            images[i] = new ImageIcon("images/bigplane"+i+".png").getImage();
        }
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getSpeed() {
        return speed;
    }

}
