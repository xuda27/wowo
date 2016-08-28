package com.yc.wowo.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import com.yc.wowo.biz.IAdminInfoBiz;
import com.yc.wowo.biz.IGoodsBiz;
import com.yc.wowo.biz.IGoodsTypeBiz;
import com.yc.wowo.biz.IShopBiz;
import com.yc.wowo.biz.impl.AdminInfoBizImpl;
import com.yc.wowo.biz.impl.GoodsBizImpl;
import com.yc.wowo.biz.impl.GoodsTypeBizImpl;
import com.yc.wowo.biz.impl.ShopBizImpl;
import com.yc.wowo.entities.AdminInfo;
import com.yc.wowo.entities.Goods;
import com.yc.wowo.entities.GoodsType;
import com.yc.wowo.entities.Shop;
import com.yc.wowo.utils.UploadUtil;

public class GoodsServlet extends BasicServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
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
		}else if("selectGoods".equals(op)){
			selectGoods(request,response);
		}else if("findGoodsIndex".equals(op)){
			findGoodsIndex(request,response);
		}
	
		
	}
	

	
	/**
	 * 模糊查询
	 * @param request
	 * @param response
	 */
	private void selectGoods(HttpServletRequest request,
			HttpServletResponse response) {
		String tname = request.getParameter("tname");
		String sname = request.getParameter("aname");
		String aname = request.getParameter("aname");
		String pageNo = request.getParameter("page");
		String pageSize = request.getParameter("rows");
		
		Map<String,String> map = new HashMap<String, String>();
		if(!"".equals(tname.trim()) || tname!=null){
			map.put(" tname like ", "%"+tname+"%");
		}
		if(aname!=null && !aname.equals("")){
			map.put(" aname like ", "%"+aname+"%");
		}
		if(sname!=null && !sname.equals("")){
			map.put(" sname like ", "%"+sname+"%");
		}

		
		IGoodsBiz sp = new GoodsBizImpl();
		List<Goods> list = sp.find(map, Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		List<Goods> list1 = sp.find(map, null, null);
		this.out(response, list, list1.size());
		
	}
	
	/**
	 * 添加商品
	 * @param request
	 * @param response
	 */
	private void addGoods(HttpServletRequest request,
			HttpServletResponse response) {
		UploadUtil upload = new UploadUtil();
		PageContext pageContext = JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true, 2048, true);
		Map<String,String> map = upload.upload(pageContext);
		
		String gname = map.get("gname");
		String tid = map.get("tname");
		String spid = map.get("sname");
		String myimg = map.get("myimg");
		String price = map.get("price");
		String des = map.get("des");
		IGoodsBiz sp = new GoodsBizImpl();
		int result = sp.add(gname, Integer.parseInt(tid), Integer.parseInt(spid), Integer.parseInt(price), des, myimg);
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
	
	/**
	 * 首页加载商品信息
	 * @param request
	 * @param response
	 */
	private void findGoodsIndex(HttpServletRequest request,
			HttpServletResponse response) {
		String pageNo = request.getParameter("pageNo");
		String pageSize = request.getParameter("pageSize");
		IGoodsBiz sp = new GoodsBizImpl();
		List<Goods> list = sp.find(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		this.out(response, list);
	}
}
