package com.yc.wowo.dao.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yc.wowo.dao.DBHelper;
import com.yc.wowo.dao.IOrderDao;
import com.yc.wowo.entities.AdminInfo;
import com.yc.wowo.entities.Orders;

public class OrderDaoImpl implements IOrderDao {

	@Override
	public Integer add(Integer usid, Integer totalPrice) {
		String sql = "insert into orders values(seq_orders_oid.nextval,sysdate,?,?,1)";
		List<Object> params = new LinkedList<Object>();
		params.add(usid);
		params.add(totalPrice);
		
		DBHelper db = new DBHelper();
		return db.doUpdate(sql, params);
	}

	@Override
	public List<Orders> find(Integer pageNo, Integer pageSize) {
		DBHelper db=new DBHelper();
		List<Object> params = new ArrayList<Object>();
		String sql=null;
		if(pageNo==null){
			sql="select * from orderss order by oid desc";
		}else{
			params.add(pageNo*pageSize);
			params.add((pageNo-1)*pageSize);
			sql="select * from(select a.*,rownum rn from("
					+ "select * from orderss order by oid desc )a where rownum<=? ) where rn>?";
		}
		return db.find(sql, params,Orders.class);
	}

	@Override
	public int getTotal(Integer oid) {
		DBHelper db = new DBHelper();
		String sql = null;
		List<Object> params = new ArrayList<Object>();
		if(oid == null){
			sql = "select count(oid) from orders";
		}else{
			sql = "select count(oid) from orders where oid = ?";
			params.add(oid);
		}
		return db.findByOne(sql, params);
	}

	@Override
	public List<Orders> find(Map<String, String> params, Integer pageNo,
			Integer pageSize) {
		DBHelper db = new DBHelper();
		List<Object> param = new ArrayList<Object>();
		String sql = "　select * from orderss";
		if(params!=null && params.size()>0){
			sql+=" where 1=1";
			Set<String> keys = params.keySet();
			for(String key : keys){
				sql+=" and "+key +"?"; //and rid = ?
				param.add(params.get(key));
			}
		}
		sql += " order by oid desc";
		if(pageNo != null){
			sql = "select * from (select a.*,rownum rn from("+sql+") a where rownum<=?)where rn > ?";
			param.add(pageNo*pageSize);
			param.add((pageNo-1)*pageSize);
		}
		return db.find(sql, param, Orders.class);
	}

	@Override
	public List<Orders> find() {
		String sql = "select * from orderss";
		DBHelper db = new DBHelper();
		
		return db.find(sql, null,Orders.class);
	}

}
