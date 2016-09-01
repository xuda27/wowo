package com.yc.wowo.biz.impl;

import java.util.List;

import com.yc.wowo.biz.ICartItemBiz;
import com.yc.wowo.dao.ICartItem;
import com.yc.wowo.dao.impl.CartItemDaoImpl;
import com.yc.wowo.entities.CartItem;

public class CartItemBizImpl implements ICartItemBiz {

	@Override
	public Integer add(Integer usid, Integer gid,Integer num) {
		ICartItem cartItem = new CartItemDaoImpl();
		return cartItem.add(usid, gid, num);
		
	}

	@Override
	public List<CartItem> find(String uname) {
		ICartItem cartItem = new CartItemDaoImpl();
		return cartItem.find(uname);
	}

}
