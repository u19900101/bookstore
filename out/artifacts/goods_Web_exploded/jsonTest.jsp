<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>json交互测试</title>
	<script src="<c:url value='/js/jquery-1.4.4.min.js'/>"></script>
<script type="text/javascript">
//请求json，输出是json
function requestJson(){
	$.ajax({
		type:'post',
		url:'${pageContext.request.contextPath }/requestJson.action',
		contentType:'application/json;charset=utf-8',
		//数据格式是json串，商品信息
		data:'{"name":"手机","price":999}',
		success:function(data){//返回json结果
			alert(data.name);
		}
		
	});
}
//请求key/value，输出是json
function responseJson(){
	
	$.ajax({
		type:'post',
		url:'${pageContext.request.contextPath }/responseJson.action',
		//请求是key/value这里不需要指定contentType，因为默认就 是key/value类型
		//contentType:'application/json;charset=utf-8',
		//数据格式是json串，商品信息
		data:'name=手机&price=999',
		success:function(data){//返回json结果
			alert(data.price);
		}
		
	});
}

function updateQuantity(){//更新修改后再查询显示
	var item = new Object();
	item.cartitemid="kkkk";
	item.quantity=999;
	$.ajax({
		type:'post',
		url:'${pageContext.request.contextPath }/updateQuantity.action',
		contentType:'application/json;charset=utf-8',
		//data:'{"cartitemid":"cartId","quantity":999}',
		/*data:{
			cartitemid:i,
			quantity:n
		},*/
		data: JSON.stringify(item),
		success:function(data) {
			alert(JSON.stringify(data));
		}
	});
}
</script>
</head>
<body>
<input type="button" onclick="updateQuantity()" value="访问购物车"/>
<input type="button" onclick="requestJson()" value="请求json，输出是json"/>
<input type="button" onclick="responseJson()" value="请求key/value，输出是json"/>
</body>
</html>