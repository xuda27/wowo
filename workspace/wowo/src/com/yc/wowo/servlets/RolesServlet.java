package com.yc.wowo.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.wowo.biz.IRolesBiz;
import com.yc.wowo.biz.impl.RolesBizImpl;
import com.yc.wowo.entities.Roles;
import com.yc.wowo.utils.AttributeData;

public class RolesServlet extends BasicServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		
		if("findAllRoles".equals(op)){
			findAllRoles(request, response);
		}
	}

	/**
	 * 查询所有的角色信息
	 * @param request
	 * @param response
	 */
	private void findAllRoles(HttpServletRequest request,
			HttpServletResponse response) {
		
		//取出application中的Allroles
		Object roles = this.getServletContext().getAttribute(AttributeData.ALLROLES);
		
		List<Roles> list = null;
		
		//调用业务层
		if(roles != null ){
			list = (List<Roles>)roles;
		}else{
			IRolesBiz rolesBiz = new RolesBizImpl();
			list = rolesBiz.find();
			
			//存application
			this.getServletContext().setAttribute(AttributeData.ALLROLES, list);
		}
		
		//将所有角色信息返回给用户
		this.out(response, list);
	}

}
