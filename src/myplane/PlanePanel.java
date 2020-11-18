package myplane;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

//将image文件拷贝到当前项目的根目录中

//自定义画布
public class PlanePanel extends JPanel implements MouseMotionListener,MouseListener, KeyListener {

    //保存当前游戏状态
    int state = START ;
    //定义4种状态常量
    public static final int START = 1;//开始状态
    public static final int RUNNING = 2;//运行状态
    public static final int PAUSE = 3;//暂停状态
    public static final int GAMEOVER = 4;//结束状态

    //保存游戏的分数
    int score = 0;
    int maxsore = 0;//保存最高分

    //保存背景图片的坐标
    Integer backgroundx = 0;
    Integer backgroundy = -5400;

    //英雄机
    Hero hero = new Hero();
    //Boss机
    Boss boss = new Boss();
    //全屏炸弹
    Boom boom = new Boom();
    //子弹集合
    ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    ArrayList<Bullet2> bullet2s = new ArrayList<Bullet2>();
    //敌人 大小 蜜蜂
    ArrayList<FlyObject> enemies = new ArrayList<FlyObject>();
   //敌人boss机
    ArrayList<Boss> bossplane = new ArrayList<Boss>();
    //重写JPanel中的paint方法 Graphics g画笔
//保存不同的状态图
    Image startImage;
    Image pauseImage;
    Image gameoverImage;
    //保存背景图
    Image bk;
    public PlanePanel() {
        //测试添加假数据
        bullets.add(new Bullet(100,200));
        //测试：可以先将所有敌人的y坐标 改为 正的高度
        enemies.add(new Airplane());
        enemies.add(new Bigplane());
        enemies.add(new Bee());
//        bossplane.add(new Boss());
        //画背景图  new ImageIcon("文件路径") 相对路径：相对于当前工程的根目录开始寻找
        bk = new ImageIcon("images/background_3.png").getImage();
        startImage = new ImageIcon("images/interface_1.png").getImage();
        pauseImage = new ImageIcon("images/pause.png").getImage();
        gameoverImage = new ImageIcon("images/jeimian_2.png").getImage();
    }

    @Override
    //画内容
    public void paint(Graphics g) {
        super.paint(g);
        //画背景图
        g.drawImage(bk, backgroundx, backgroundy, null);
        //画英雄机
        g.drawImage(hero.getImage(), hero.getX(), hero.getY(), null);
        //画子弹
        for (int i = 0; i < bullets.size(); i++) {
            Bullet b = bullets.get(i);
            g.drawImage(b.getImage(), b.getX(), b.getY(), null);
        }
        for (int i = 0; i < bullet2s.size(); i++) {
            Bullet2 b2 = bullet2s.get(i);
            g.drawImage(b2.getImage(), b2.getX(), b2.getY(), null);
        }
        //画敌人
        for (int i = 0; i < enemies.size(); i++) {
            FlyObject a = enemies.get(i);
            g.drawImage(a.getImage(), a.getX(),a.getY(), null);
        }
        //画boss机
        for (int i = 0; i < bossplane.size(); i++) {
            Boss bs = bossplane.get(i);
            g.drawImage(bs.getImage(),bs.getX(),bs.getY(),null);
        }
        //画分数 和 生命值
        ImageIcon lifeimage = new ImageIcon("images/planelife.png");
        ImageIcon boomimage = new ImageIcon("images/boom.png");

        g.setColor(Color.BLACK);
        g.setFont(new Font("微软雅黑",Font.BOLD,20));
        g.drawString("分数:"+score, 20, 30);
        g.drawImage(lifeimage.getImage(),20,40,null);
        g.drawString("X "+hero.getLife().toString(), 55, 60);

        g.drawImage(boomimage.getImage(),300,30,null);
        g.drawString(" X "+boom.getCount().toString(), 330,50);

        //画状态图
        switch (state) {
            case START:
                g.drawImage(startImage, 0, 0, null);
                //画最高分玩家信息
                g.setColor(Color.WHITE);
                g.setFont(new Font("宋体",Font.BOLD,20));
                g.drawString("历史最高分数:"+maxsore, 20, 30);
                break;
            case PAUSE:
                g.drawImage(pauseImage, 0, 0, null);
                break;
            case GAMEOVER:
                g.drawImage(gameoverImage, 0, 0, null);
                g.setColor(Color.WHITE);
                g.setFont(new Font("宋体",Font.BOLD,20));
                g.drawString("本次得分为:"+score, 20, 30);
                break;
        }
    }

