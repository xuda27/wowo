$(function() {
    /*邮箱*/
    $("#email").blur(function() {
        $(".rcodeInfo strong").text($(this).val());
    });

    $('#navigation').show();
    $('#navigation a').bind('click', function(e) {
        var $this = $(this);
        if ($this.attr("href") != "#") {
            return;
        } else {
            $this.closest('ul').find('li').removeClass('selected');
            $this.parent().addClass('selected');
            $this.parent().find("span").addClass("checked").removeClass("error");

            var num = $this.data("type-name");
            $('#steps').stop().animate({
                marginLeft: '-' + num * 600 + 'px'
            }, 500);
        }
    });
});

/*输入验证码后的下一步，即新密码设置*/
function nextstep() {
	var rcode = $("#rcode").val();
	$.post("../adminInfoServlet",{op:"testRcode",rcode:rcode},function(data){
		if( data == 0 ){
			alert("验证码不能为空！");
		}else if(data == 1){
			alert("验证码过期啦！");
		}else if(data == 2 ){
			alert("验证码错误！")
		}else if(data == 3 ){
			alert("验证成功！")
			$("#navigation li").removeClass("selected");
		    $("#newpwdli").addClass("selected");
		    $("#newpwdli a").attr("href", "#");
		    $("#newpwdli span").addClass("checked").removeClass("error");
		    $('#steps').stop().animate({
		        marginLeft: '-1200px'
		    }, 500);
		}
	});
	
    
}

/*获取验证码*/
function getCodeInfo() {
	var email = $("#email").val();
	var username = $("#username").val();
	$.post("../adminInfoServlet",{op:"retrievePassword",email:email, username:username},function(data) {
		if(data == 0){
			alert("您输入的格式有误！");
		}else if (data == 1){
			$("#navigation li").removeClass("selected");
		    $("#navigation li:eq(1)").addClass("selected");
		    $("#navigation li span:eq(1)").addClass("checked").removeClass("error");
		    $('#steps').stop().animate({
		        marginLeft: '-600px'
		    }, 500);
		}
	});
}
