package cn.itcast.goods.ssm.mapper;

import cn.itcast.goods.ssm.po.Book;
import cn.itcast.goods.ssm.po.BookExample;
import cn.itcast.goods.ssm.po.BookVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookMapper {
    int countByExample(BookExample example);

    int deleteByExample(BookExample example);

    int deleteByPrimaryKey(String bid);

    int insert(Book record);

    int insertSelective(Book record);

    List<Book> selectByExample(BookExample example);
    Book selectByPrimaryKey(String bid);
    Book findByBookId(String bid);
    int updateByExampleSelective(@Param("record") Book record, @Param("example") BookExample example);

    int updateByExample(@Param("record") Book record, @Param("example") BookExample example);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);



    int selectBookCountByAuthor(String param);
    int selectBookCountByCid(String cid);
    List<Book> selectFromTo(@Param(value = "from")int from,
                            @Param(value = "to")int to,
                            @Param(value = "cid")String cid);



    int selectBookCount(BookVo bookVo);
    List<Book> selectFromTo(BookVo bookVo);
}