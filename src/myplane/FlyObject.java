package myplane;

import java.awt.*;

//所有飞行物的父类
public abstract class FlyObject {
    //protected对父类子类可见
    protected Integer width;//宽度
    protected Integer height;//高度
    protected Integer x;//x坐标
    protected Integer y;//y坐标
    //飞行物状态:活着 爆炸 死亡
    public static final int LIFE = 1;
    public static final int BOMB = 2;
    public static final int DEAD = 3;
    //飞行物的当前状态 默认初始状态是活着
    protected Integer state = LIFE;


    //飞行物的移动
    public abstract void step();
    //根据飞行物的状态显示当前图片
    public abstract Image getImage();

    public boolean isLife(){return state==LIFE;}
    public boolean isBomb(){return state==BOMB;}
    public boolean isDead(){return state==DEAD;}

    //改为 死了的状态
    public void goDead() {
        state = DEAD;
    }
    //改为 爆炸的状态
    public void goBomb() {
        state = BOMB;
    }
    //改为活着的状态
    public void goLife() {
        state = LIFE;
    }


    //set和get方法
    //构造方法：快捷键:alt+insert 选择constr
    public void setWidth(Integer width) {
        this.width = width;
    }
    public Integer getWidth() {
        return width;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getHeight() {
        return height;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getX() {
        return x;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getY() {
        return y;
    }

    //构造方法
    public FlyObject(Integer width, Integer height, Integer x, Integer y) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }
    public FlyObject(){ }
}
