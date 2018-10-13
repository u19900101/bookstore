package cn.itcast.goods.jsontest;

import cn.itcast.ssm.controller.validate.ValidateGroup1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
//为了对url进行分类管理 ，可以在这里定义根路径，最终访问url是根路径+子路径
//比如：商品列表：/items/queryItems.action
@RequestMapping("/items")
public class ItemsController {

	@Autowired
	private ItemsService itemsService;

	@Autowired
	private ItemsMapper itemsMapper;

	// 商品分类
	//itemtypes表示最终将方法返回值放在request中的key
	@ModelAttribute("itemtypes")
	public Map<String, String> getItemTypes() {

		Map<String, String> itemTypes = new HashMap<String, String>();
		itemTypes.put("101", "数码");
		itemTypes.put("102", "母婴");

		return itemTypes;
	}

	// 商品查询
	//@RequestMapping("/queryItems")
	@RequestMapping(value="/queryItems",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView queryItems(HttpServletRequest request) throws Exception {
		//测试forward后request是否可以共享

		//System.out.println("进入了controller中.....");
		//System.out.println(request.getParameter("id"));

		// 调用service查找 数据库，查询商品列表
		List<ItemsCustom> itemsList = itemsService.findItemsList(null);
		
		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		// 相当 于request的setAttribut，在jsp页面中通过itemsList取数据
		modelAndView.addObject("itemsList", itemsList);

		// 指定视图
		// 下边的路径，如果在视图解析器中配置jsp路径的前缀和jsp路径的后缀，修改为
		// modelAndView.setViewName("/WEB-INF/jsp/items/itemsList.jsp");
		// 上边的路径配置可以不在程序中指定jsp路径的前缀和jsp路径的后缀
		modelAndView.setViewName("/test_image/jsp/items/itemsList");

		return modelAndView;

	}

	
	@RequestMapping(value="/editItems",method={RequestMethod.POST,RequestMethod.GET})
	//@RequestParam里边指定request传入参数名称和形参进行绑定。
	//通过required属性指定参数是否必须要传入
	//通过defaultValue可以设置默认值，如果id参数没有传入，将默认值和形参绑定。
	public String editItems(Model model,@RequestParam(value="id",required=true) Integer items_id)throws Exception {
		
		//调用service根据商品id查询商品信息
		Items itemsCustom = itemsMapper.selectByPrimaryKey(items_id);
		/*if(itemsCustom==null){
			throw new CustomerException("修改的商品信息不存在......");
		}*/
		//通过形参中的model将model数据传到页面
		//相当于modelAndView.addObject方法
		model.addAttribute("itemsCustom", itemsCustom);
		
		return "test_image/jsp/items/editItems";
	}


	//查询商品信息，输出json
	///itemsView/{id}里边的{id}表示占位符，通过@PathVariable获取占位符中的参数，
	//如果占位符中的名称和形参名一致，在@PathVariable可以不指定名称
	@RequestMapping("/itemsView/{id}")
	 public @ResponseBody
	 ItemsCustom itemsView(@PathVariable("id") Integer id)throws Exception{

		//调用service查询商品信息
		ItemsCustom itemsCustom = itemsService.findItemsById(id);

		return itemsCustom;

	}


	@RequestMapping("/Testkk")
	public ModelAndView Testkk(MultipartFile item_pic,Items itemsCustom,BindingResult bindingResult,
					   Model model,HttpServletRequest request) throws Exception {
		ModelAndView modelAndView=new ModelAndView();
		if(bindingResult.hasErrors()){
			List<ObjectError> allErrors=bindingResult.getAllErrors();
			for (ObjectError objectError:allErrors)
			{
				System.out.println(objectError.getDefaultMessage());
			}

			model.addAttribute("allErrors",allErrors);

			modelAndView.setViewName("/test_image/jsp/items/editItems");
			return modelAndView;
		}

		//原始名称
		String originalFilename = item_pic.getOriginalFilename();
		//上传图片
		if(item_pic!=null && originalFilename!=null && originalFilename.length()>0){
			//存储图片的物理路径
			String pic_path = "D:\\java\\idea\\springmvc\\goods\\WebRoot\\jsps\\test_image\\";
			//新的图片名称
			String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
			//新图片
			File newFile = new File(pic_path+newFileName);
			//将内存中的数据写入磁盘
			item_pic.transferTo(newFile);
			//将新图片名称写到itemsCustom中
			itemsCustom.setPic(newFileName);
		}
		itemsMapper.updateByPrimaryKeySelective(itemsCustom);
		//重定向到商品查询列表
		modelAndView.setViewName("/test_image/jsp/items/itemsList");
		List<ItemsCustom> itemsList = itemsService.findItemsList(null);
		modelAndView.addObject("itemsList", itemsList);
		return modelAndView;
	}
}
