package com.yc.wowo.biz.impl;

import java.util.List;

import com.yc.wowo.biz.IGoodsTypeBiz;
import com.yc.wowo.dao.IGoodsType;
import com.yc.wowo.dao.impl.GoodsTypeDaoImpl;
import com.yc.wowo.entities.GoodsType;

public class GoodsTypeBizImpl implements IGoodsTypeBiz {
	IGoodsType goodsTypeDao = new GoodsTypeDaoImpl();
	
	@Override
	public List<GoodsType> find(Integer pageNo, Integer pageSize) {
		if(pageNo <= 0){
			pageNo = 1;
		}
		
		if(pageSize <= 0){
			pageSize = 1;
		}
		return goodsTypeDao.find(pageNo, pageSize);
	}

	@Override
	public List<GoodsType> find() {
		return goodsTypeDao.find();
	}

	@Override
	public Integer add(String tname, String des) {
		return goodsTypeDao.add(tname, des);
	}

	@Override
	public Integer update(Integer tid, String tname, String des) {
		return goodsTypeDao.update(tid, tname, des);
	}

}
