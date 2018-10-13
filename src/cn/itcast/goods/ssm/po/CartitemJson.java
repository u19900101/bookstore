package cn.itcast.goods.ssm.po;

/**
 * Created by liupanbangbangda on 2018/9/29.
 */
public class CartitemJson {
    private String cartitemid;

    private Integer quantity;

    @Override
    public String toString() {
        return "CartitemJson{" +
                "cartitemid='" + cartitemid + '\'' +
                ", quantity=" + quantity +
                '}';
    }

    public String getCartitemid() {
        return cartitemid;
    }

    public void setCartitemid(String cartitemid) {
        this.cartitemid = cartitemid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
