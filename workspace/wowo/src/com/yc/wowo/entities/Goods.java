package com.yc.wowo.entities;

import java.io.Serializable;

public class Goods implements Serializable{
	private static final long serialVersionUID = 127537045368401723L;
	private Integer gid;
	private String gname;
	private String des;
	private Integer price;
	private Integer tid;
	private String pic;
	private Integer spid;
	
	private String sname;
	private String aname;
	private String flag;
	private String mark;
	private String temp;
	public Integer getGid() {
		return gid;
	}
	public void setGid(Integer gid) {
		this.gid = gid;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public Integer getSpid() {
		return spid;
	}
	public void setSpid(Integer spid) {
		this.spid = spid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Goods [gid=" + gid + ", gname=" + gname + ", des=" + des
				+ ", price=" + price + ", tid=" + tid + ", pic=" + pic
				+ ", spid=" + spid + ", sname=" + sname + ", aname=" + aname
				+ ", flag=" + flag + ", mark=" + mark + ", temp=" + temp + "]";
	}
	public Goods(Integer gid, String gname, String des, int price,
			int tid, String pic, Integer spid, String sname, String aname,
			String flag, String mark, String temp) {
		super();
		this.gid = gid;
		this.gname = gname;
		this.des = des;
		this.price = price;
		this.tid = tid;
		this.pic = pic;
		this.spid = spid;
		this.sname = sname;
		this.aname = aname;
		this.flag = flag;
		this.mark = mark;
		this.temp = temp;
	}
	public Goods() {
		super();
	}
	
	
	
	
	
}
