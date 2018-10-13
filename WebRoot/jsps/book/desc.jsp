<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>图书详细</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/pager/pager.css'/>" />
    <script type="text/javascript" src="<c:url value='/jsps/pager/pager.jsp'/>"></script>
	<script src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/book/desc.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/cart/list.css'/>">

	  <script src="<c:url value='/jsps/js/book/desc.js'/>"></script>

	  <script type="text/javascript">
		  $(function(){
		   $(".jian").click(function(){
			  var quantity=Number($("#Quantity").val());
			  if(quantity==1||quantity==0||quantity==null){
				  if(confirm("您是否要返回？")) {
					  //返回到图书列表页面
					  location="${pageContext.request.contextPath }/jsps/body.jsp";
				  }
			  }else{
				  $("#Quantity").val(quantity-1);
			  }
		  })

		  $(".jia").click(function(){
			  var quantity=Number($("#Quantity").val());
			  $("#Quantity").val(quantity+1);
		  })
	  })
	  </script>
  </head>
  
  <body>
  <div class="divBookName">${book.bname }</div>
  <div>
    <img align="top" src="<c:url value='/${book.image_w }'/>" class="img_image_w"/>
    <div class="divBookDesc">
	    <ul>

	    	<li>商品编号：${book.bid }</li>
	    	<li>传智价：<span class="price_n">&yen;${book.currPrice }</span></li>
	    	<li>定价：<span class="spanPrice">&yen;${book.price }</span>　折扣：<span style="color: #c30;">${book.discount }</span>折</li>
	    </ul>
		<hr class="hr1"/>
		<table>
			<tr>
				<td colspan="3">
					作者：${book.author } 著
				</td>
			</tr>
			<tr>
				<td colspan="3">
					出版社：${book.press }
				</td>
			</tr>
			<tr>
				<td colspan="3">出版时间：${book.publishtime }</td>
			</tr>
			<tr>
				<td>版次：${book.edition }</td>
				<td>页数：${book.pageNum }</td>
				<td>字数：${book.wordNum }</td>
			</tr>
			<tr>
				<td width="180">印刷时间：${book.printtime }</td>
				<td>开本：${book.booksize } 开</td>
				<td>纸张：${book.paper }</td>
			</tr>
		</table>
		<div class="divForm">
			<form id="form1" action="<c:url value='/cartitem/addCartItem.action?bid=${book.bid }'/>" method="post">

				我要买：
				<a class="jian"></a>
				<input class="quantity"  id="Quantity" oninput="myfunction()"
					   onkeyup="value=value.replace(/[^\d]/g,'')" type="text"
					   value="1" style="width: 33px; " name="quantity">
				<a class="jia"></a>
				件
			</form>

  			<a  id="btn" href="javascript:$('#form1').submit();"></a>

	</div>
  </div>
  </div>
  </body>
</html>
