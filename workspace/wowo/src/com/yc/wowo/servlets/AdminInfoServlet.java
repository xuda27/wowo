package com.yc.wowo.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.wowo.biz.IAdminInfoBiz;
import com.yc.wowo.biz.impl.AdminInfoBizImpl;
import com.yc.wowo.entities.AdminInfo;
import com.yc.wowo.utils.AttributeData;

public class AdminInfoServlet extends BasicServlet{
	private static final long serialVersionUID = -1895688370031862751L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String op = request.getParameter("op");

		if("adminLogin".equals(op)){//判断登录用户的账号密码
			adminLogin(request,response);
		}else if("getLoginInfo".equals(op)){//返回登录对象
			getLoginInfo(request,response);
		}
	}
	
	/**
	 * 获取当前用户信息
	 * @param request
	 * @param response
	 */
	private void getLoginInfo(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession();
		Object obj = session.getAttribute(AttributeData.CURRENTADMINLOGIN);
		if(obj==null){//说明用户没有登录
			this.out(response, obj);
		}else{
			this.out(response, (AdminInfo)obj);
		}
	}

	/**
	 * 后台管理员登录
	 * @param request
	 * @param response
	 */
	private void adminLogin(HttpServletRequest request,HttpServletResponse response) {
		String role = request.getParameter("role");
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String code = request.getParameter("code");
		
		//在访问业务层之前，先可以验证验证码   获取session
		 HttpSession session = request.getSession();
		 String codes = (String) session.getAttribute("rands");
		 int result = 0;
		 if(code.equals(codes)){//验证码成功，调用业务层
			 IAdminInfoBiz adminInfoBiz = new AdminInfoBizImpl();
			 AdminInfo adminInfo = adminInfoBiz.login(name, pwd, role);
			 if(adminInfo == null){ //说明用户 密码错误
				 result = 2;
			 }else{//登录成功
				 result = 3;
				 session.setAttribute(AttributeData.CURRENTADMINLOGIN, adminInfo);
			 }
		 }else{//验证码错误
			 result = 1;
		 }
		this.out(response, result);
	}
}
