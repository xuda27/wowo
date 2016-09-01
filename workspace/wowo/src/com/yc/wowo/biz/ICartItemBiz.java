package com.yc.wowo.biz;

import java.util.List;

import com.yc.wowo.entities.CartItem;

public interface ICartItemBiz {
	public Integer add(Integer usid,Integer gid,Integer num);
	
	public List<CartItem> find(String uname);
}
