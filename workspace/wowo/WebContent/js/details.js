var goods = {};
var url=""
var gid=""
var loginFlag = false;
var uname = ""
/**
 * 判断是否有用户已经登录
 */
$(function() {
	$.post("wowoServlet",{op:"userSession"},function(data){
		if(data!=null){
			loginFlag = true;
			uname = data.uname;
			$("#loginReg").replaceWith("<span id=\"loginReg\" class=\"Gray7\">Hi,"+uname+"</span>");
		}
	},"json");
	
	
	url=document.location.href;
	gid=url.substring(url.indexOf("?")+1);
	gid = parseInt($.trim(gid));
	
	
	if(gid>0){
		$.post("goodsServlet",{op:"findGoodsByGid",gid:gid},function(data){
			goods=data;
			$("#item-gname").text(data.gname);
			$("#item-des").text(data.des);
			$("#item-price").text("￥"+data.price);
			$("#ari-price").text(data.price);
			$("#ari-wowoprice").text(data.price);
			$("#imgPic").attr("src",data.pic);
			
			$("#pymoney").text("￥"+data.price);
			$("#wowoMoney").text(data.price);
			$("#originalPrice").text(data.price*2);
		},"json")
	}
})
//shopcart.html



function purchase() {
	
	
	
	var title = goods.gname;
	var price = goods.price;
	
	$.post("cartServlet",{op:"toCart",uname:uname,gid:gid,title:title,price:price},function(data){
		data = $.trim(data)
		if(data==0){
			alert("您还没有登录，请登录~");
			location.href="login.html";
		}else{
			location.href="shopcart.html";
		}
	})
}
