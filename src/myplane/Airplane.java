package myplane;

import javax.swing.*;
import java.awt.*;

public class Airplane extends FlyObject{
    private Integer speed;
    private Image[] images;

    @Override
    public void step() {
        //向下移动
        this.y += speed;
    }
    private  int index = 1;//第二张图片
    @Override
    public Image getImage() {
        //活着的时候显示images[0] 爆炸显示1-4放完最后一张消失
        if (isLife()){
            return images[0];
        }
        if (isBomb()){
            Image image = images[index++];
            if (index==5){
                state = DEAD;//死了
            }

            return image;
        }
        return null;
    }

    public Airplane() {
        //x坐标在[0,400]内随机正整数 400窗口宽度
        //y坐标是负高度
        super(49, 36,(int)(Math.random() * 400),-36);

        this.speed = 4;
        this.images = new Image[5];
        for (int i = 0; i < images.length; i++) {
            images[i] = new ImageIcon("images/airplane"+i+".png").getImage();
        }
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getSpeed() {
        return speed;
    }

}
