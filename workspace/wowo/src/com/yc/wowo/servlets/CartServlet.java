package com.yc.wowo.servlets;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingDeque;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.wowo.biz.ICartItemBiz;
import com.yc.wowo.biz.impl.CartItemBizImpl;
import com.yc.wowo.dao.DBHelper;
import com.yc.wowo.entities.CartItem;


public class CartServlet extends BasicServlet {
	private static final long serialVersionUID = 1L;
       

    public CartServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		if("toCart".equals(op)){
			toCart(request,response);
		}else if("findCart".equals(op)){
			findCart(request,response);
		}
		
		
		
	}

	/**
	 * 查询当前用户的购物车数据
	 * @param request
	 * @param response
	 */
	private void findCart(HttpServletRequest request,
			HttpServletResponse response) {
		String uname = request.getParameter("uname");
		ICartItemBiz cib = new CartItemBizImpl();
		List<CartItem> list = cib.find(uname);
		this.out(response, list);
	}

	private void toCart(HttpServletRequest request, HttpServletResponse response) {
		String uname = request.getParameter("uname");
		//查询出是 哪个用户登录
		if(uname.equals("null") || uname ==null ||uname.equals("")){
			this.out(response, 0);
			return;
		}
		
		String sql = "select usid from userInfo where uname = '"+uname+"'";
		DBHelper db = new DBHelper();
		String usid = db.findOne(sql, null);
		
		
		//购物车已经有该商品 则该商品数量 +1
		String gid = request.getParameter("gid");
		String sql1 = "select num from cartItem where gid ="+gid;
		String num = db.findOne(sql1, null);
		if(num!=null){
			String sql2 = "update cartItem set num="+(Integer.parseInt(num)+1)+" where gid="+gid;
			this.out(response, db.doUpdate(sql2, null));
			return;
		}
		
		// 购物车没有该商品
		ICartItemBiz cib = new CartItemBizImpl();
		int result = cib.add(Integer.parseInt(usid), Integer.parseInt(gid), 1);
		this.out(response, result);
	}

}
