package com.yc.wowo.dao;

import java.util.List;

import com.yc.wowo.entities.CartItem;

public interface ICartItem {
	public Integer add(Integer usid,Integer gid,Integer num);
	
	public List<CartItem> find(String uname);
}
