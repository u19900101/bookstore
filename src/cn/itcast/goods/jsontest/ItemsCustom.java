package cn.itcast.goods.jsontest;

import cn.itcast.ssm.controller.validate.ValidateGroup1;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 
 * <p>Title: ItemsCustom</p>
 * <p>Description: 商品信息的扩展类</p>
 * <p>Company: www.itcast.com</p> 
 * @author	传智.燕青
 * @date	2015-4-13下午3:24:05
 * @version 1.0
 */
public class ItemsCustom {
    private Integer id;

    /* @Size(min=1,max = 30,message = "items.t_name.length.error")*/
    @Size(min=1,max = 30,message = "请输入1到30个字符的商品名称",groups = {ValidateGroup1.class})
    private String t_name;

    private Float price;

    private String pic;

    /* @NotNull(message = "items.createtime.isNUll")*/
    @NotNull(message = "请输入商品的生产日期")
    private Date createtime;

    private String detail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return t_name;
    }

    public void setName(String t_name) {
        this.t_name = t_name == null ? null : t_name.trim();
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }
}
