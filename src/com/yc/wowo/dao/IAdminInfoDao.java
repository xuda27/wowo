package com.yc.wowo.dao;

import java.util.List;

import com.yc.wowo.entities.AdminInfo;

/**
 * 后台管理员信息处理接口
 * @author Eden
 *
 */
public interface IAdminInfoDao {
	
	/**
	 * 返回登录的
	 * @param aname
	 * @param pwd
	 * @return
	 */
	public AdminInfo login(String aname,String pwd);
	
	/**
	 * 查询所有后台管理员
	 * @return
	 */
	public List<AdminInfo> find();
	
	public AdminInfo find(Integer aid);
	
	/**
	 * 分页查询
	 * @param pageNo 要查询的页数
	 * @param pageSize 页面的大小
	 * @return
	 */
	public List<AdminInfo>  find(Integer pageNo, Integer pageSize);
	
	public List<AdminInfo> find(Integer rid, Integer pageNo, Integer pageSize);
	
	public Integer update(Integer aid, String photo);
	
	public Integer update(Integer aid, String oldPwd, String newPwd);
	
	public Integer update(String aid);
	
	public Integer update(String aname, Integer rid, String tel, String photo);
	
	public Integer update(String aid, String email);
	/**
	 * 添加管理员
	 * @param aname 管理员名
	 * @param mark 管理员描述
	 * @return
	 */
	public Integer add(String aname, String mark);
	
	/**
	 * 删除管理员
	 * @param aid 要删除管理员的ID
	 * @return
	 */
	public Integer del(String aid);
	
	/**
	 * 修改管理员
	 * @param aid 要修改管理员的ID
	 * @param status 状态
	 * @param mark 描述
	 * @return
	 */
	public Integer update(String aid, String status, String mark);

	
}
