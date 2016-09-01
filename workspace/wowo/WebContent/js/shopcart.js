
$(function() {
	
	$.post("wowoServlet",{op:"userSession"},function(data){
		if(data!=null){
			loginFlag = true;
			var uname = data.uname;
			$("#hidden-uname").val(uname);
			$("#loginReg").replaceWith("<span id=\"loginReg\" class=\"Gray7\">Hi,"+uname+"</span>");
			
			$.post("cartServlet",{op:"findCart",uname:uname},function(data){
				var str = "";
				$.each(data,function(index,item){
					str+="<tr>"+
					"<td valign=\"middle\" height=\"60\">"+
					"<div id=\"numimg"+item.gid+"\" class=\"numimg\" onclick=\"removeProduct(this)\" ></div>"+
					"<a href=\"#\">"+
					"<span id=\""+"title"+item.gid+"\" class=\"title\">"+item.gname+"</span>"+
					"</a>"+
					"</td>"+
					"<td valign=\"middle\" height=\"60\" align=\"center\">"+
					"<span id=\"originalPrice"+item.price+"\" class=\"price\">￥"+(item.price*2)+"</span>"+
					"</td>"+
					"<td valign=\"middle\" height=\"60\" align=\"center\">"+
					"<span id=\"price"+item.price+"\"class=\"price\">￥"+item.price+"</span>"+
					"</td>"+
					"<td valign=\"middle\" height=\"60\">"+
					"<div class=\"numbox\">"+
					"<!-- 减 -->"+
					" <div id=\"numimg1"+item.gid+"\" class=\"numimg1\" onclick=\"subNum(this)\" ></div>"+
					"<input id=\"productnum"+item.gid+"\" class=\"numtext yahei\" type=\"text\" value=\""+item.num+"\" autocomplete=\"off\" style=\"ime-mode: disabled;\" maxnum=\"999\" minnum=\"1\" name=\"number\">"+
					"<!-- 加 -->"+	
					"<div  id=\"numimg2"+item.gid+"\" class=\"numimg2\" onclick=\"addNum(this)\" ></div>"+
					"</div>"+
					"</td>"+
					"<td class=\"last\" valign=\"middle\" height=\60\" align=\"center\">"+
					"<!-- 总计 -->"+
					"<span id=\"yprice"+item.gid+"\" class=\"price\">￥"+(item.num*item.price)+"</span>"+
					"</td>"+
					"</tr>";
				})
				$("#cartTbody").html(str)
				getAllPrice()
			},"json");
			
		}else if(!data){
			alert("发生错误请重新登陆");
			location.href="login.html"
			return;
		}
	},"json")
});



$(".yahei").bind({
	change:function(){
		getPrice(this);
	}
});

function getPrice(obj){
	var price=$(obj).parent().parent().prev().children().eq(0).text();
	price=price.substr(1,price.length-1);
	
	price=price*$(obj).val();
	$(obj).parent().parent().next().children().eq(0).text( "￥"+price);
	
	getAllPrice();
}


function getAllPrice(){
	var allPrice=0;
	$("tbody").children().each(function(index, element) {
       var singlePrice=$(element).children().eq(4).children().eq(0).text();
	   singlePrice=singlePrice.substr(1,singlePrice.length-1);
	   allPrice+= parseInt( singlePrice );
    });
	$("tfoot .price").text("￥"+allPrice);
}


function subNum(obj){
	var num=$(obj).next().val();
	if(num<=0){
		$(obj).next().val(1);
	}else if(num>999){
		$(obj).next().val(999);
	}else{
		num--;
		$(obj).next().val(num);
		getPrice($(obj).next());
	}
}

function addNum(obj){
	var num=$(obj).prev().val();
	if(num<0){
		$(obj).prev().val(1);
	}else if(num>=999){
		$(obj).prev().val(999);
	}else{
		num++;
		$(obj).prev().val(num);
		getPrice($(obj).prev());
	}
}

function removeProduct(obj){
	$(obj).parent().parent().remove();
	getAllPrice();
}

function pay(){
	var total = $("tfoot .price").text();
	var totalPrice = parseInt(total.substr(1,total.length-1));
	var uname  = $("#hidden-uname").val();
	$.post("orderServlet",{op:"payOrder",uname:uname,totalPrice:totalPrice},function(data){
		data = $.trim(data);
		if(data>0){
			alert("结算成功");
			location.href="index.html";
		}else{
			alert("结算失败，出错！");
		}
	});
	
}