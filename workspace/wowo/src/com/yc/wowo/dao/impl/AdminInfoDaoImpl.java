package com.yc.wowo.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yc.wowo.dao.DBHelper;
import com.yc.wowo.dao.IAdminInfoDao;
import com.yc.wowo.entities.AdminInfo;

public class AdminInfoDaoImpl implements IAdminInfoDao{

	@Override
	public AdminInfo login(String name, String pwd,String rid) {
		DBHelper db=new DBHelper();
		String sql=null;
		if(name.contains("@")){  //说明是登录邮箱
			sql="select * from adminInfos where email=? and pwd=? and status=2 and rid=? ";
		}else{
			sql="select * from adminInfos where aname=? and pwd=? and status=2 and rid=? ";
		}
		List<Object> params = new ArrayList<Object>();
		params.add(name);
		params.add(pwd);
		params.add(rid);
		return db.findByOne(sql, params, AdminInfo.class);
	}
	
	
	
	@Override
	public AdminInfo find(Integer aid) {
		DBHelper db=new DBHelper();
		List<Object> params = new ArrayList<Object>();
		params.add(aid);
		return db.findByOne("select * from adminInfos where aid=?", params, AdminInfo.class);
	}

	
	@Override
	public List<AdminInfo> find(Integer pageNo, Integer pageSize) {
		DBHelper db=new DBHelper();
		List<Object> params = new ArrayList<Object>();
		String sql=null;
		if(pageNo==null){
			sql="select * from adminInfos where status !=3 order by aid desc";
		}else{
			params.add(pageNo*pageSize);
			params.add((pageNo-1)*pageSize);
			sql="select * from(select a.*,rownum rn from("
					+ "select * from adminInfos where status !=3 order by aid desc )a where rownum<=? ) where rn>?";
		}
		return db.find(sql, params,AdminInfo.class);
	}

	@Override
	public List<AdminInfo> find(Integer rid, Integer pageNo, Integer pageSize) {
		DBHelper db=new DBHelper();
		String sql=null;
		List<Object> params = new ArrayList<Object>();
		if(rid==null && pageNo==null){
			sql="select * from adminInfos order by desc";
		}else if(rid!=null && pageNo==null){
			sql="select * from adminInfos where rid=? order by aid desc";
			params.add(rid);
		}else if(rid!=null && pageNo!=null){
			sql="select * from (select a.*,rownum rn from ("
					+ "select * from adminInfos where rid=? order by aid desc) a where rownum<=?)where rn>?";
			params.add(rid);
			params.add(pageNo*pageSize);
			params.add((pageNo-1)*pageSize);
		}else if(rid==null && pageNo!=null){
			sql="select * from (select a.*,rownum rn from ("
					+ "select * from adminInfos order by aid desc) a where rownum<=?)where rn>?";
			params.add(pageNo*pageSize);
			params.add((pageNo-1)*pageSize);
		}
		return db.find(sql, params, AdminInfo.class);
	}

	@Override
	public Integer update(String aid, String status, String mark) {
		DBHelper db=new DBHelper();
		String sql=null;
		List<Object> params = new ArrayList<Object>();
		if(aid.contains(",") && !aid.contains(" or")){
			sql="update adminInfo set status=?,mark=? where aid in("+aid+")";
			params.add(status);
			params.add(mark);
		}else{
			sql="update adminInfo set status=?,mark=? where aid=?";
			params.add(status);
			params.add(mark);
			params.add(aid);
		}
		return db.doUpdate(sql,params);
	}

	@Override
	public Integer update(Integer aid, String photo) {
		DBHelper db=new DBHelper();
		String sql="update adminInfo set photo=? where aid=?";
		List<Object> params = new ArrayList<Object>();
		params.add(photo);
		params.add(aid);
		return db.doUpdate(sql, params);
	}

	@Override
	public Integer update(Integer aid, String oldPwd, String newPwd) {
		DBHelper db=new DBHelper();
		String sql="update adminInfo set pwd=? where aid=? and pwd=?";
		List<Object> params = new ArrayList<Object>();
		params.add(newPwd);
		params.add(aid);
		params.add(oldPwd);
		return db.doUpdate(sql,params);
	}

