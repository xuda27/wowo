package com.yc.wowo.dao;

import java.util.List;

import com.yc.wowo.entities.Roles;

/**
 * 管理员角色处理接口
 * @author Eden
 *
 */
public interface IRolesDao {
	
	/**
	 * 查找所有角色的信息
	 * @return
	 */
	public List<Roles> find();
	
	/**
	 * 分页查询
	 * @param pageNo 要查询的页数
	 * @param pageSize 页面的大小
	 * @return
	 */
	public List<Roles>  find(Integer pageNo, Integer pageSize);
	
	/**
	 * 添加角色
	 * @param rname 角色名
	 * @param mark 角色描述
	 * @return
	 */
	public Integer add(String rname, String mark);
	
	/**
	 * 删除角色
	 * @param rid 要删除角色的ID
	 * @return
	 */
	public Integer del(String rid);
	
	/**
	 * 修改角色
	 * @param rname 角色名称
	 * @param mark 描述
	 * @param rid 要修改角色的ID
	 * @return
	 */
	public Integer update(String rname, String mark, String rid);
}
