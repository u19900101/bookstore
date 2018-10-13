<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>cartlist.jsp</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	  <script src="<c:url value='/js/jquery-1.4.4.min.js'/>"></script>
	<%--  <script src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>--%>
	  <script src="<c:url value='/js/round.js'/>"></script>

	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/cart/list.css'/>">

  <script type="text/javascript">
	  $(function(){
		  showTotal(); //一加载就开始显示
		  $("#selectAll").click(function(){
			  var bool = $("#selectAll").attr("checked");
			  setItemCheckBox(bool);
			  setJiesuan(bool);
			  showTotal();
		  });

		  $(":checkbox[name=checkboxBtn]").click(function(){
			  var all=$(":checkbox[name=checkboxBtn]").length;
			  var selected=$(":checkbox[name=checkboxBtn][checked=true]").length;

			  if(all==selected){
				  $("#selectAll").attr("checked",true);
				  setJiesuan(true);
			  }else if(selected==0){
				  $("#selectAll").attr("checked",false);
				  setJiesuan(false);
			  }else{
				  $("#selectAll").attr("checked",false);
				  setJiesuan(true);
			  }
			  showTotal();
		  });

		  $(".jian").click(function(){
			  var cartItemArray=$(this).attr("Id").substring(0,32);
			  var quantity=Number($("#"+cartItemArray+"Quantity").val());
			  if(quantity==1||quantity==0){
					  itemDeletelocation(cartItemArray);
			  }else{
				  updateQuantity(cartItemArray,quantity-1);
			  }
		  });

		  $(".jia").click(function(){

			  var cartId=$(this).attr("id").substring(0,32);
			  var quantity=Number($("#"+cartId+"Quantity").val());
			  //alert(quantity);
			  updateQuantity(cartId,quantity+1);
		  });

		  $(".deleteitem").click(function() {
			  var cartItemArray = $(this).attr("id");
			  itemDeletelocation(cartItemArray);
		  });
	  })

	  function myfunction(){
		  $(".quantity").each(function(){

			  var cartId=$(this).attr("id").substring(0,32);
			  var quantity=$(this).val();
			 // alert(quantity);
			  if(quantity==0||quantity==null||quantity.trim()==""){
				  //内有判断是否真的删除
				  itemDeletelocation(cartId);
				  updateQuantity(cartId,1);
			  }else{
				  updateQuantity(cartId,quantity);
			  }
		  });
	  }

	  function updateQuantity(cartId,quantity){//更新修改后再查询显示
		  //alert("进入updateQuantity");

		  var cartitem = new Object();
		  cartitem.cartitemid=cartId;
		  cartitem.quantity=quantity;
		  $.ajax({
			  type:'post',
			  url:'${pageContext.request.contextPath }/cartitem/updateQuantity.action',
			  contentType:'application/json;charset=utf-8',

			  //JSON.stringify(Object)能将对象转化为json串
			  data: JSON.stringify(cartitem),
			  success:function(data) {
				//  1. 修改数量
				  $("#" + cartId + "Quantity").val(data.quantity);
				//  2. 修改小计
				  $("#" + cartId + "Subtotal").text(data.subtotal);
				//  3. 重新计算总计
				  showTotal();
			  }
		  });
	  }
	  function setJiesuan(bool){
		  if(bool){
			  $("#jiesuan").removeClass("kill").addClass("jiesuan");
			  $("#jiesuan").unbind("click");//撤消当前元素止所有click事件
		  }else{
			  $("#jiesuan").removeClass("jiesuan").addClass("kill");
			  $("#jiesuan").click(function() {return false;});
		  }
	  }

	  function setItemCheckBox(bool){
		  $(":checkbox[name=checkboxBtn]").attr("checked",bool);
	  }
	  function showTotal(){
		  var total=0;

		  $(":checkbox[name=checkboxBtn][checked=true]").each(function() {
			  var id=$(this).val();
			  var subtotal=$("#"+id+"Subtotal").text();//这样才得到了值
			  total+=Number(subtotal);
		  });

		  $("#total").text(round(total, 2));
	  }

	  function batchDelete(){
		  var cartItemArray=new Array();
		  $(":checkbox[name=checkboxBtn][checked=true]").each(function() {
			  cartItemArray.push($(this).val());
		  });
		  itemDeletelocation(cartItemArray);
	  }


	  function itemDeletelocation(cartItemArray){
		  if(confirm("您是否真要删除该条目？")) {
			  location="${pageContext.request.contextPath }/cartitem/batchDelete.action?cartitemids="+cartItemArray;
		  }
	  }


	  function loadCartItemByIds(){
		  var cartItemArray=new Array();
		  $(":checkbox[name=checkboxBtn][checked=true]").each(function() {
			  cartItemArray.push($(this).val());
		  });
		  var total=$("#total").text();
		  location="${pageContext.request.contextPath}/cartitem/jiesuan.action?cartitemids="+cartItemArray+"&total="+total;
	  }
  </script>
  </head>
  <body>
  <c:choose>
	  	<c:when test="${empty cartItemList}">
			<table width="95%" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td align="right">
						<img align="top" src="<c:url value='/goods/images/icon_empty.png'/>"/>
					</td>
					<td>
						<span class="spanEmpty">您的购物车中暂时没有商品</span>
					</td>
				</tr>
			</table>  
	  	</c:when>
