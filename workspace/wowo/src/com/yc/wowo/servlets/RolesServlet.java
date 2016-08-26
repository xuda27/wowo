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
		}else if("addRoles".equals(op)){
			addRoles(request, response);
		}else if("findRolesByPage".equals(op)){//分页
			findRolesByPage(request, response);
		}else if("deleteRoles".equals(op)){//删除角色信息
			deleteRoles(request,response);
		}else if("updateRoles".equals(op)){
			updateRoles(request, response);
		}
	}
	
	private void updateRoles(HttpServletRequest request,
			HttpServletResponse response) {
		String rname = request.getParameter("rname");
		String mark = request.getParameter("mark");
		String rid	= request.getParameter("rid");
		IRolesBiz rb = new RolesBizImpl(); 
		
		int result = rb.update(rname, mark, rid);
		List<Roles>  list = rb.find();
		if(result > 0){
			this.getServletContext().setAttribute(AttributeData.ALLROLES, list);
		}
		this.out(response, result);
	}

	/**
	 * 删除角色信息
	 * @param request
	 * @param response
	 */
	private void deleteRoles(HttpServletRequest request,
			HttpServletResponse response) {
		String rid = request.getParameter("rid");
		IRolesBiz	rb = new RolesBizImpl();
		int result = rb.del(rid);
		this.out(response, result);
	}

	/**
	 * 分页查询角色信息
	 * @param request
	 * @param response
	 */
	private void findRolesByPage(HttpServletRequest request,
			HttpServletResponse response) {
		String pageNo = request.getParameter("page");
		String pageSize = request.getParameter("rows");
		IRolesBiz rb = new RolesBizImpl();
		List<Roles> list = rb.find(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		List<Roles> rs = (List<Roles>) this.getServletContext().getAttribute(AttributeData.ALLROLES);
		this.out(response, list, rs.size());
		
	}

	/**
	 * 添加角色信息
	 * @param request
	 * @param response
	 */
	private void addRoles(HttpServletRequest request,
			HttpServletResponse response) {
		String rname = request.getParameter("rname");
		String mark = request.getParameter("mark");
		String status = request.getParameter("status");
		
		IRolesBiz roleBiz = new RolesBizImpl();
		int result  = roleBiz.add(rname, mark);
		
		if(result>0){//如果添加成功,修改application的角色列表值
			this.getServletContext().setAttribute(AttributeData.ALLROLES, roleBiz.find());
		}
		this.out(response, result);
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
