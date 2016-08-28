package com.yc.wowo.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.wowo.biz.IRolesBiz;
import com.yc.wowo.biz.IUserBiz;
import com.yc.wowo.biz.impl.RolesBizImpl;
import com.yc.wowo.biz.impl.UserBizImpl;
import com.yc.wowo.entities.Roles;
import com.yc.wowo.entities.UserInfo;
import com.yc.wowo.utils.AttributeData;

public class UserServlet extends BasicServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String op = request.getParameter("op");
		if("findUserByPage".equals(op)){
			findUserByPage(request,response);
		}else if("regUser".equals(op)){
			regUser(request,response);
		}else if("testUsername".equals(op)){
			testUsername(request,response);
		}else if("testCode".equals(op)){
			testCode(request,response);
		}else if("searchUser".equals(op)){
			searchUser(request,response);
		}else if("testEmail".equals(op)){
			testEmail(request,response);
		}else if("userLogin".equals(op)){ // 前台登录 
			userLogin(request,response);
		}
		
		
		
	}
	
	
	/**
	 * 前台登录 将登录的用户 存在session中
	 * @param request
	 * @param response
	 */
	private void userLogin(HttpServletRequest request,
			HttpServletResponse response) {
		String code = request.getParameter("logsafecode");
		String scode = "";
		HttpSession session = request.getSession();
		if(session != null ){
			scode = (String) session.getAttribute("rands");
		}
		if(code.equals("")|| scode == null || scode.equals("") || !code.trim().equals(scode.trim())  ){
			this.out(response, 0);
			return;
		}
		
		String uname = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		IUserBiz ub =  new UserBizImpl();
		int rs = ub.find(uname, pwd);
		
		UserInfo user = new UserInfo();
		if(rs > 0){
			user = ub.select(uname, pwd);
		}
		session.setAttribute("user", user);
		this.out(response, rs);
		
	}

	private void testEmail(HttpServletRequest request,
			HttpServletResponse response) {
		String email = request.getParameter("email");
		email = email.trim();
		List<UserInfo> list = (List<UserInfo>) request.getServletContext().getAttribute(AttributeData.ALLUSER);
		for(UserInfo u :list){
			if(u.getEmail().equals(email)){
				this.out(response, 1);
				return;
			}
		}
		
		this.out(response, 0);
	}

	/**
	 * 模糊条件查询
	 * @param request
	 * @param response
	 */
	private void searchUser(HttpServletRequest request,
			HttpServletResponse response) {
		String province  = request.getParameter("province");
		String city = request.getParameter("city");
		String area = request.getParameter("area");
		String uname= request.getParameter("uname");
		String status = request.getParameter("status");
		String pageNo = request.getParameter("page");
		String pageSize = request.getParameter("rows");
		
		Map<String,String> map = new HashMap<String, String>();
		if(!"".equals(province.trim()) && province!=null && !"所有".equals(province.trim()) && !"--请选择省份--".equals(province.trim())){
			map.put("prov=", province);
		}
		if(!"".equals(city.trim()) && city!=null && !"所有".equals(city.trim()) && !"--请选择城市--".equals(city.trim())){
			map.put("city=", city);
		}
		if(!"".equals(area.trim()) && area!=null && !"所有".equals(area.trim()) && !"--请选择地区--".equals(area.trim())){
			map.put("area=", area);
		}
		if(!"".equals(uname.trim()) && uname!=null){
			map.put("uname like", "%"+uname+"%");
		}
		if(!"".equals(status.trim()) && status!=null && !"-1".equals(status.trim())){
			map.put("status=", status);
		}
		IUserBiz ub = new UserBizImpl();
		List<UserInfo> list = ub.find(map, Integer.parseInt(pageNo.trim()), Integer.parseInt(pageSize.trim()));
		int total = ub.find(map, null, null).size();
		this.out(response, list, total);
	}

	/**
	 * 前台注册会员
	 * @param request
	 * @param response
	 */
	private void regUser(HttpServletRequest request,
			HttpServletResponse response) {
		String email = request.getParameter("email");
		String uname = request.getParameter("username");
		String pwd = request.getParameter("password");
		String tel = request.getParameter("tel");
		String prov = request.getParameter("province");
		String city = request.getParameter("city");
		String area = request.getParameter("district");
		
		IUserBiz ub = new UserBizImpl();
		int result = ub.add(email, uname, pwd, tel, prov, city, area);
		this.out(response, result);
	}
	
	/**
	 * 后台查询会员信息
	 * @param request
	 * @param response
	 */
	private void findUserByPage(HttpServletRequest request,
			HttpServletResponse response) {
		String pageNo = request.getParameter("page");
		String pageSize = request.getParameter("rows");
		IUserBiz rb = new UserBizImpl();
		List<UserInfo> list = rb.find(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		List<UserInfo> rs = (List<UserInfo>) this.getServletContext().getAttribute(AttributeData.ALLUSER);
		this.out(response, list, rs.size());
	}

	/**
	 * 验证用户名是否被注册
	 * @param request
	 * @param response
	 */
	private void testUsername(HttpServletRequest request,
			HttpServletResponse response) {
		String uname = request.getParameter("uname");
		List<UserInfo> list = (List<UserInfo>) request.getServletContext().getAttribute(AttributeData.ALLUSER);
		for(UserInfo u :list){
			if(u.getUname().equals(uname)){
				this.out(response, 1);
				return;
			}
		}
		
		this.out(response, 0);
	}
	
	/**
	 * 验证码判断
	 * @param request
	 * @param response
	 */
	private void testCode(HttpServletRequest request,
			HttpServletResponse response) {
		String code = request.getParameter("code");
		 HttpSession session = request.getSession();
		 String scode =  (String) session.getAttribute("sessionCode");
		 if(code.trim().equals(scode.trim())){ // 正确
			 this.out(response, 0);
			 return	;
		 }
		 
		 this.out(response, 1); // 错误
	}
}
