package cn.itcast.goods.ssm.po;

import java.math.BigDecimal;

public class Book {
    private String bid;

    private BigDecimal currPrice;

    private BigDecimal discount;

    private String press;

    private String publishtime;

    private Integer edition;

    private Integer pageNum;

    private Integer wordNum;

    private String printtime;

    private Integer booksize;

    private String paper;

    private String cid;

    private String image_w;

    private String image_b;

    private Integer orderby;
    private String bname;
    private String price;
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }



    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    private String author;


    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public Integer getBooksize() {
        return booksize;
    }

    public void setBooksize(Integer booksize) {
        this.booksize = booksize;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public BigDecimal getCurrPrice() {
        return currPrice;
    }

    public void setCurrPrice(BigDecimal currPrice) {
        this.currPrice = currPrice;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Integer getEdition() {
        return edition;
    }

    public void setEdition(Integer edition) {
        this.edition = edition;
    }

    public String getImage_b() {
        return image_b;
    }

    public void setImage_b(String image_b) {
        this.image_b = image_b;
    }

    public String getImage_w() {
        return image_w;
    }

    public void setImage_w(String image_w) {
        this.image_w = image_w;
    }

    public Integer getOrderby() {
        return orderby;
    }

    public void setOrderby(Integer orderby) {
        this.orderby = orderby;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public String getPaper() {
        return paper;
    }

    public void setPaper(String paper) {
        this.paper = paper;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getPrinttime() {
        return printtime;
    }

    public void setPrinttime(String printtime) {
        this.printtime = printtime;
    }

    public String getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(String publishtime) {
        this.publishtime = publishtime;
    }

    public Integer getWordNum() {
        return wordNum;
    }

    public void setWordNum(Integer wordNum) {
        this.wordNum = wordNum;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", bid='" + bid + '\'' +
                ", currPrice=" + currPrice +
                ", discount=" + discount +
                ", press='" + press + '\'' +
                ", publishtime='" + publishtime + '\'' +
                ", edition=" + edition +
                ", pageNum=" + pageNum +
                ", wordNum=" + wordNum +
                ", printtime='" + printtime + '\'' +
                ", booksize=" + booksize +
                ", paper='" + paper + '\'' +
                ", cid='" + cid + '\'' +
                ", image_w='" + image_w + '\'' +
                ", image_b='" + image_b + '\'' +
                ", orderby=" + orderby +
                ", bname='" + bname + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

}