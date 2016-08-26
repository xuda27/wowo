package com.yc.wowo.biz;

import java.util.List;

import com.yc.wowo.entities.Goods;

public interface IGoodsBiz {
	public List<Goods> find();
	
	public List<Goods> find(Integer pageNo, Integer pageSize );
	
	public Integer add(String gname,int tid, int spid, int price, String des, String pic );

}
