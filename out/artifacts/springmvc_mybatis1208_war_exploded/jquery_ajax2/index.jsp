<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<title>ajax</title> 
<script type="text/javascript" src="<c:url value='/jquery_ajax2/jquery.js'/>"></script>
<script type="text/javascript">
//初始状态
getContent();
getMember();  
window.setInterval(function(){getContent();getMember();},3000);  

//函数
/*function inChat(){
$.ajax({  
  type: "POST",
  url: "member.jsp",  
  success: function(msg){  
  getMember();  
  }  
});  
}  
 
$(document).ready(function(){
	$("#to").click(function send(){
   		$.ajax({  
 	 	type: "POST",  
  		url: "content.jsp",  
  		data: "say="+$("#say").val(),  
  		success: function(msg){ 
  		getContent();  
  		$("#say").val(""); 
  //		alert("请求成功传送！！");
  					}
  			});  
	});
 });*/
function getContent(){
    alert("进入了content中");
    $.ajax({
      type: "POST",
      url: "http://localhost:8080/jquery_ajax2/content.jsp"/*,
      success: function(msg){
      $("#content").html(msg);
      }*/
    });

  alert("执行完了content");
}  
 
function getMember(){  
$.ajax({  
  type: "POST",  
  url: "member.jsp",  
  success: function(msg){  
  $("#member").html(msg);  
  }  
});  
}  
</script> 
</head> 
<body> 
<%--
<div id="content" style="font-size: 12px;overflow-y:auto ;height: 300px;width: 300px;border: 1px solid #CCC;padding: 10px;float: left;"></div>
<div id="member" style="overflow-y:auto ;height: 300px;width: 100px;border: 1px solid #CCC;padding: 10px;"></div> 
<textarea rows="5" cols="35" id="say"></textarea><br/><input  type="button" id="to" value="发送"/> 
--%>


<input onclick="getContent()" type="submit" value="点我">

</body>
</html>  