package com.yc.wowo.dao;

import java.util.List;
import java.util.Map;

import com.yc.wowo.entities.AdminInfo;
import com.yc.wowo.entities.Goods;

/**
 * 
 * @author Eden
 * @eamail 
 */
public interface IGoods {
	public List<Goods> find();
	
	public List<Goods> find(Integer pageNo, Integer pageSize );
	
	public Integer add(String gname,int tid, int sid, int price, String des, String pic );
	
	public List<Goods> find(Map<String, String> params, Integer pageNo,
			Integer pageSize);

}
