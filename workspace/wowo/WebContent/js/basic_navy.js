$.fn.moveDiv = function() {
    var obj = $(this); //获得给定的对象
    var isDown = false;//记录鼠标没有按下去
    var cx = 0, cy = 0; //记录鼠标按下时的位置
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
        mousedown:function(e) {
            isDown = true;

            //记录当前鼠标点击的位置
            cx = e.clientX;
            cy = e.clientY;
        },
        mouseup:function(e) {
            isDown =false;
            x = nowx;
            y = nowy;
        },
        mousemove:function(e) {
            if (isDown) {
                nowx = x + e.clientX - cx;
                nowy = y + e.clientY - cy;
                $(this).css({left:nowx, top:nowy});
            }
        }
    });
}