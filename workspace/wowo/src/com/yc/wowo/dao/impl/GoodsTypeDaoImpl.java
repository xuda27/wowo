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
				"select * from goodsType where status = 1 order by tid asc) a where rownum<=?) where rn>?";
		return db.find(sql, params, GoodsType.class);
	}

	@Override
	public List<GoodsType> find() {
		String sql = "select * from goodsType";
		return db.find(sql, null, GoodsType.class);
	}

	@Override
	public Integer add(String tname, String des) {
		String sql = "insert into goodstype values(seq_goodstype_tid.nextval,?,?,1)";
		List<Object> params = new LinkedList<Object>();
		params.add(tname);
		params.add(des);
		return db.doUpdate(sql, params);
	}

	@Override
	public Integer update(Integer tid, String tname, String des) {
		String sql = "update goodstype set tname=?,des=? where tid=?";
		List<Object> list =new LinkedList<Object>();
		list.add(tname);
		list.add(des);
		list.add(tid);
		return db.doUpdate(sql, list);
	}

}
