$(function(){
	$(".labelError").each(function(){
		showError($(this));
	});
	
	$("#submit").hover(
			function(){
				$("#submit").attr("src","/goods/images/regist2.jpg");
			},
			function(){
				$("#submit").attr("src","/goods/images/regist1.jpg");
			}
	);
	
	/*输入框得到焦点，隐藏信息
	 * */
	$(".input").focus(function(){
		var labelId=$(this).attr("id")+"Error";//通过label的class找到所有的 id，然后赋值
		$("#"+labelId).text("");//先清空label内容
		showError($("#"+labelId));//再隐藏  没有  内容的
	});
	
	$(".input").blur(function(){//焦点移除时开始校验
		var id=$(this).attr("id");//得到当前input框的name值
		var funcName="validate"+id.substring(0, 1).toUpperCase()+id.substring(1)+"()";
		eval(funcName);
	});
	
	$("#registForm").submit(function(){
		 
		var bool=true;
		 
		if(!validateLoginname()){
			bool=false;
			//return bool;
		}
		if(!validateLoginpass()){
			bool=false;
			//return bool;
		}
		if(!validateReloginpass()){
			bool=false;
			//return bool;
		}
		if(!validateEmail()){
			bool=false;
		//	return bool;
		}
		if(!validateVerifyCode()){
			bool=false;
		//	return bool;
		}
		return bool;
	});
	
});


//用户名校验
function validateLoginname(){
	var id="loginname";
	var value=$("#"+id).val();
	
	if(!value){
			$("#"+id+"Error").text("用户名不能为空");
			showError($("#"+id+"Error")); 	
			return false;	
		}
	
	if(value.length<3||value.length>20){
		$("#"+id+"Error").text("用户名必须为3-20位");
		showError($("#"+id+"Error")); 	
		return false;
	}
	
	/*
	 * 3. 是否注册校验
	 */
	$.ajax({
		url:"/goods/UserServlet",//要请求的servlet
		data:{method:"ajaxValidateLoginname", loginname:value},//给服务器的参数
		type:"POST",
		dataType:"json",
		async:false,//是否异步请求，如果是异步，那么不会等服务器返回，我们这个函数就向下运行了。
		cache:false,
		success:function(result) {
			if(!result) {//如果校验失败
				$("#" + id + "Error").text("用户名已被注册！");
				showError($("#" + id + "Error"));
				return false;
			}
		}
	});
	return true;
}
//密码校验
function validateLoginpass(){
	var id="loginpass";
	var value=$("#"+id).val();
	
	if(!value){
			$("#"+id+"Error").text("密码不能为空");
			showError($("#"+id+"Error")); 	
			return false;
		}
	
	if(value.length<3||value.length>20){
		$("#"+id+"Error").text("密码必须为3-20位");
		showError($("#"+id+"Error")); 	
		return false;
	}
	return true;
}
//确认密码校验
function validateReloginpass(){
	var id="reloginpass";
	var value=$("#"+id).val();
	
	if(!value){
			$("#"+id+"Error").text("密码不能为空");
			showError($("#"+id+"Error")); 	
			return false;
		}
	if(value.length<3||value.length>20){
		$("#"+id+"Error").text("密码必须为3-20位");
		showError($("#"+id+"Error")); 	
		return false;
	}
	if(value!=$("#loginpass").val()){
		$("#"+id+"Error").text("两次输入的密码不一致！！");
		showError($("#"+id+"Error")); 	
		return false;
	}
	return true;
}

//邮箱校验
function validateEmail(){
	var id="email";
	var value=$("#"+id).val();
	
	if(!value){
			$("#"+id+"Error").text("邮箱不能为空");
			showError($("#"+id+"Error")); 	
			return false;
		}
	if(!(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/.test(value))){
		$("#"+id+"Error").text("邮箱命名错误额");
		showError($("#"+id+"Error")); 	
		return false;
	}
	
	/*
	 * 3. 邮箱是否注册校验
	 */
	$.ajax({
		url:"/goods/UserServlet",//要请求的servlet
		data:{method:"ajaxValidateEmail", email:value},//给服务器的参数
		type:"POST",
		dataType:"json",
		async:false,//是否异步请求，如果是异步，那么不会等服务器返回，我们这个函数就向下运行了。
		cache:false,
		success:function(result) {
			if(!result) {//如果校验失败
				$("#" + id + "Error").text("Email已被注册！");
				showError($("#" + id + "Error"));
				return false;
			}
		}
	});
	
	return true;	
}

//验证码校验
function validateVerifyCode(){
	var id="verifyCode";
	var value=$("#"+id).val();
	if(!value){
		$("#"+id+"Error").text("验证码不能为空");
		showError($("#"+id+"Error")); 	
		return false;
	}
	if(value.length!=4){
		$("#"+id+"Error").text("验证码错误！！");
		showError($("#"+id+"Error")); 	
		return false;
	}
	
	/*
	 * 3. 验证码是否正确
	 */
	$.ajax({
		url:"/goods/UserServlet",//要请求的servlet
		data:{method:"ajaxValidateVerifyCode", verifyCode:value},//给服务器的参数
		type:"POST",
		dataType:"json",
		async:false,//是否异步请求，如果是异步，那么不会等服务器返回，我们这个函数就向下运行了。
		cache:false,
		success:function(result) {
			if(!result) {//如果校验失败
				$("#" + id + "Error").text("验证码错误！");
				showError($("#" + id + "Error"));
				return false;
			}
		}
	});
	return true;
}

function showError(ele){
	var text =ele.text();
	if(!text){
		ele.css("display","none");
	}else{
			ele.css("display","");
		}
};

function _hyz(){
	$("#vCode").attr("src","/goods/VerifyCodeServlet?a="+new Date().getTime());
};


