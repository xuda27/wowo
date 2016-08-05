--角色信息表
create table roles(
       rid number(10) primary key, --角色编号
       rname varchar(100) not null unique,  --角色名称
       mark varchar(200),--角色描述
       status number(2) --状态
);
create sequence seq_roles_rid start with 1001;

--后台管理员信息表
create table adminInfo(
       aid number(10) primary key, --管理员编号
       aname varchar2(100), --管理员姓名
       rid number(10)
           constraints FK_adminInfo_roles_rid references roles(rid),
       pwd varchar2(100),--密码
       email varchar2(100) unique,
       photo varchar(400),--图片
       tel varchar2(15),
       status number(2),--0 表示未审核 1审核未通过 2已审核可登陆 3冻结
       mark varchar2(200)--说明
);
create sequence seq_adminInfo_aid start with 1001;

--前台注册用户表
create table userInfo(
       usid number(10) primary key, --会员编号
       uname varchar2(100) not null unique, -- 昵称
       relname varchar2(100), --真实姓名
       pwd varchar(20),
       tel varchar(15) not null unique,
       photo varchar2(200),
       
       prov varchar2(100),
       city varchar2(100),
       area varchar2(100),
       
       grade number(10,2), --积分
       status number(2)
       
       
);
create sequence seq_userInfo_usid start with 1001;

--商品类型
create table goodstype(
       tid number(10) primary key,--类型编号
       tname varchar2(100) not null unique, --类型名称
       des varchar2(200), --类型描述
       status number(2) --类型状态
);
create sequence seq_goodstype start with 1001;

--商店信息
create table shop(
       spid number(20) primary key,--店铺编号
       sname varchar2(200), --店铺名称
       aid number(10)
           constraints FK_shop_adminInfo_aid references adminInfo(aid),
       tid number(10)      --店铺类型
       constraints FK_shop_goodstype references goodstype(tid),
       prov varchar2(100),
       city varchar2(100),
       area varchar2(100),
       points varchar2(400),--详细地址
       tel varchar2(50),    --联系方式
       info clob, --店铺信息
       status number(2) --状态
);
create sequence seq_shop_spid start with 1001;

--商品信息
create table goods(
   gid number(20) primary key,
   gname varchar2(200),--商品名称
   des varchar2(200),--商品描述
   price number(10,8), --商品原价
   tid number(10)      --商品类型
       constraints FK_goods_goodstype references goodstype(tid),
   pic varchar2(2000), --商品展示的图片
   spid number(20) --商家编号
        constraints FK_goods_shop references shop(spid)
        
       
);
create sequence seq_goods_gid start with 1001;

--商品活动表
create table goodsActivity(
       gaid number(10) primary key,
       gid number(20)
           constraints FK_goodsActivity_goods_gid references goods(gid),
       aprice number(10,2), --活动价格
       personnum number(2), --几人餐
       title varchar2(200), --活动标题
       startdate date,      --活动开始时间
       enddate date,        --活动结束时间
       prompt varchar(4000), --温馨提示
       content clob--活动介绍
);
create sequence seq_goodsActivity_gaid start with 1001;

--消息表
create table message(
       mid number(10) primary key,
       title varchar2(200), 
       content varchar2(4000),
       mdate date, --消息时间
       aid number(10)
           constraints FK_message_adminInfo_aid references adminInfo(aid)
);
create sequence seq_message_mid start with 1001;

--订单表
create table orders(
       oid varchar2(200) primary key,
       odate date,
       usid number(10)
            constraints FK_orders_usersInfo_usid references userInfo(usid),
       gaid number(10)
            constraints Fk_orders_goodsActivity_gaid references goodsActivity(gaid),
       nums number(10), --订单份数
       totalprice number(10,8), --总额
       status number(2) --订单状态     
);
create sequence seq_orders_oid start with 1001;

