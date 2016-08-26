package com.yc.wowo.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.wowo.biz.IGoodsBiz;
import com.yc.wowo.biz.IGoodsTypeBiz;
import com.yc.wowo.biz.IShopBiz;
import com.yc.wowo.biz.impl.GoodsBizImpl;
import com.yc.wowo.biz.impl.GoodsTypeBizImpl;
import com.yc.wowo.biz.impl.ShopBizImpl;
import com.yc.wowo.entities.Goods;
import com.yc.wowo.entities.GoodsType;
import com.yc.wowo.entities.Shop;

public class GoodsServlet extends BasicServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		if("findGoodsByPage".equals(op)){
			findGoodsByPage(request,response);
		}else if("selectTname".equals(op)){
			selectTname(request,response);
		}else if("selectSname".equals(op)){
			selectSname(request,response);
		}else if("addGoods".equals(op)){
			addGoods(request,response);
		}
	
		
	}
	
	
	private void addGoods(HttpServletRequest request,
			HttpServletResponse response) {
		String gname =request.getParameter("gname");
		String tid = request.getParameter("tname");
		String spid	= request.getParameter("sname");
		String price = request.getParameter("price");
		String des = request.getParameter("des");
		String pic = request.getParameter("pic");
		IGoodsBiz sp = new GoodsBizImpl();
		int result = sp.add(gname, Integer.parseInt(tid), Integer.parseInt(spid), Integer.parseInt(price), des, pic);
		this.out(response, result);
	}

	/**
	 * 查询店铺
	 * @param request
	 * @param response
	 */
	private void selectSname(HttpServletRequest request,
			HttpServletResponse response) {
		IShopBiz sp = new ShopBizImpl();
		List<Shop> list = sp.select();
		this.out(response, list);
	}
	
	/**
	 * 查询商品类型
	 * @param request
	 * @param response
	 */
	private void selectTname(HttpServletRequest request,
			HttpServletResponse response) {
		IGoodsTypeBiz tb = new GoodsTypeBizImpl();
		List<GoodsType> list = tb.find();
		this.out(response, list);
	}

	/**
	 * 查询商品
	 * @param request
	 * @param response
	 */
	private void findGoodsByPage(HttpServletRequest request,
			HttpServletResponse response) {
		String pageNo = request.getParameter("page");
		String pageSize = request.getParameter("rows");
		IGoodsBiz sp = new GoodsBizImpl();
		List<Goods> list = sp.find(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		List<Goods> rs = sp.find();
		this.out(response, list, rs.size());
	}

}
