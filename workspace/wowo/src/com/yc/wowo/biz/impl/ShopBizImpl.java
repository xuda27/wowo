package com.yc.wowo.biz.impl;

import java.util.List;

import com.yc.wowo.biz.IShopBiz;
import com.yc.wowo.dao.IShop;
import com.yc.wowo.dao.impl.ShopDaoImpl;
import com.yc.wowo.entities.AdminInfo;
import com.yc.wowo.entities.GoodsType;
import com.yc.wowo.entities.Shop;

public class ShopBizImpl implements IShopBiz {
	IShop shopDao = new ShopDaoImpl();

	@Override
	public List<GoodsType> find() {
		return shopDao.find();
	}

	@Override
	public List<AdminInfo> find(Integer rid) {
		return shopDao.find(rid);
	}

	@Override
	public Integer add(String sname, String aid, String tid, String prov,
			String city, String area, String addr, String tel, String text) {
		return shopDao.add(sname, aid, tid, prov, city, area, addr, tel, text);
	}

	@Override
	public List<Shop> find(Integer pageNo, Integer pageSize) {
		return shopDao.find(pageNo, pageSize);
	}

	@Override
	public List<Shop> select() {
		
		return shopDao.select();
	}

}
