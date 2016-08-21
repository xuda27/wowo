package com.yc.wowo.servlets;

import java.io.IOException;
import java.util.List;

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
		}
		
		
		
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
		 if(code.equals(scode)){ // 正确
			 this.out(response, 0);
			 return	;
		 }
		 
		 this.out(response, 1); // 错误
	}
}
