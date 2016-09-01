package com.yc.wowo.dao.impl;

import java.util.LinkedList;
import java.util.List;

import com.yc.wowo.dao.DBHelper;
import com.yc.wowo.dao.ICartItem;
import com.yc.wowo.entities.CartItem;

public class CartItemDaoImpl implements ICartItem{

	@Override
	public Integer add(Integer usid, Integer gid,Integer num) {
		String sql = "insert into cartItem values(seq_cartItem_ciid.nextval,?,?,?)";
		List<Object> params = new LinkedList<Object>();
		params.add(gid);
		params.add(num);
		params.add(usid);
		DBHelper db = new DBHelper();
		return db.doUpdate(sql, params);
	}

	@Override
	public List<CartItem> find(String uname) {
		String sql = "select * from cartItems where uname =?";
		List<Object> params = new LinkedList<Object>();
		params.add(uname);
		DBHelper db = new DBHelper();
 		return db.find(sql, params, CartItem.class);
	}

}
