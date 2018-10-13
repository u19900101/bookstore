package cn.itcast.goods.ssm.service.impl;

import cn.itcast.goods.pager.PageBean;
import cn.itcast.goods.pager.PageConstants;
import cn.itcast.goods.ssm.mapper.BookMapper;
import cn.itcast.goods.ssm.po.Book;
import cn.itcast.goods.ssm.po.BookVo;
import cn.itcast.goods.ssm.service.BookService;
import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liupanbangbangda on 2018/9/12.
 */
public class BookServiceImpl implements BookService {

    private ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:applicationContext-dao.xml");
    BookMapper bookMapper = (BookMapper) applicationContext.getBean("bookMapper");

    List<Book> beanList=new ArrayList<Book>();

    int ps = PageConstants.BOOK_PAGE_SIZE;//每页记录数

    @Override
    public PageBean<Book> findByCriteria(int pc, Book book) throws SQLException, UnsupportedEncodingException {
        int tr=0;
        BookVo bookVo=new BookVo();
        bookVo.setFrom((pc - 1)*ps);
        bookVo.setTo(ps);
        bookVo.setBook(book);

        tr = bookMapper.selectBookCount(bookVo);
        beanList = bookMapper.selectFromTo(bookVo);
        PageBean<Book> pb = new PageBean<Book>();
        pb.setBeanList(beanList);
        pb.setPc(pc);
        pb.setPs(ps);
        pb.setTr(tr);
        return pb;
    }

    public Book selectByBid(String bid){
        return bookMapper.selectByPrimaryKey(bid);
    }
   /* @Override
    public PageBean<Book> findByCombination(Book supersearch, int pc) throws SQLException {
        List<Expression> exprList=new ArrayList<Expression>();
        exprList.add(new Expression("bname","like","%"+supersearch.getBname() +"%"));
        exprList.add(new Expression("author","like","%"+supersearch.getAuthor()+"%"));
        exprList.add(new Expression("press","like","%"+supersearch.getPress()+"%"));
        return findByCriteria(exprList,pc);
    }*/

   /* @Override
    public Book findByBid(String bid) throws SQLException {
        String sql = "select *from t_book where bid = ?";
       Book book=qr.query(sql, new BeanHandler<Book>(Book.class),bid);

        CategoryDao categoryDao=new CategoryDao();
        book.setCategory(categoryDao.findForBook(book.getCid()));
        return book;
    }*/

   /* @Override
    public void addBook(cn.itcast.goods.book.domain.Book book) throws SQLException {
        String sql = "insert into t_book(bid,bname,author,price,currPrice," +
                "discount,press,publishtime,edition,pageNum,wordNum,printtime," +
                "booksize,paper,cid,image_w,image_b)" +
                " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Object[] params = {book.getBid(),book.getBname(),book.getAuthor(),
                book.getPrice(),book.getCurrPrice(),book.getDiscount(),
                book.getPress(),book.getPublishtime(),book.getEdition(),
                book.getPageNum(),book.getWordNum(),book.getPrinttime(),
                book.getBooksize(),book.getPaper(), book.getCategory().getCid(),
                book.getImage_w(),book.getImage_b()};
        qr.update(sql, params);
    }

    @Override
    public void deleteBook(String bid) throws SQLException {
        String sql="delete from t_book where bid = ?";
        qr.update(sql,bid);
    }

    public void editBook(cn.itcast.goods.book.domain.Book book) throws SQLException {
        String sql = "update t_book set bname=?,author=?,price=?,currPrice=?," +
                "discount=?,press=?,publishtime=?,edition=?,pageNum=?,wordNum=?," +
                "printtime=?,booksize=?,paper=?,cid=? where bid=?";
        Object[] params = {book.getBname(),book.getAuthor(),
                book.getPrice(),book.getCurrPrice(),book.getDiscount(),
                book.getPress(),book.getPublishtime(),book.getEdition(),
                book.getPageNum(),book.getWordNum(),book.getPrinttime(),
                book.getBooksize(),book.getPaper(),
                book.getCid(),book.getBid()};
        qr.update(sql, params);
    }*/
}
