package cn.itcast.goods.ssm.po;

/**
 * Created by liupanbangbangda on 2018/9/19.
 */
public class OrderVo extends  Order{
    int from;
    int to;

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }
}
