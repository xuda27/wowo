package com.yc.wowo.biz.impl;

import java.util.List;
import java.util.Map;

import com.yc.wowo.biz.IGoodsBiz;
import com.yc.wowo.dao.IGoods;
import com.yc.wowo.dao.impl.GoodsDaoImpl;
import com.yc.wowo.entities.Goods;

public class GoodsBizImpl implements IGoodsBiz {
	IGoods goodsDao = new GoodsDaoImpl();
	@Override
	public List<Goods> find() {
		return goodsDao.find();
	}

	@Override
	public List<Goods> find(Integer pageNo, Integer pageSize) {
		return goodsDao.find(pageNo, pageSize);
	}

	@Override
	public Integer add(String gname, int tid, int spid, int price, String des,
			String pic) {
		return goodsDao.add(gname, tid, spid, price, des, pic);
	}

	@Override
	public List<Goods> find(Map<String, String> params, Integer pageNo,
			Integer pageSize) {
		return goodsDao.find(params, pageNo, pageSize);
	}
	
}
