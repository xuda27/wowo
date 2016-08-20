$(function() {
    $(".container").css("position", "fixed").css("top", ($(window).height() - $(".container").height()) / 2)
        .css("left", ($(window).width() - $(".container").width()) / 2);

    $('.close-button').click(function() {
        $(this).parent().removeClass("slidePageInFromLeft").addClass("slidePageBackLeft");
    });

    $(window).resize(function() {
        $(".container").css("position", "fixed").css("top", ($(window).height() - $(".container").height()) / 2)
            .css("left", ($(window).width() - $(".container").width()) / 2);
    });

    $.post("../rolesServlet", {
        op: "findAllRoles"
    }, function(data) {
        var str = "";
        var str1 = "";
        $.each(data, function(index, item) {
            str += "<li><a id='" + item.rid + "' href=\"javascript:login('" + item.rid + "','loginrole')\">" + item.rname + "</a></li>";
            str1 += "<li><a id='" + item.rid + "' href=\"javascript:login('" + item.rid + "','role')\">" + item.rname + "</a></li>";
        });
        $("#registerRoles").html("").append($(str1));
        $("#loginRoles").html("").append($(str));
    }, "json");

    $("#vcode").focus(function() {
        $(this).css("border-color", "green");
    });

});

function login(id, role) {
    var flag = $("#" + id).text();
    $("#" + role).val(flag);
    $("#roleId").val(id); //将roleId存到隐藏域中 
}

function showRegisterPage() {
    $(".register-page").addClass("slidePageInFromLeft").removeClass("slidePageBackLeft");
}

function backlogin() {
    $(".register-page").removeClass("slidePageInFromLeft").addClass("slidePageBackLeft");
}

function adminLogin() {
    var role = $.trim($("#roleId").val());
    var name = $.trim($("#uname").val());
    var pwd = $.trim($("#pwd").val());
    var code = $.trim($("#vcode").val());

    if (role == "") {
        return false;
    }

    if (name == "") {
        $("#uname").css("border-color", "red");
        return false;
    }

    if (pwd == "") {
        return false;
    }

    if (code == "") {
        return false;
    }

    $.post("../adminInfoServlet", {
        op: "adminLogin",
        role: role,
        name: name,
        pwd: pwd,
        code: code
    }, function(data) {
        data = parseInt($.trim(data));
        if (data == 1) {
            $("#vcode").css("border-color", "red");
        } else if (data == 2) {
            alert("用户名或密码错误...");
        } else if (data == 3) { //登录成功
            location.href = "manager/index.html";
        } else {
            alert("错误...");
        }
    });
    return false;
}

var flag1 = [];
/**
 * 管理员注册部分
 */
function blurName() {
    var obj = $("#rname").val();
    var reg = /^[a-zA-Z0-9_\u4e00-\u9fa5]{2,12}$/;

    if( reg.test(obj) ) {
        $("#label-rname").css("color", "blue");
        $("#label-rname").text("输入正确");
        flag1[0] = 1;
    }else {
        $("#label-rname").css("color", "red");
        $("#label-rname").text("用户名必须由2-12位的中文、字母、数字和下划线组成");
        flag1[0] = 0;
    }

    $.post("../adminInfoServlet", {op:"testAdminName", aname:obj}, function(date){
        if (date == 0) { // 有重名
            $("#label-rname").css("color", "red");
            $("#label-rname").text("该用户名已被注册，请重新输入");
            flag1[0] = 0;
        }
    });
}

function focusName() {
    $("#label-rname").css("color", "#525252");
    $("#label-rname").text("请输入用户名");
}


function blurRpwd() {
   var obj = $("#rpwd").val();
    var reg = /^[a-zA-Z0-9_]{6,16}$/;

    if( reg.test(obj) ) {
        $("#label-rpwd").css("color", "blue");
        $("#label-rpwd").text("输入正确");
        flag1[1] = 1;
    }else {
        $("#label-rpwd").css("color", "red");
        $("#label-rpwd").text("密码必须由6-16位的字母、数字和下划线组成");
        flag1[1] = 0;
    } 
}

function focusRpwd() {
    $("#label-rpwd").css("color", "#525252");
    $("#label-rpwd").text("请再次输入密码");
}



function blurRpwds() {
    var obj = $("#rpwds").val();
    var obj2 = $("#rpwd").val();
    if(obj == null || obj == "") {
        $("#label-rpwds").css("color", "red");
        $("#label-rpwds").text("密码不能为空");
        flag1[2] = 0;
    }else if (obj === obj2) {
        $("#label-rpwds").css("color", "blue");
        $("#label-rpwds").text("两次密码一致，请继续下一步");
        flag1[2] = 1;
    }else {
        $("#label-rpwds").css("color", "red");
        $("#label-rpwds").text("密码不一致");
        flag1[2] = 0;
    }
}

function focusRpwds() {
    $("#label-rpwds").css("color", "#525252");
    $("#label-rpwds").text("请输入密码");
}

$(function () {

    $('#email').emailComplete({
        opacity: 0.7,
        radius: 4
    });

});

function blurEmail() {
    var obj = $("#email").val();
    var label = $("#label-email");
    var reg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;

    if ( reg.test(obj) ) {
        label.css("color", "blue");
        label.text("符合邮箱格式");
        flag1[3] = 1;
    } else {
        label.css("color", "red");
        label.text("请输入正确的邮箱");
        flag1[3] = 0;
    } 

    $.post("../adminInfoServlet", {op:"testEmail", email:obj}, function(date){
        if (date == 0) { // 有重名
            label.css("color", "red");
            label.text("该邮箱已被注册，请重新输入");
            flag1[3] = 0;
        }
    });
}

function focusEmail() {
    $("#label-email").css("color", "#525252");
    $("#label-email").text("请输入您的邮箱");
}

function blurTel() {
    var obj = $("#tel").val();
    var label = $("#label-tel");
    var reg = /^1[3|4|5|7|8]\d{9}$/;

    if ( reg.test(obj) ) {
        label.css("color", "blue");
        label.text("符合手机号格式");
        flag1[4] = 1;
    } else {
        label.css("color", "red");
        label.text("请输入正确的手机号码");
        flag1[4] = 0;
    } 
}

function focusTel() {
    $("#label-tel").css("color", "#525252");
    $("#label-tel").text("请输入您的手机号码");
}


//判断是否可以提交
function toSubmit() {
 /*   var a = $("label.input-group-addon.promptinfo");
    for (var i = 0; i < a.length; i++) {
        var label = a[i];
        if( label.css("color") == "red" ){
            alert("您的注册信息有误，请确认您的注册信息");
            return false;
        }
    }*/

    for (var i = 0; i < flag1.length; i++) {
        if ( flag1[i] != 1 ) {
            alert("您的注册信息有误，请确认您的注册信息");
            return false;
        }
    }

    var aname = $("#rname").val();
    var rid = $("#roleId").val();
    var pwd = $("#rpwd").val();
    var email = $("#email").val();
    var tel = $("#tel").val();
    $.post("../adminInfoServlet", {op:"registeraAdmin", aname:aname, rid:rid, pwd:pwd, email:email, tel:tel}, function(data){
    	var d = parseInt(data);
        if (d > 0) {
            alert("注册成功");
            document.getElementById("register-form").reset(); //jquery不行
            location.href = "login.html";
        } else {
            alert("注册失败");
            return false;
        }
    });
    return false;
}

