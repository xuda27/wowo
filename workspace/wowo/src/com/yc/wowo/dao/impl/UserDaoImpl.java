package com.yc.wowo.dao.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yc.wowo.dao.DBHelper;
import com.yc.wowo.dao.IUserInfo;
import com.yc.wowo.entities.UserInfo;

public class UserDaoImpl implements IUserInfo {
	DBHelper db = new DBHelper();
	
	@Override
	public Integer add(String email, String uname, String pwd,String tel, String prov,
			String city, String area) {
		String sql = "insert into userInfo(usid,email,uname,pwd,tel,prov,city,area,status) values(seq_userInfo_usid.nextval,?,?,?,?,?,?,?,1)";
		List<Object> list = new LinkedList<Object>();
		list.add(email);
		list.add(uname);
		list.add(pwd);
		list.add(tel);
		list.add(prov);
		list.add(city);
		list.add(area);
		return db.doUpdate(sql, list);
	}
	
	@Override
	public List<UserInfo> find() {
		String sql = "select * from userInfo";
		return db.find(sql, null, UserInfo.class);
	}
	
	@Override
	public List<UserInfo> find(Integer pageNo, Integer pageSize) {
		List<Object> params = new LinkedList<Object>();
		params.add(pageNo*pageSize);
		params.add((pageNo-1)*pageSize);
		String sql = "select * from(select a.*,rownum as rn from("+
				"select usid,uname,tel,prov,city,area,status from userInfo where status = 1 order by usid asc) a where rownum<=?) where rn>?";
		return db.find(sql, params, UserInfo.class);
	}

	@Override
	public List<UserInfo> find(Map<String, String> map, Integer pageNo,
			Integer pageSize) {
		List<Object> params = new LinkedList<Object>();
		String sql = "select * from userInfo where 1=1";
		if(map != null && map.size() > 0){
			Set<String> keys = map.keySet();
			for(String key: keys){
				sql += " and "+ key + "?";
				params.add(map.get(key));
			}
		}
		sql += " order by usid desc";
		if(pageNo != null){
			sql = "select * from (select a.*,rownum rn from("+sql+") a where rownum<=?)where rn > ?";
			params.add(pageNo*pageSize);
			params.add((pageNo-1)*pageSize);
		}
		System.out.println(sql);
		return db.find(sql, params, UserInfo.class);
	}

	@Override
	public Integer find(String uname, String pwd) {
		String sql = "select count(*) from userInfo where uname =? and pwd = ?";
		List<Object> params = new LinkedList<Object>();
		params.add(uname);
		params.add(pwd);
		return db.findByOne(sql, params);
	}

	@Override
	public UserInfo select(String uname, String pwd) {
		String sql = "select * from userInfo where uname =? and pwd = ?";
		List<Object> params = new LinkedList<Object>();
		params.add(uname);
		params.add(pwd);
		return db.findByOne(sql, params, UserInfo.class);
	}
	
	
}
