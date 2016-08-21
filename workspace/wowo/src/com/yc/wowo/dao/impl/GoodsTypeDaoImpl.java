package com.yc.wowo.dao.impl;

import java.util.LinkedList;
import java.util.List;

import com.yc.wowo.dao.DBHelper;
import com.yc.wowo.dao.IGoodsType;
import com.yc.wowo.entities.GoodsType;
import com.yc.wowo.entities.UserInfo;

public class GoodsTypeDaoImpl implements IGoodsType {
	
	DBHelper db = new DBHelper();
	
	@Override
	public List<GoodsType> find(Integer pageNo, Integer pageSize) {
		List<Object> params = new LinkedList<Object>();
		params.add(pageNo*pageSize);
		params.add((pageNo-1)*pageSize);
		String sql = "select * from(select a.*,rownum as rn from("+
				"select * from goodsType where status = 1 order by usid asc) a where rownum<=?) where rn>?";
		return db.find(sql, params, GoodsType.class);
	}

	@Override
	public List<GoodsType> find() {
		String sql = "select * from goodsType";
		return db.find(sql, null, GoodsType.class);
	}

}
