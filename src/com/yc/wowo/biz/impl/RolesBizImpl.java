package com.yc.wowo.biz.impl;

import java.util.List;

import com.yc.wowo.biz.IRolesBiz;
import com.yc.wowo.dao.IRolesDao;
import com.yc.wowo.dao.impl.RolesDaoImpl;
import com.yc.wowo.entities.Roles;

public class RolesBizImpl implements IRolesBiz {

	@Override
	public List<Roles> find() {
		IRolesDao rolesDao = new RolesDaoImpl();
		return rolesDao.find();
	}

	@Override
	public List<Roles> find(Integer pageNo, Integer pageSize) {
		if(pageNo <= 0){
			pageNo = 1;
		}
		
		if(pageSize <= 0){
			pageSize = 1;
		}
		IRolesDao rolesDao = new RolesDaoImpl();
		
		return rolesDao.find(pageNo, pageSize);
	}

	@Override
	public Integer add(String rname, String mark) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer del(String rid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(String rname, String mark, String rid) {
		// TODO Auto-generated method stub
		return null;
	}

}
