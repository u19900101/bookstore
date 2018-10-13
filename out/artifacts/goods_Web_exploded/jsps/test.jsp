<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>菜鸟教程(runoob.com)</title>
</head>
<body>

	<a href="<c:url value='/order/afterPayment.action?oid=${order.oid }'/>">kkk</a>
	<p>在文本框中尝试输入触发函数。</p>
		<input type="text" id="myInput" oninput="myFunction()">
	<p id="demo"></p>
<script>
	function myFunction() {
	    var x = document.getElementById("myInput").value;
	    alert("ssssss  is  "+x);
	   // document.getElementById("demo").innerHTML = "你输入的是: " + x;
	}
</script>



</body>
</html>