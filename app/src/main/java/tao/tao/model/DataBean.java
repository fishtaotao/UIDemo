package tao.tao.model;

/**
 * Author：wjt on 2018/4/23 0023 15:22
 */


public class DataBean {

    public int animType;
    public String animName;

    public int getAnimType() {
        return animType;
    }

    public DataBean(int animType, String animName) {
        this.animType = animType;
        this.animName = animName;
    }

    public void setAnimType(int animType) {
        this.animType = animType;
    }

    public String getAnimName() {
        return animName;
    }

    public void setAnimName(String animName) {
        this.animName = animName;
    }
}