package com.yc.wowo.listeners;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.yc.wowo.biz.IAdminInfoBiz;
import com.yc.wowo.biz.IRolesBiz;
import com.yc.wowo.biz.impl.AdminInfoBizImpl;
import com.yc.wowo.biz.impl.RolesBizImpl;
import com.yc.wowo.entities.AdminInfo;
import com.yc.wowo.entities.Roles;
import com.yc.wowo.utils.AttributeData;
/**
 * 应用程序加载的监听
 * @author Eden
 *
 */
public class WowoServletContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//将用户角色查询出来存放到application中
		IRolesBiz roleBiz = new RolesBizImpl();
		List<Roles> list = roleBiz.find();
		sce.getServletContext().setAttribute(AttributeData.ALLROLES, list);
		
		//将管理员查询出来存放到application中
		IAdminInfoBiz ab = new AdminInfoBizImpl();
		List<AdminInfo> list1 = ab.find();
		sce.getServletContext().setAttribute(AttributeData.CURRENTADMINLOGIN, list1);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		sce.getServletContext().removeAttribute(AttributeData.ALLROLES);
	}

}
