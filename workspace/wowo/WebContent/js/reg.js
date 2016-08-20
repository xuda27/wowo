function createXmlDom() {
    var xmlDom = null;

    if (window.ActiveXObject) {
        xmlDom = createIEXmlDom();
    } else if (document.implementation.createDocument) {
        //其他浏览器
        //                              指定文档的命名空间 空间URL DOC类型
        xmlDom = document.implementation.createDocument("", "", null);
    } else {
        alert("您的浏览器不支持xml dom，请及时更新浏览器");
    }
    return xmlDom;
}

function createIEXmlDom() {
    //这个函数专用用来创建ie浏览器里面的xmlDom
    //每个ie浏览器都必定会有一个浏览器内核，每一个版本的浏览器，只有得到了自己专有的核心，才能够创建xmldom
    var arr = ["MSXML2.DOMDocument.6.0", "MSXML2.DOMDocument.5.0", "MSXML2.DOMDocument.4.0", "MSXML2.DOMDocument.3.0", "MSXML2.DOMDocument.2.0", "Microsoft.XMLDOM"];
    for (var i = 0; i < arr.length; i++) {
        //如果ie浏览器发现版本不对，那么就会继续往下循环。
        try {
            var ieXmlDom = new ActiveXObject(arr[i]); //创建一个ie的xml文档对象
            return ieXmlDom;
        } catch (ex) {
            alert("您的浏览器不支持MSXML组件，请更新浏览器");
        }
    }
}
$(function() {
    var xmlDom = createXmlDom();
    xmlDom.async = false;
    xmlDom.load("city.xml");

    var province = document.getElementById("province");
    var city = document.getElementById("city");
    var area = document.getElementById("district");
    //省份
    var pros = xmlDom.getElementsByTagName("province");
    for (var i = 0; i < pros.length; i++) {
        addOption(pros[i], province);
    }
    province.onchange = function() {
            var code = province.value;
            city.length = 1;
            for (var i = 0; i < pros.length; i++) {
                if (pros[i].nodeType == 1 && pros[i].getAttribute("postcode") == code) {
                    for (var j = 0; j < pros[i].childNodes.length; j++) {
                        if (pros[i].childNodes[j].nodeType == 1)
                            addOption(pros[i].childNodes[j], city);
                    }
                }
            }
        }
        //根据province.onchange 依葫芦画瓢 
    var cities = xmlDom.getElementsByTagName("city");
    city.onchange = function() {
        var code = city.value;
        area.length = 1;
        for (var i = 0; i < cities.length; i++) {
            if (cities[i].nodeType == 1 && cities[i].getAttribute("postcode") == code) {
                for (var j = 0; j < cities[i].childNodes.length; j++) {
                    if (cities[i].childNodes[j].nodeType == 1)
                        addOption(cities[i].childNodes[j], area);
                }
            }
        }
    }

    $('#regemail').emailComplete({
        opacity: 0.7,
        radius: 4
    });

    checkInfo();
    
    var box = document.getElementById("argee");
    if(box.checked == false){
    	$("#button").attr("disabled",true);
    }else{
    	$("#button").attr("disabled",false);
    }
});

function addOption(node, element) {
    var opt = document.createElement("option");
    opt.appendChild(document.createTextNode(node.getAttribute("name")));
    opt.setAttribute("value", node.getAttribute("postcode"));
    element.appendChild(opt);
}


function goSubmit() {
    $("#registererform").submit();
}

