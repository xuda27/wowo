package com.yc.wowo.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.wowo.biz.IGoodsTypeBiz;
import com.yc.wowo.biz.IShopBiz;
import com.yc.wowo.biz.impl.GoodsTypeBizImpl;
import com.yc.wowo.biz.impl.ShopBizImpl;
import com.yc.wowo.entities.AdminInfo;
import com.yc.wowo.entities.GoodsType;
import com.yc.wowo.entities.Shop;

public class ShopServlet extends BasicServlet {
	private static final long serialVersionUID = 1L;

	public ShopServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		if("selectTname".equals(op)){
			selectTname(request,response);
		}else if("selectAname".equals(op)){
			selectAname(request,response);
		}else if("addShop".equals(op)){
			addShop(request,response);
		}else if("findShopsByPage".equals(op)){
			findShopsByPage(request,response);
		}
	}

	/**
	 * 查询店铺信息
	 * @param request
	 * @param response
	 */
	private void findShopsByPage(HttpServletRequest request,
			HttpServletResponse response) {
		String pageNo = request.getParameter("page");
		String pageSize = request.getParameter("rows");
		IShopBiz sp = new ShopBizImpl();
		List<Shop> list = sp.find(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		List<Shop> rs = sp.select();
		this.out(response, list, rs.size());
	}

	/**
	 * 查店家
	 * @param request
	 * @param response
	 */
	private void selectAname(HttpServletRequest request,
			HttpServletResponse response) {
		IShopBiz sp = new  ShopBizImpl();
		List<AdminInfo> list = sp.find(1041); // 店家的ID1041
		this.out(response, list);
	}

	/**
	 * 管理员添加店铺
	 * @param request
	 * @param response
	 */
	private void addShop(HttpServletRequest request,
			HttpServletResponse response) {
		String  sname = request.getParameter("sname");
		String  aid = request.getParameter("aid");
		String  tid = request.getParameter("tid");
		String  prov = request.getParameter("prov");
		String  city = request.getParameter("city");
		String  area = request.getParameter("area");
		String  addr = request.getParameter("addr");
		String  tel = request.getParameter("tel");
		String  text = request.getParameter("text");
		
		IShopBiz sp = new ShopBizImpl();
		int result = sp.add(sname, aid, tid, prov, city, area, addr, tel, text);
		this.out(response, result);
		
	}

	/**
	 * 查询类型名称
	 * @param request
	 * @param response
	 */
	private void selectTname(HttpServletRequest request,
			HttpServletResponse response) {
		IShopBiz sb = new ShopBizImpl();
		this.out(response, sb.find());
		
	}

}
