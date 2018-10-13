<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>添加分类</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>
<script type="text/javascript">

$(function(){	
	$("#submit").submit(function(){
		var bool=true;
		if(!validatecname()){
			bool=false;
		}
		return bool;
	});
	
	$(".input").blur(function(){//焦点移除时开始校验	
		var id=$(this).attr("id");//得到当前input框的name值
		var funcName="validate"+id+"()";
		eval(funcName);
	});	
});

function validatedesc(){
	var bool=true;
	//alert("分类描述不能为空！");
	if(!$("#descri").val()) {
		$("#descriError").text("描述不能为空！");
		bool=false;
	}else {
		$("#descriError").text("");
	}
	return bool;
}
function validatecname(){
	var bool=true;
	var cname=$("#cname").val();
	if(!$("#cname").val()) {
		$("#cnameError").text("分类名不能为空！");
		bool=false;
	}else {
		$("#cnameError").text("");
	}
	
	$.ajax({
			url:"/goods/AdminCategoryServlet",//要请求的servlet
			data:{method:"ajaxValidateAdminCategory", cname:cname},//给服务器的参数
			type:"POST",
			dataType:"json",
			async:false,//是否异步请求，如果是异步，那么不会等服务器返回，我们这个函数就向下运行了。
			cache:false,
				success:function(result) {
					if(!result) {//如果校验失败
						$("#cnameError").text("存在同名一级分类，请修改分类名称！！！");
						bool=false;
					}	
				}
			});
	return bool;
}

</script>
<style type="text/css">
	body {background: rgb(254,238,189);}
</style>
  </head>
  
  <body>
    <h3>添加1级分类</h3>
    <h1></h1>
    <p style="font-weight: 900; color: red">${msg }</p>
    <form action="<c:url value='/AdminCategory/addOneLevel.action'/>" method="post" id="submit">  <!-- onsubmit="return checkForm()" -->
    	分类名称：<input type="text" name="cname" id="cname" class="input" /> 
    	<label class="labelError" id="cnameError"></label> <br/>
    	
    	分类描述：<textarea rows="5" cols="50" name="descri" id="descri" id="input"></textarea>
		<label class="labelError" id="descriError"></label> <br/>
		
    	<input type="submit" value="添加一级分类" />
    	<input type="button" value="返回" onclick="history.go(-1)"/>
    </form>
  </body>
</html>
