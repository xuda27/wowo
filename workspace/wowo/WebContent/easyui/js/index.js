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
			}
		}
	});
});