    //实现飞行物的移动
    public void flyMove() {
        //敌人移动
        for (int i = 0; i < enemies.size(); i++) {
            FlyObject a = enemies.get(i);
            a.step();
            //到达窗口的最下面 删除越界的敌人
            if(a.getY()>PlaneFrame.HEIGHT) {
                enemies.remove(i);
            }
        }
        //子弹移动
        for (int i = 0; i < bullets.size(); i++) {
            Bullet b = bullets.get(i);
            b.step();
            //到达窗口的最上面 删除越界的子弹
            if(b.getY()<b.getHeight()) {
                bullets.remove(i);
            }
        }
        //boss机移动
        for (int i = 0; i < bossplane.size(); i++) {
            Boss bs = bossplane.get(i);
            bs.step();
        }
        //boss机子弹移动
        for (int i = 0; i < bullet2s.size(); i++) {
            FlyObject b2 = bullet2s.get(i);
            b2.step();
            //到达窗口的最下面 删除越界的敌人
            if(b2.getY()>PlaneFrame.HEIGHT) {
                bullet2s.remove(i);
            }
        }
    }

    //随机产生敌人 小敌机产生的概率最高，其次是大敌机  小蜜蜂产生的概率是最低
     private  int emcount =1;//统计产生的敌人数量
     private  int flage = 1;//避免生成2架BOSS机
    public void addEnemy() {
        //随机数[0,30)
        //如果随机数是在[0,15] 则产生小敌机   16/30
        //如果随机数是在[16,25] 则产生大敌机   10/30
        //如果随机数是在[26,30)则产生小蜜蜂  4/30
        //如果得分每1000分产生一架boss机
        Random random = new Random();
        int x = random.nextInt(30);
        if(x>=0 && x<=15) {
            Airplane a = new Airplane();
            //将产生的小敌机 添加到enemeis集合中
            enemies.add(a);
        }else if(x>=16 && x<=25) {
            Bigplane b = new Bigplane();
            enemies.add(b);

        }else {
            Bee bee = new Bee();
            enemies.add(bee);

        }
    }
    //产生boss机
    public  void addBoss(){
        Boss bs = new Boss();
        boolean flage =true;
        //只允许当前产生一架BOSS机
        for(int i = 0;i<bossplane.size();i++){
            if(bossplane.get(i).isLife())
                flage = false;
        }
        if(flage==true){
            bossplane.add(bs);
            flage=true;
        }

    }


    //产生子弹
    public void addBullet() {
        int w = hero.getWidth()/4;//英雄机宽度的1/4
        if(hero.getDoubleFire()>0) {
            //双倍火力
            int x1 = hero.getX()+w;
            int x2 = hero.getX()+3*w;
            int y = hero.getY()-15;
            Bullet b1 = new Bullet(x1, y);
            Bullet b2 = new Bullet(x2, y);
            bullets.add(b1);
            bullets.add(b2);
            //每一次双倍火力 火力值减2
            hero.subDoublefire();
        }else {
            //单倍火力
            //子弹的x坐标：英雄机的x坐标+英雄机的宽度1/2
            int x = hero.getX()+w*2;
            int y = hero.getY()-15;//固定值越大 子弹离英雄机越远
            Bullet b = new Bullet(x, y);
            bullets.add(b);
        }
    }
    //产生boss子弹
    public  void addBullet2(){

        for (int i = 0; i < bossplane.size(); i++) {
            if(bossplane.get(i).isLife()){
                int w = bossplane.get(i).getWidth()/4;//boss机宽度的1/4

                int x1 = bossplane.get(i).getX()+w*2;

                int y1 = bossplane.get(i).getY()+215;
                Bullet2 b1 = new Bullet2(x1,y1);
                bullet2s.add(b1);
            }
        }



    }

