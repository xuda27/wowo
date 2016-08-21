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
    xmlDom.load("../../city.xml");

    var province = document.getElementById("manager_shopping_prov");
    var city = document.getElementById("manager_shopping_city");
    var area = document.getElementById("manager_shopping_area");
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

});

function addOption(node, element) {
    var opt = document.createElement("option");
    opt.appendChild(document.createTextNode(node.getAttribute("name")));
    opt.setAttribute("value", node.getAttribute("postcode"));
    element.appendChild(opt);
}