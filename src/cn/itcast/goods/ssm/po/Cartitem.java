package cn.itcast.goods.ssm.po;

import java.math.BigDecimal;

public class Cartitem {
    private String cartitemid;

    private Integer quantity;

    private String bid;

    private String uid;

    private Integer orderby;

    public Book book;

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double subtotal;

    public  double getSubtotal() {
        BigDecimal b1=new BigDecimal(book.getCurrPrice()+"");
        BigDecimal b2=new BigDecimal(quantity+"");
        BigDecimal b3=b1.multiply(b2);
        return b3.doubleValue();
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getCartitemid() {
        return cartitemid;
    }

    public void setCartitemid(String cartitemid) {
        this.cartitemid = cartitemid == null ? null : cartitemid.trim();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid == null ? null : bid.trim();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public Integer getOrderby() {
        return orderby;
    }

    public void setOrderby(Integer orderby) {
        this.orderby = orderby;
    }

    @Override
    public String toString() {
        return "Cartitem{" +
                "bid='" + bid + '\'' +
                ", cartitemid='" + cartitemid + '\'' +
                ", quantity=" + quantity +
                ", uid='" + uid + '\'' +
                ", orderby=" + orderby +
                ", book=" + book +
                ", subtotal=" + subtotal +
                '}';
    }
}