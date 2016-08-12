package com.yc.wowo.listeners;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.yc.wowo.biz.IRolesBiz;
import com.yc.wowo.biz.impl.RolesBizImpl;
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
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		sce.getServletContext().removeAttribute(AttributeData.ALLROLES);
	}

}
