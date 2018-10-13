package cn.itcast.goods.ssm.service;

import cn.itcast.goods.pager.PageBean;
import cn.itcast.goods.ssm.po.Book;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

/**
 * Created by liupanbangbangda on 2018/9/12.
 */
public interface BookService {

//    public PageBean<Book> findByAuthor(Book book, int pcc) throws SQLException, UnsupportedEncodingException;
//
//    public PageBean<Book> findByBname(Book book, int pcc) throws SQLException, UnsupportedEncodingException;
//
//    public PageBean<Book> findByPress(Book book, int pcc) throws SQLException, UnsupportedEncodingException;

  //  public PageBean<Book> findByCombination(Book supersearch, int pcc) throws SQLException;

    PageBean<Book> findByCriteria( int pc, Book book) throws SQLException, UnsupportedEncodingException;
  //  String methodName,
  /*  public PageBean<Book> findByCategory(Book book, int pcc) throws SQLException, UnsupportedEncodingException;
*/

}

