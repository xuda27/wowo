package com.yc.wowo.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.wowo.biz.IAdminInfoBiz;
import com.yc.wowo.biz.IOrderBiz;
import com.yc.wowo.biz.IUserBiz;
import com.yc.wowo.biz.impl.AdminInfoBizImpl;
import com.yc.wowo.biz.impl.OrderBizImpl;
import com.yc.wowo.biz.impl.UserBizImpl;
import com.yc.wowo.dao.DBHelper;
import com.yc.wowo.dao.IOrderDao;
import com.yc.wowo.dao.IUserInfo;
import com.yc.wowo.dao.impl.OrderDaoImpl;
import com.yc.wowo.entities.AdminInfo;
import com.yc.wowo.entities.Orders;
import com.yc.wowo.entities.UserInfo;

public class OrderServlet extends BasicServlet {
	private static final long serialVersionUID = 1L;
       
    public OrderServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		if("payOrder".equals(op)){
			parOrder(request,response);
		}else if("findOrderByPage".equals(op)){
			findOrderByPage(request,response);
		}else if("searchOrderByPage".equals(op)){
			searchOrderByPage(request,response);
		}else if("findAllUsers".equals(op)){
			findAllUsers(request,response);
		}else if("findOrder".equals(op)){
			findOrder(request,response);
		}
	}
	
	/**
	 * 查所有订单
	 * @param request
	 * @param response
	 */
	private void findOrder(HttpServletRequest request,
			HttpServletResponse response) {
		IOrderDao od = new  OrderDaoImpl();
		this.out(response, od.find());
	}

	/**
	 * 查找所有会员
	 * @param request
	 * @param response
	 */
	private void findAllUsers(HttpServletRequest request,
			HttpServletResponse response) {
		IUserBiz ub = new UserBizImpl();
		List<UserInfo> list= ub.find();
		this.out(response, list);
	}

	/**
	 * 条件查询
	 * @param request
	 * @param response
	 */
	private void searchOrderByPage(HttpServletRequest request,
			HttpServletResponse response) {
		String pageNo = request.getParameter("page");
		String pageSize = request.getParameter("rows");
		String usid = request.getParameter("usid");
		String status = request.getParameter("status");
		
		Map<String,String> map = new HashMap<String, String>();
		if(!"-1".equals(usid.trim())){
			map.put("usid=", usid);
		}
		if(!"-1".equals(status.trim())){
			map.put("status=", status);
		}
		IOrderDao od = new OrderDaoImpl();
		List<Orders> list =  od.find(map, Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		List<Orders> list1 = od.find(map, null, null);
		this.out(response, list, list1.size());
	}

	/**
	 * 分页查询
	 * @param request
	 * @param response
	 */
	private void findOrderByPage(HttpServletRequest request,
			HttpServletResponse response) {
		String pageNo = request.getParameter("page");
		String pageSize = request.getParameter("rows");
		IOrderDao od = new OrderDaoImpl();
		List<Orders> list = od.find(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		this.out(response, list, od.getTotal(null));
	}

	/**
	 * 生成订单
	 * @param request
	 * @param response
	 */
	private void parOrder(HttpServletRequest request,
			HttpServletResponse response) {
		String uname = request.getParameter("uname");
		String totalPrice = request.getParameter("totalPrice");
		
		String sql = "select usid from userInfo where uname = '"+uname+"'";
		DBHelper db = new DBHelper();
		String usid = db.findOne(sql, null);
		
		IOrderBiz ob = new OrderBizImpl();
		int r = ob.add(Integer.parseInt(usid), Integer.parseInt(totalPrice));
		if(r>0){ // 结算成功
			String sql1 = "delete cartItem where usid = '"+usid+"'";
			this.out(response, db.doUpdate(sql1, null));
		}else{
			this.out(response, 0);
		}
		
		
	}

}
