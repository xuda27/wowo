package com.yc.wowo.dao.impl;

import java.util.LinkedList;
import java.util.List;

import com.yc.wowo.dao.DBHelper;
import com.yc.wowo.dao.IGoods;
import com.yc.wowo.entities.Goods;
import com.yc.wowo.entities.GoodsType;

public class GoodsDaoImpl implements IGoods {
	DBHelper db = new DBHelper();
	@Override
	public List<Goods> find() {
		String sql = "select * from goodss";
		return db.find(sql, null, Goods.class);
	}

	@Override
	public List<Goods> find(Integer pageNo, Integer pageSize) {
		List<Object> params = new LinkedList<Object>();
		params.add(pageNo*pageSize);
		params.add((pageNo-1)*pageSize);
		String sql = "select * from(select a.*,rownum as rn from("+
				"select * from goodss order by gid asc) a where rownum<=?) where rn>?";
		return db.find(sql, params, Goods.class);
	}

	@Override
	public Integer add(String gname, int tid, int spid, int price, String des,
			String pic) {
		String sql = "insert into goods values(seq_goods_gid.nextval,?,?,?,?,?,?)";
		List<Object> params = new LinkedList<Object>();
		params.add(gname);
		params.add(des);
		params.add(price);
		params.add(tid);
		params.add(pic);
		params.add(spid);
		return db.doUpdate(sql, params);
	}

}
