package com.yc.wowo.dao;

import java.util.List;

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

}
