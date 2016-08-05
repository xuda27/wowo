$.fn.moveDiv = function() {
    var obj = $(this); //获得给定的对象
    var isDown = false;//记录鼠标没有按下去
    var cx = 0, cy = 0;
    var nowx = 0, nowy = 0;

    //获取当前对象的位置
    var y = obj.css("top");
    var x = obj.css("left");

    if (y == "auto") {
        y = 0;
    }

    //水平居中
    if (x == "auto") {
        x = ( $(window).width() - obj.outerWidth() )/2;
    }

    obj.css({"position":"absolute", "cursor":"move", "top":y, "left":x });

    obj.bind({
        mousedown:function() {
            isDown = true;
        },
        mouseup:function() {
            isDown =false;
            //记录当前鼠标点击的位置
            cx = e.cilentX;
            cy = e.cilentY;
        },
        mousemove:function(e) {
            if (isDown) {
                nowx = x + e.cilentX - cx;
                nowy = y + e.cilentY - cy;
                $(this).css({left:nowx, top:nowy});
            }
        }
    });
}