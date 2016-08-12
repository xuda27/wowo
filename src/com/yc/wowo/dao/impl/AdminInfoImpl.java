package com.yc.wowo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.yc.wowo.dao.DBHelper;
import com.yc.wowo.dao.IAdminInfoDao;
import com.yc.wowo.entities.AdminInfo;

public class AdminInfoImpl implements IAdminInfoDao {

	@Override
	public List<AdminInfo> find() {
		DBHelper  db =new DBHelper();
		String sql = "";
		return null;
	}

	@Override
	public Integer add(String aname, String mark) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer del(String aid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AdminInfo login(String name, String pwd) {
		DBHelper db = new DBHelper();
		String sql = null;
		if(name.contentEquals("@")){
			sql = "select * from adminInfos where email=? and pwd = ? and status = 2";
		}else{
			sql = "select * from adminInfos where aid = ? and pwd = ? and status = 2";
		}
		
		List<Object> params = new ArrayList<Object>();
		params.add(name);
		params.add(pwd);
		return db.findByOne(sql, params, AdminInfo.class);
	}

	@Override
	public AdminInfo find(Integer aid) {
		DBHelper db = new DBHelper();
		List<Object> params = new ArrayList<Object>();
		params.add(aid);
		String sql = "select * from adminInfos where aid = ?";
		return db.findByOne(sql, params, AdminInfo.class);
	}

	@Override
	public List<AdminInfo> find(Integer pageNo, Integer pageSize) {
		DBHelper db =  new DBHelper();
		String sql = null;
		List<Object> params = new ArrayList<Object>();
		if(pageNo ==  null){
			sql = "select * from adminInfos order by aid desc";
		}else{
			params.add(pageNo*pageSize);
			params.add((pageNo-1)*pageSize);
			sql = "select * from (select a.*, rownum rn from(select * from adminInfos order by aid desc) a where rownum<?)where rn>? ";
		}
		return db.find(sql, params, AdminInfo.class);
	}
	
	@Override
	public List<AdminInfo> find(Integer rid, Integer pageNo, Integer pageSize) {
		DBHelper db = new DBHelper();
		List<Object> params = new ArrayList<Object>();
		String sql = null;
		if(rid == null && pageNo == null){
			sql = "select * from adminInfos order by aid desc";
		}else if(rid != null && pageNo == null){
			sql = "select * from adminInfos where rid = ? order by aid desc";
		}else if(rid != null && pageNo != null){
			params.add(pageNo*pageSize);
			params.add((pageNo-1)*pageSize);
			sql = "select * from (select a.*, rownum rn from(select * from adminInfos where rid = ? order by aid desc) a where rownum<?)where rn>? ";
		}else if(rid == null && pageNo != null){
			params.add(pageNo*pageSize);
			params.add((pageNo-1)*pageSize);
			sql = "select * from (select a.*, rownum rn from(select * from adminInfos order by aid desc) a where rownum<?)where rn>? ";
		}
		
		return db.find(sql, params, AdminInfo.class);
	}

	@Override
	public Integer update(String aid, String status, String mark) {
		DBHelper db = new DBHelper();
		String sql = null;
		List<Object> params = new ArrayList<Object>();
		if( aid.contains(",") && !aid.contains(" or") ){
			sql = "update adminInfo set status = ?, mark = ? where aid in("+aid+")";
			params.add(status);
			params.add(mark);
		}else{
			sql =  "update adminInfo set status = ?,mark = ? where aid = ?";
			params.add(status);
			params.add(mark);
			params.add(aid);
		}
		return db.doUpdate(sql, params);
	}
	
	@Override
	public Integer update(Integer aid, String photo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(Integer aid, String oldPwd, String newPwd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(String aid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(String aname, Integer rid, String tel, String photo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(String aid, String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
