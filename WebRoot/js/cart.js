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
        var cartId=$(this).attr("Id").substring(0,32);
        var quantity=Number($("#"+cartId+"Quantity").val());
        //alert(quantity);
        if(quantity==1||quantity==0){
            if(confirm("您是否真要删除该条目？")) {
                location = "/goods/CartItemServlet?method=batchDelete&cartitemids=" + cartId;
            }
        }else{
            updateQuantity(cartId,quantity-1);
        }
    });

    $(".jia").click(function(){

        var cartId=$(this).attr("id").substring(0,32);
        var quantity=Number($("#"+cartId+"Quantity").val());
        alert(quantity);
        updateQuantity(cartId,quantity+1);
    });
});

function myfunction(){
    $(".quantity").each(function(){
        var cartId=$(this).attr("Id").substring(0,32);
        var quantity=$(this).val();
        updateQuantity(cartId,quantity);
    });
};

function updateQuantity(cartId,quantity){//更新修改后再查询显示
    alert("进入updateQuantity");
    $.ajax({
       /* async:false,
        cache:false,*/
        url:'${pageContext.request.contextPath }/cartitem/updateQuantity.action',
        data:{cartitemid:cartId,quantity:quantity},
        type:"POST",
        contentType:'application/json;charset=utf-8',
        success:function(result) {
            //1. 修改数量
            $("#" + cartId + "Quantity").val(result.quantity);
            //2. 修改小计
            $("#" + cartId + "Subtotal").text(result.subtotal);
            //3. 重新计算总计
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
    location="/goods/CartItemServlet?method=batchDelete&cartitemids="+cartItemArray;
}

function loadCartItemByIds(){
    var cartItemArray=new Array();
    $(":checkbox[name=checkboxBtn][checked=true]").each(function() {
        cartItemArray.push($(this).val());
    });
    var total=$("#total").text();
    location="/CartItemServlet?method=loadCartItemByIds&cartitemids="+cartItemArray+"&total="+total;
}