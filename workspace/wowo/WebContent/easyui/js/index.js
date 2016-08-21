$(function() {
	$.post("../../adminInfoServlet", {op:"getLoginInfo"}, function(data){
		if(data == "" || data == null){
			location.href="../login.html";
		}else{
			$("#index_loginuser").text(data.aname);
			$("#index_loginphoto").attr("src","../../"+data.photo);
		}
	},"json");
	
	$("#index_content_info").tabs("add",{
		title:"达达软件",
		closable:true,
		fit:true, //设置为true时，选项卡的大小将铺满它所在的容器（浏览器）。
		href:"xd.html"
	});
	
	//当点击菜单的时候，自动创建选项卡
	$("#index_menu_tree").tree({
		onClick:function(node){
			var tab=$("#index_content_info");//获取选项卡的对象
			if(node.id == "index_role"){
				if(tab.tabs("exists","角色管理")){
					tab.tabs("select","角色管理");
				}else{
					tab.tabs("add",{
						title:"角色管理",
						closable:true,
						fit:true,
						href:"roles.html"
					});
				}
			}else if(node.id=="index_admin"){
				if(tab.tabs("exists","管理员信息")){
					tab.tabs("select","管理员信息");
				}else{
					tab.tabs("add",{
						title:"管理员信息",
						closable:true,
						fit:true,
						href:"admin.html"
					});
				}
			}else if(node.id=="index_shop3"){
				if(tab.tabs("exists","管理店铺信息")){
					tab.tabs("select","管理店铺信息");
				}else{
					tab.tabs("add",{
						title:"管理店铺信息",
						closable:true,
						fit:true,
						href:"managerShopping.html"
					});
				}
			}else if (node.id == "index_user") {
				if (tab.tabs("exists","会员信息")) {
					tab.tabs("select","会员信息");
				} else {
					tab.tabs("add",{
						title:"会员信息",
						closable:true,
						fit:true,
						href:"user.html"
					});
				}
			}else if(node.id == "index_shop2") {
				if (tab.tabs("exists","查看店铺信息")) {
					tab.tabs("select","查看店铺信息");
				} else {
					tab.tabs("add",{
						title:"查看店铺信息",
						closable:true,
						fit:true,
						href:"shop.html"
					});
				}
			}else if(node.id == "index_types") {
				if (tab.tabs("exists","商品类型信息")) {
					tab.tabs("select","商品类型信息");
				} else {
					tab.tabs("add",{
						title:"商品类型信息",
						closable:true,
						fit:true,
						href:"goodsType.html"
					});
				}
			}else if(node.id == "index_goods2") {
				if (tab.tabs("exists","查看商品信息")) {
					tab.tabs("select","查看商品信息");
				} else {
					tab.tabs("add",{
						title:"查看商品信息",
						closable:true,
						fit:true,
						href:"goods.html"
					});
				}
			}else if(node.id == "index_message") {
				if (tab.tabs("exists","消息管理")) {
					tab.tabs("select","消息管理");
				} else {
					tab.tabs("add",{
						title:"消息管理",
						closable:true,
						fit:true,
						href:"message.html"
					});
				}
			}else if(node.id == "index_orders") {
				if (tab.tabs("exists","订单管理")) {
					tab.tabs("select","订单管理");
				} else {
					tab.tabs("add",{
						title:"订单管理",
						closable:true,
						fit:true,
						href:"orders.html"
					});
				}
			}
		}
	});
});

