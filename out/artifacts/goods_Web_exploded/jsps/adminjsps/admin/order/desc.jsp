<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>订单详细</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/order/desc.css'/>">
	 <script src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
<!-- 	<script type="text/javascript">
	
function cancelOrder(){
		var oid=$("#oid").val();
		location="/goods/OrderServlet?method=canelOrderByOid&oid="+oid;
		alert("取消成功！！");
	}
</script> -->

  </head>
<body>

	<input type="hidden" id="oid" value="${order.oid}"/>
	<div class="divOrder" >
		<span>订单号： ${order.oid}
			
<c:choose>
	<c:when test="${order.status eq 1 }">(等待付款)</c:when>
	<c:when test="${order.status eq 2 }">(准备发货)</c:when>
	<c:when test="${order.status eq 3 }">(等待确认)</c:when>
	<c:when test="${order.status eq 4 }">(交易成功)</c:when>
	<c:when test="${order.status eq 5 }">(订单已取消)</c:when>
	<c:when test="${order.status eq 6 }">(已退款)</c:when>
	<c:when test="${order.status eq 7 }">(确认收款)</c:when>
</c:choose>	

		　　　下单时间：${order.ordertime}</span>
	</div>
	<div class="divContent">
		<div class="div2">
			<dl>
				<dt>收货人信息</dt>
				<dd>${order.address}</dd>
			</dl>
		</div>
		<div class="div2">
			<dl>
				<dt>商品清单</dt>
				<dd>
					<table cellpadding="0" cellspacing="0">
						<tr>
							<th class="tt">商品名称</th>
							<th class="tt" align="left">单价</th>
							<th class="tt" align="left">数量</th>
							<th class="tt" align="left">小计</th>
						</tr>
	
				<c:forEach items="${order.orderItemList }" var="orderItem">
						<tr style="padding-top: 20px; padding-bottom: 20px;">
							<td class="td" width="400px">
								<div class="bookname">
								  <img align="middle" width="70" src="<c:url value='/${ orderItem.book.image_b}'/>"/>
								  <a href="<c:url value='/book/findByBid.action?bid=${orderItem.bid }'/>">${ orderItem.book.bname}</a>
								</div>
							</td>
							<td class="td" >
								<span>&yen;${ orderItem.book.currPrice}</span>
							</td>
							<td class="td">
								<span>${ orderItem.quantity}</span>
							</td>
							<td class="td">
								<span>&yen;${ orderItem.subtotal}</span>
							</td>			
						</tr>
					</c:forEach>	
					</table>
				</dd>
			</dl>
		</div>

		<div style="margin: 10px 10px 10px 550px;">
			<span style="font-weight: 900; font-size: 15px;">合计金额：</span>
			<span class="price_t">&yen;${ order.total}</span><br/>

			<c:url value="/AdminOrder/canelOrderByOid.action?oid=${order.oid }" var="cancelUrl"></c:url>
			<c:url value="/AdminOrder/sendoutOrderByOid.action?oid=${order.oid }" var="sendout"></c:url>
			<c:url value="/AdminOrder/canelOrderByOid.action?oid=${order.oid }" var="payBack"></c:url>

    <c:if test="${btn eq 'lookup' ||btn eq 'sendout' ||btn eq 'cancel' ||btn eq 'payBack'}">
   		<c:if test="${order.status eq 2 }">
			<a href="${sendout}" class="confirm">发货</a><br/>
			<a href="${payBack}" class="cancel">退款</a><br/>
		</c:if>
    </c:if>

		</div>
	</div>
</body>
</html>