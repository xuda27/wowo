package com.yc.wowo.biz.impl;

import java.util.List;

import com.yc.wowo.biz.IUserBiz;
import com.yc.wowo.dao.IUserInfo;
import com.yc.wowo.dao.impl.UserDaoImpl;
import com.yc.wowo.entities.UserInfo;

public class UserBizImpl implements IUserBiz {
	IUserInfo userDao = new UserDaoImpl();
	
	@Override
	public Integer add(String email, String uname, String pwd, String tel,
			String prov, String city, String area) {
		return userDao.add(email, uname, pwd, tel, prov, city, area);
	}

	@Override
	public List<UserInfo> find() {
		return userDao.find();
	}

	@Override
	public List<UserInfo> find(Integer pageNo, Integer pageSize) {
		if(pageNo <= 0){
			pageNo = 1;
		}
		
		if(pageSize <= 0){
			pageSize = 1;
		}
		return userDao.find(pageNo, pageSize);
	}

}
