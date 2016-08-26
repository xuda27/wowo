package com.yc.wowo.dao;

import java.util.List;

import com.yc.wowo.entities.AdminInfo;
import com.yc.wowo.entities.GoodsType;
import com.yc.wowo.entities.Shop;

public interface IShop {
	public List<GoodsType> find();
	
	public List<AdminInfo> find(Integer rid);
	
	public Integer add(String sname, String aname, String tid, String prov, String city, String area
			,String addr, String tel, String text);
	
	public List<Shop> find(Integer pageNo, Integer pageSize);
	
	public List<Shop> select();
}