	@Override
	public Integer update(String aid) {
		DBHelper db=new DBHelper();
		String sql=null;
		if(aid.contains(",") && !aid.contains(" or")){
			sql="update adminInfo set pwd='aaa' where aid in("+aid+")";
		}else{
			sql="update adminInfo set pwd='aaa' where aid=?";
		}
		List<Object> params = new ArrayList<Object>();
		params.add(aid);
		return db.doUpdate(sql,params);
	}

	@Override
	public Integer update(String aname, String rid, String tel, String photo,String aid) {
		DBHelper db=new DBHelper();
		List<Object> params = new ArrayList<Object>();
		String sql="update adminInfo set aid=aid";
		if(aname!=null){
			sql+=",aname=?";
			params.add(aname);
		}
		
		if(rid!=null){
			sql+=",rid=?";
			params.add(rid);
		}
		
		if(tel!=null){
			sql+=",tel=?";
			params.add(tel);
		}
		
		if(photo!=null){
			sql+=",photo=? ";
			params.add(photo);
		}
		sql+=" where aid=?";
		params.add(aid);
		return db.doUpdate(sql,params);
	}

	@Override
	public Integer update(String aid, String email) {
		DBHelper db=new DBHelper();
		String sql=null;
		sql="update adminInfo set email=? where aid=?";
		List<Object> params = new ArrayList<Object>();
		params.add(email);
		params.add(aid);
		return db.doUpdate(sql, params);
	}

	@Override
	public Integer add(String aname, String pwd, String rid, String email,
			String tel, String photo) {
		DBHelper db=new DBHelper();
		String sql=null;
		sql="insert into adminInfo values(seq_adminInfo_aid.nextval,?,?,?,?,?,?,0,'')";
		List<Object> params = new ArrayList<Object>();
		params.add(aname);
		params.add(rid);
		params.add(pwd);
		params.add(email);
		params.add(photo);
		params.add(tel);
		return db.doUpdate(sql, params);
	}

	/**
	 * 冻结
	 */
	@Override
	public Integer del(String aid) {
		DBHelper db=new DBHelper();
		String sql=null;
		List<Object> params=new ArrayList<Object>();
		if(aid.contains(",") && !aid.contains(" or")){
			sql="update adminInfo set status=3 where aid in("+aid+")";
		}else{
			sql="update adminInfo set status=3 where aid=?";
			params.add(aid);
		}
		return db.doUpdate(sql, params);
	}

	@Override
	public int getTotal(Integer rid) {
		DBHelper db = new DBHelper();
		String sql = null;
		List<Object> params = new ArrayList<Object>();
		if(rid == null){
			sql = "select count(aid) from adminInfo";
		}else{
			sql = "select count(aid) from adminInfo where rid = ?";
			params.add(rid);
		}
		return db.findByOne(sql, params);
	}

	@Override
	public int getTotal(String aname, String email) {
		if("".equals(aname) || "".equals(email) || aname==null || email==null){
			return 0;
		}
		DBHelper db = new DBHelper();
		String sql = "select count(aid) from adminInfo where aname = ? and email = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(aname);
		params.add(email);
		return db.findByOne(sql, params);
	}
	
	@Override
	public List<AdminInfo> find(Map<String, String> params, Integer pageNo,
			Integer pageSize) {
		DBHelper db = new DBHelper();
		List<Object> param = new ArrayList<Object>();
		String sql = "　select * from adminInfos";
		if(params!=null && params.size()>0){
			sql+=" where 1=1";
			Set<String> keys = params.keySet();
			for(String key : keys){
				sql+=" and "+key +"?"; //and rid = ?
				param.add(params.get(key));
			}
		}
		sql += " order by aid desc";
		if(pageNo != null){
			sql = "select * from (select a.*,rownum rn from("+sql+") a where rownum<=?)where rn > ?";
			param.add(pageNo*pageSize);
			param.add((pageNo-1)*pageSize);
		}
		return db.find(sql, param, AdminInfo.class);
	}

	@Override
	public Integer update1(String email, String newpwd) {
		String sql = "update adminInfo set pwd =? where email = ?";
		List<Object> param = new ArrayList<Object>();
		param.add(newpwd);
		param.add(email);
		DBHelper db = new DBHelper();
		
		return db.doUpdate(sql, param);
	}



	@Override
	public List<AdminInfo> find() {
		String sql = "select * from adminInfos";
		DBHelper db = new DBHelper();
		return db.find(sql, null, AdminInfo.class);
	}

	

}
