package com.yc.wowo.entities;

import java.io.Serializable;
/**
 * 管理员实体类
 * @author Eden
 *
 */
public class Roles implements Serializable {
	private static final long serialVersionUID = 4711554600960695172L;
	private Integer rid; //角色编号
	private String rname;//角色名称
	private String mark; //角色描述
	private String status; //状态
	
	@Override
	public String toString() {
		return "Roles [rid=" + rid + ", rname=" + rname + ", mark=" + mark
				+ ", status=" + status + "]";
	}
	
	public Integer getRid() {
		return rid;
	}
	
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	
	public String getRname() {
		return rname;
	}
	
	public void setRname(String rname) {
		this.rname = rname;
	}
	
	public String getMark() {
		return mark;
	}
	
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mark == null) ? 0 : mark.hashCode());
		result = prime * result + ((rid == null) ? 0 : rid.hashCode());
		result = prime * result + ((rname == null) ? 0 : rname.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Roles other = (Roles) obj;
		if (mark == null) {
			if (other.mark != null)
				return false;
		} else if (!mark.equals(other.mark))
			return false;
		if (rid == null) {
			if (other.rid != null)
				return false;
		} else if (!rid.equals(other.rid))
			return false;
		if (rname == null) {
			if (other.rname != null)
				return false;
		} else if (!rname.equals(other.rname))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
	
	public Roles(Integer rid, String rname, String mark, String status) {
		super();
		this.rid = rid;
		this.rname = rname;
		this.mark = mark;
		this.status = status;
	}
	
	public Roles() {
		super();
	}
	
	
	
	
}