function checkInfo() {

    $("#username_msg").css("color", "red");
    $("#username_msg").css("font-size", "14px");

    $("#password_msg").css("color", "red");
    $("#password_msg").css("font-size", "14px");

    $("#reppassword_msg").css("color", "red");
    $("#reppassword_msg").css("font-size", "14px");

    $("#tel_msg").css("color", "red");
    $("#tel_msg").css("font-size", "14px");

    $("#regemail_msg").css("color", "red");
    $("#regemail_msg").css("font-size", "14px");


    $("#regemail").bind({
        blur: function() {
            var val = $("#regemail").val();
            var reg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;

            var tdInfo = "";

            if (val == "") {
                tdInfo = "请输入邮箱";
            } else if (!reg.test(val)) {
                tdInfo = "请输入正确的邮箱";
            } else {
                tdInfo = "";
            }

            $("#regemail_msg").text(tdInfo);
        },
        focus: function() {
            $("#regemail_msg").text("");
        }
    });

    $("#username").bind({
        blur: function() {
            var val = $("#username").val();
            var reg = /^[a-zA-Z\u4e00\u9fa5][a-zA-Z0-9_\u4e00\u9fa5]{2,17}$/;

            var tdInfo = "";

            if (val == "") {
                tdInfo = "请输入用户名";
            } else if (!reg.test(val)) {
                tdInfo = "请输入正确的用户名";
            } else {
                tdInfo = "";
            }

            $("#username_msg").text(tdInfo);
        },
        focus: function() {
            $("#username_msg").text("");
        }
    });


    $("#password").bind({
        blur: function() {
            var val = $("#password").val();
            var reg = /^\w{6,16}$/;

            var tdInfo = "";

            if (val == "") {
                tdInfo = "请输入密码";
            } else if (!reg.test(val)) {
                tdInfo = "请输入正确的密码";
            } else {
                tdInfo = "";
            }

            $("#password_msg").text(tdInfo);
            
        },
        focus: function() {
            $("#password_msg").text("");
        },
    });

    $("#reppassword").bind({
        blur: function() {
            var val = $("#password").val();
            var reval = $("#reppassword").val();

            if (reval == "") {
                tdInfo = "请输入密码";
            } else if (val != reval) {
                tdInfo = "请确认密码";
            } else {
                tdInfo = "";
            }

            $("#reppassword_msg").text(tdInfo);
        },
        focus: function() {
            $("#reppassword_msg").text("");
        }
    });

    $("#tel").bind({
        blur: function() {
            var val = $("#tel").val();
            var reg = /^([1][3|4|5|7|8][0-9]{9})$|^(0[0-9]{2,3}\-?\d{7,8})$/;

            var tdInfo = "";

            if (val == "") {
                tdInfo = "请输入联系电话";
            } else if (!reg.test(val)) {
                tdInfo = "请输入正确的联系电话";
            } else {
                tdInfo = "";
            }

            $("#tel_msg").text(tdInfo);
        },
        focus: function() {
            $("#tel_msg").text("");
        }
    });
    
}
var time = 60;
var timer;

function sendCode(){
	var mail = $("#regemail").val();
	var uname = $("#username").val();
	$("#mycodebtn").attr("disabled", true);
	$.post("sendMailServlet", {uname:uname, mail:mail}, function(data){
		data=parseInt($.trim(data));
		if(data == 1){
			timer = setInterval("changeNum()", 1000);
		}else{
			alert("邮件发送失败，请检查邮箱是否正确");
			$("#mycodebtn").attr("disabled", false);
		}
	});
}

function changeNum(){
	time--;
	if(time<=0){
		$("#mycodebtn").val("获取验证码").attr("disabled",false);
		window.clearInterval(timer);
	}else{
		time = 60;
		$("#mycodebtn").val("点击再去获取("+time+"s)");
	}
}

function judgePwd(){
	var val = $("#password").val();
    if(val.length>0 && val.length<=7){
    	$(".ucr-stronger.clearfix li:eq(0)").css("color","yellow");
    	$(".ucr-stronger.clearfix li:eq(1)").css("color","white");
    	$(".ucr-stronger.clearfix li:eq(2)").css("color","white");
    }else if(val.length>7 && val.length<=12){
    	$(".ucr-stronger.clearfix li:eq(0)").css("color","white");
    	$(".ucr-stronger.clearfix li:eq(1)").css("color","yellow");
    	$(".ucr-stronger.clearfix li:eq(2)").css("color","white");
    }else if(val.length>12){
    	$(".ucr-stronger.clearfix li:eq(0)").css("color","white");
    	$(".ucr-stronger.clearfix li:eq(1)").css("color","white");
    	$(".ucr-stronger.clearfix li:eq(2)").css("color","yellow");
    }else{
    	$(".ucr-stronger.clearfix li:eq(0)").css("color","white");
    	$(".ucr-stronger.clearfix li:eq(1)").css("color","white");
    	$(".ucr-stronger.clearfix li:eq(2)").css("color","white");
    }
}
