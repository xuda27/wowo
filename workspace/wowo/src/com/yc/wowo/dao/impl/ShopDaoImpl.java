package com.yc.wowo.dao.impl;

import java.util.LinkedList;
import java.util.List;

import com.yc.wowo.dao.DBHelper;
import com.yc.wowo.dao.IShop;
import com.yc.wowo.entities.AdminInfo;
import com.yc.wowo.entities.GoodsType;
import com.yc.wowo.entities.Shop;

public class ShopDaoImpl implements IShop {
	
	DBHelper db =  new DBHelper();
	@Override
	public List<GoodsType> find() {
		String sql = "select * from Goodstype";
		return db.find(sql, null, GoodsType.class);
	}
	
	@Override
	public List<AdminInfo> find(Integer rid) {
		String sql = "select * from AdminInfo where rid = ?";
		List<Object> params = new LinkedList<Object>();
		params.add(rid);
		return db.find(sql, params,AdminInfo.class);
	}

	@Override
	public Integer add(String sname, String aid, String tid, String prov,
			String city, String area, String addr, String tel, String text) {
		String sql = "insert into shop values(seq_shop_spid.nextval,?,?,?,?,?,?,?,?,?,1)";
		List<Object> params = new LinkedList<Object>();
		params.add(sname);
		params.add(aid);
		params.add(tid);
		params.add(prov);
		params.add(city);
		params.add(area);
		params.add(addr);
		params.add(tel);
		params.add(text);
		return db.doUpdate(sql, params);
	}

	@Override
	public List<Shop> find(Integer pageNo, Integer pageSize) {
		List<Object> params = new LinkedList<Object>();
		params.add(pageNo*pageSize);
		params.add((pageNo-1)*pageSize);
		String sql = "select * from(select a.*,rownum as rn from("+
				"select * from shops order by spid asc) a where rownum<=?) where rn>?";
		
		return db.find(sql, params, Shop.class);
	}

	@Override
	public List<Shop> select() {
		
		return db.find("select * from shops", null, Shop.class);
	}

}
