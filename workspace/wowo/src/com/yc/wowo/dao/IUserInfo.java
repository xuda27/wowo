package com.yc.wowo.dao;

import java.util.List;
import java.util.Map;

import com.yc.wowo.entities.UserInfo;

public interface IUserInfo {
	
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
	
	/**
	 * 条件查询
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<UserInfo> find(Map<String,String> map, Integer pageNo, Integer pageSize);
	
	/**
	 * 登录验证
	 * @param uname
	 * @param pwd
	 * @return
	 */
	public Integer find(String uname, String pwd);
	
	/**
	 * 存session
	 * @param uname
	 * @param pwd
	 * @return
	 */
	public UserInfo select(String uname , String pwd);
}
