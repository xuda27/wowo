package com.yc.wowo.dao;

import java.util.List;

import com.yc.wowo.entities.GoodsType;

/**
 * 
 * @author Eden
 *
 */
public interface IGoodsType {
	
	/**
	 * 分页查询
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<GoodsType> find(Integer pageNo, Integer pageSize);
	
	public List<GoodsType> find();
}
