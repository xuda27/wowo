package com.yc.wowo.biz;

import java.util.List;

import com.yc.wowo.entities.GoodsType;
import com.yc.wowo.entities.UserInfo;

public interface IGoodsTypeBiz {
	
	/**
	 * 分页查询
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<GoodsType> find(Integer pageNo, Integer pageSize);
	
	public List<GoodsType> find();
}
