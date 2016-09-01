package com.yc.wowo.biz.impl;

import java.util.List;
import java.util.Map;

import com.yc.wowo.biz.IOrderBiz;
import com.yc.wowo.dao.IOrderDao;
import com.yc.wowo.dao.impl.OrderDaoImpl;
import com.yc.wowo.entities.Orders;

public class OrderBizImpl implements IOrderBiz {

	@Override
	public Integer add(Integer usid, Integer totalPrice) {
		IOrderDao orderDao = new OrderDaoImpl();
		return orderDao.add(usid, totalPrice);
	}

	@Override
	public List<Orders> find(Integer pageNo, Integer pageSize) {
		IOrderDao orderDao = new OrderDaoImpl();
		return orderDao.find(pageNo, pageSize);
	}

	@Override
	public int getTotal(Integer oid) {
		IOrderDao orderDao = new OrderDaoImpl();
		return orderDao.getTotal(oid);
	}

	@Override
	public List<Orders> find(Map<String, String> params, Integer pageNo,
			Integer pageSize) {
		IOrderDao orderDao = new OrderDaoImpl();
		return orderDao.find(params, pageNo, pageSize);
	}

	@Override
	public List<Orders> find() {
		IOrderDao orderDao = new OrderDaoImpl();
		return orderDao.find();
	}

}
