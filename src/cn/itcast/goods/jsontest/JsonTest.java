package cn.itcast.goods.jsontest;

import cn.itcast.goods.ssm.po.CartitemJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * <p>Title: JsonTest</p>
 * <p>Description: json交互测试</p>
 * <p>Company: www.itcast.com</p> 
 * @author	传智.燕青
 * @date	2015-4-14下午3:54:32
 * @version 1.0
 */
@Controller
public class JsonTest {

	@RequestMapping("/test")
	public void testAction(){
		System.out.println("kkk");
	}
	//请求json串(商品信息)，输出json(商品信息)
	//@RequestBody将请求的商品信息的json串转成itemsCustom对象
	//@ResponseBody将itemsCustom转成json输出
	@RequestMapping(value={"/requestJson"},method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody ItemsCustom requestJson(@RequestBody ItemsCustom itemsCustom){
	/*public ItemsCustom requestJson(ItemsCustom itemsCustom){*/
		//@ResponseBody将itemsCustom转成json输出
		System.out.println(itemsCustom);
		return itemsCustom;
	}

	@RequestMapping(value={"/updateQuantity"},method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody CartitemJson updateQuantity(@RequestBody CartitemJson cartitemJson){
		//@ResponseBody将itemsCustom转成json输出
		System.out.println(cartitemJson);
		return cartitemJson;
	}
	//请求key/value，输出json
	@RequestMapping("/responseJson")
	public @ResponseBody ItemsCustom responseJson(ItemsCustom itemsCustom){
		
		//@ResponseBody将itemsCustom转成json输出
		return itemsCustom;
	}



}
