package com.yc.wowo.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.wowo.entities.UserInfo;

public class WowoServlet extends BasicServlet {
	private static final long serialVersionUID = 1L;
       
    public WowoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		if("userSession".equals(op)){
			userSession(request,response);
		}
	}

	/**
	 * 判断是否有用户已经登录
	 * @param request
	 * @param response
	 */
	private void userSession(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession userSession = request.getSession();
		UserInfo user = (UserInfo) userSession.getAttribute("user");
		this.out(response, user);

	}

}
