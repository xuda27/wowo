package com.yc.wowo.entities;

import java.io.Serializable;

public class Orders implements Serializable{
	private Integer oid;
	private String osdate;
	private Integer usid;
	private Integer totalPrice;
	private Integer status;
	
	private String uname;

	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public String getosdate() {
		return osdate;
	}

	public void setosdate(String osdate) {
		this.osdate = osdate;
	}

	public Integer getUsid() {
		return usid;
	}

	public void setUsid(Integer usid) {
		this.usid = usid;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getStatus() {
		return status;
	}
	
	public String getStatusStr(){
		if(status == 1){
			return "已支付";
		}else if( status == 0){
			return "未支付";
		}else{
			return "错误";
		}
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	@Override
	public String toString() {
		return "Orders [oid=" + oid + ", osdate=" + osdate + ", usid=" + usid
				+ ", totalPrice=" + totalPrice + ", status=" + status
				+ ", uname=" + uname + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((osdate == null) ? 0 : osdate.hashCode());
		result = prime * result + ((oid == null) ? 0 : oid.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((totalPrice == null) ? 0 : totalPrice.hashCode());
		result = prime * result + ((uname == null) ? 0 : uname.hashCode());
		result = prime * result + ((usid == null) ? 0 : usid.hashCode());
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
		Orders other = (Orders) obj;
		if (osdate == null) {
			if (other.osdate != null)
				return false;
		} else if (!osdate.equals(other.osdate))
			return false;
		if (oid == null) {
			if (other.oid != null)
				return false;
		} else if (!oid.equals(other.oid))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (totalPrice == null) {
			if (other.totalPrice != null)
				return false;
		} else if (!totalPrice.equals(other.totalPrice))
			return false;
		if (uname == null) {
			if (other.uname != null)
				return false;
		} else if (!uname.equals(other.uname))
			return false;
		if (usid == null) {
			if (other.usid != null)
				return false;
		} else if (!usid.equals(other.usid))
			return false;
		return true;
	}

	public Orders(Integer oid, String osdate, Integer usid, Integer totalPrice,
			Integer status, String uname) {
		super();
		this.oid = oid;
		this.osdate = osdate;
		this.usid = usid;
		this.totalPrice = totalPrice;
		this.status = status;
		this.uname = uname;
	}

	public Orders() {
		super();
	}
	
	
	
	
	
}