    //子弹与敌人碰撞后的 游戏内容
    public void shootAction() {
        //所有敌人和子弹 两两检测碰撞
        //遍历小敌人集合
        for (int i = 0; i < enemies.size(); i++) {
                //遍历子弹集合
                for (int j = 0; j < bullets.size(); j++) {
                    Bullet b = bullets.get(j);
                    FlyObject enemy = enemies.get(i);
                    if (b.isLife() && enemy.isLife() && b.shoot(enemy)) {//撞上了 前提都是活着的
                        b.goDead();//子弹去死
                        enemy.goBomb();//其他小敌人改为爆炸状态
                        //instanceof :判断 一个对象 是否属于 某种类型
                        if (enemy instanceof Airplane) {
                            score += 3;
                        }
                        if (enemy instanceof Bigplane) {
                            score += 5;
                        }
                        if (enemy instanceof Bee) {
                            Bee bee = (Bee) enemy;
                            int awardType = bee.getAwardType();
                            switch (awardType) {
                                case 1:
                                    hero.addLife();//hero.setLife(hero.getLife()+1);
                                    break;
                                case 2:
                                    hero.addDoubleFire();
                                    break;

                            }
                        }
                    }
                }
            }

        //遍历BOSS机集合
        for (int i1 = 0; i1 < bossplane.size(); i1++){
            for (int j = 0; j < bullets.size(); j++){
                Bullet b = bullets.get(j);
                Boss bs = bossplane.get(i1);
                if(b.isLife() && bs.isLife() &&b.shoot(bs)){
                    b.goDead();//子弹死亡
                    bs.shoted();//boss机扣血
                    if(bs.getLife().intValue()<=0){
                        bs.goBomb();
                        boom.count++;
                    }
                }
            }

        }






    }





    //英雄机与敌人碰撞后的 游戏内容
    public void hitAction() {
        for (int i = 0; i < enemies.size(); i++) {
            FlyObject enemy = enemies.get(i);
            if(hero.isLife() && enemy.isLife() && hero.hit(enemy)) {
                enemy.goBomb();//敌人要爆炸
                hero.subLife();//英雄机减命
                if(hero.getLife()<=0) {
                    state = GAMEOVER;
                }
            }
        }
    }

    //英雄机与BOSS机子弹碰撞后
    public void hitBullet(){
        for (int i = 0; i < bullet2s.size(); i++) {
            FlyObject b2 = bullet2s.get(i);
            if(hero.isLife() && b2.isLife() && hero.hit(b2)) {
                b2.goDead();//子弹消失
                hero.subLife();//英雄机减命
                if(hero.getLife()<=0) {
                    state = GAMEOVER;
                }
            }
        }
    }






    //使用BOOM炸弹后清空在场的除了BOSS机所有敌人
    public void useBOOM(){

            boom.count--;
            enemies.clear();

    }

    //自定义方法：实现移动
    private int count = 0;//计数
    public void move() {
        new Thread() {
            public void run() {
                while(true) {
                    count++;
                    if(state == RUNNING) {

                        //1.改坐标
                        backgroundy++;
                        if(backgroundy>=0) {
                            backgroundy = -5400;
                        }
                        flyMove();
                        //实现每隔400毫秒 随机产生敌人count=1 10   count=50 500  1000（count=100）
                        if(count % 40 ==0) {
                            addEnemy();

                        }
                        //每隔5000毫秒 产生BOSS机
                        if(count % 500 == 0){
                            addBoss();
                        }
                        //实现每隔400毫秒 产生子弹
                        if(count % 18 ==0) {
                            addBullet();
                            addBullet2();
                        }
                        //子弹与敌人碰撞
                        shootAction();
                        try{
                            Thread.sleep(20);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }

                        //英雄机与敌人碰撞
                        hitAction();
                        try{
                            Thread.sleep(20);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                        hitBullet();
                    }
                    //2.重画
                    repaint();
                    //3.休眠
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }


    @Override
    public void mouseClicked(MouseEvent e) {
        // 鼠标点击：开始状态转换为运行状态
        if(state == START) {state = RUNNING;}
        if(state == PAUSE) {state = RUNNING;}
        //鼠标点击：结束状态转换为开始状态
        if(state == GAMEOVER) {
            //在下一轮游戏开始之前 保存游戏记录
            state = START;
            //开始下一次游戏之前 对象恢复到初始情况
            if(score>=maxsore){
                maxsore = score;
            }
            score = 0;
            backgroundy = -5400;
            hero = new Hero();
            bullets.clear();//清空集合
            enemies.clear();
            bullet2s.clear();
            bossplane.clear();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

            if(e.getKeyCode()==KeyEvent.VK_UP){
                if(hero.y.intValue() >= 10)
                hero.heromove(0,-12);
            }
        if(e.getKeyCode()==KeyEvent.VK_DOWN){
            if(hero.y.intValue() <= 430)
            hero.heromove(0,12);
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            if(hero.x.intValue()>=10)
            hero.heromove(-12,0);
        }
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            if(hero.x.intValue()<=280)
            hero.heromove(12,0);
        }
        if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
            if(state == RUNNING) {
                state = PAUSE;
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_SPACE){
            if(boom.count.intValue()>0)
           useBOOM();
        }



    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
