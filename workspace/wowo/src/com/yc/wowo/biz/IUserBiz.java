package com.yc.wowo.biz;

import java.util.List;
import java.util.Map;

import com.yc.wowo.entities.UserInfo;

public interface IUserBiz {
	
	/**
	 * 添加会员信息
	 * @param email
	 * @param uname
	 * @param pwd
	 * @param tel
	 * @param prov
	 * @param city
	 * @param area
	 * @return
	 */
	public Integer add(String email, String uname, String pwd,String tel,
			String prov, String city, String area);
	
	/**
	 * 查询全部会员信息
	 * @return
	 */
	public List<UserInfo> find();
	
	
	/**
	 * 分页查询
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<UserInfo> find(Integer pageNo, Integer pageSize);
	
	public List<UserInfo> find(Map<String,String> map, Integer pageNo, Integer pageSize);
}
