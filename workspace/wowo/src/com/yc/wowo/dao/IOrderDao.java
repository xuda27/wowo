package com.yc.wowo.dao;

import java.util.List;
import java.util.Map;

import com.yc.wowo.entities.Orders;

public interface IOrderDao {
	public Integer add(Integer usid, Integer totalPrice);
	
	public List<Orders> find(Integer pageNo, Integer pageSize);
	
	public int getTotal(Integer oid);
	
	public List<Orders> find(Map<String, String> params, Integer pageNo,
			Integer pageSize);
	
	public List<Orders> find();
}