<c:otherwise>	
	<table width="95%" align="center" cellpadding="0" cellspacing="0">
		<tr align="center" bgcolor="#efeae5">
			<td align="left" width="50px">
				<input type="checkbox" id="selectAll" checked="checked"/><label for="selectAll">全选</label>
			</td>
			<td colspan="2">商品名称</td>
			<td>单价</td>
			<td>数量</td>
			<td>小计</td>
			<td>操作</td>
		</tr>
	
	<c:forEach items="${cartItemList}" var ="cartItem">
		<tr align="center">
			<td align="left">
				<input value="${cartItem.cartitemid}" type="checkbox" name="checkboxBtn" checked="checked"/>
			</td>
			<td align="left" width="70px">
				<a class="linkImage" href="<c:url value='/book/findByBid.action?bid=${cartItem.book.bid}'/>">
					<img border="0" width="54" align="top" src="<c:url value='/${cartItem.book.image_b}'/> "/></a>
			</td>
			<td align="left" width="400px">
			    <a href="<c:url value='/book/findByBid.action?bid=${cartItem.book.bid}'/>"><span>${cartItem.book.bname}</span></a>
			</td>
			<td><span>&yen;<span class="currPrice" >${cartItem.book.currPrice}</span></span></td>
			<td>
				<a class="jian" id="${cartItem.cartitemid}Jian" ></a>
					<input class="quantity"  id="${cartItem.cartitemid}Quantity" oninput="myfunction()"
						   onkeyup="value=value.replace(/[^\d]/g,'')" type="text"
						   value="${cartItem.quantity}" style="width: 33px; ">
				<a class="jia" id="${cartItem.cartitemid}Jia"></a>
			</td>
			<td width="100px">
				<span class="price_n">&yen;<span class="subTotal" id="${cartItem.cartitemid}Subtotal">${cartItem.subtotal}</span></span>
			</td>
			<td>
				<a id="${cartItem.cartitemid}" class="deleteitem">删除</a>
			</td>
		</tr>
	</c:forEach>
	
		<tr>
			<td colspan="4" class="tdBatchDelete">
				<a href="javascript:batchDelete();">批量删除</a>
			</td>
			<td colspan="3" align="right" class="tdTotal">
				<span>总计：</span><span class="price_t">&yen;<span id="total"></span></span>
			</td>
		</tr>
		
		<tr>
			<td colspan="7" align="right">
				<a href="javascript:loadCartItemByIds();" id="jiesuan" class="jiesuan">
				</a>
			</td>
		</tr>
	</table>
  	</c:otherwise>
  </c:choose>

  </body>
</html>
