package cn.itcast.goods.ssm.po;

/**
 * Created by liupanbangbangda on 2018/9/16.
 */
public class BookVo{
    int from;
    int to;
    Book book;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

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
