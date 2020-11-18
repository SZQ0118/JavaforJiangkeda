package myplane;

public class Boom {
    //初始有10发全屏炸弹
    Integer count = 10;
    public Boom() {}
    public Boom(Integer count) {
        this.count = count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCount() {
        return count;
    }
}
