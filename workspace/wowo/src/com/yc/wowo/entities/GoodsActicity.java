package com.yc.wowo.entities;

import java.io.Serializable;
import java.util.Date;

public class GoodsActicity implements Serializable{
	private static final long serialVersionUID = -8196622105114496267L;
	private Integer gaid;
	private Integer gid;
	private double aprice;
	private Integer personNum;
	private String title;
	private Date startDate;
	private Date endDate;
	private String prompt; //提示
	private String content;
	
	private String gname;
	private String sname;
	private String aname;
	private String temp;
	private String flag;
	private String mark;
	
	@Override
	public String toString() {
		return "GoodsActicity [gaid=" + gaid + ", gid=" + gid + ", aprice="
				+ aprice + ", personNum=" + personNum + ", title=" + title
				+ ", startDate=" + startDate + ", endDate=" + endDate
				+ ", prompt=" + prompt + ", content=" + content + ", gname="
				+ gname + ", sname=" + sname + ", aname=" + aname + ", temp="
				+ temp + ", flag=" + flag + ", mark=" + mark + "]";
	}

	public Integer getGaid() {
		return gaid;
	}

	public void setGaid(Integer gaid) {
		this.gaid = gaid;
	}

	public Integer getGid() {
		return gid;
	}

	public void setGid(Integer gid) {
		this.gid = gid;
	}

	public double getAprice() {
		return aprice;
	}

	public void setAprice(double aprice) {
		this.aprice = aprice;
	}

	public Integer getPersonNum() {
		return personNum;
	}

	public void setPersonNum(Integer personNum) {
		this.personNum = personNum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
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

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aname == null) ? 0 : aname.hashCode());
		long temp;
		temp = Double.doubleToLongBits(aprice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((flag == null) ? 0 : flag.hashCode());
		result = prime * result + ((gaid == null) ? 0 : gaid.hashCode());
		result = prime * result + ((gid == null) ? 0 : gid.hashCode());
		result = prime * result + ((gname == null) ? 0 : gname.hashCode());
		result = prime * result + ((mark == null) ? 0 : mark.hashCode());
		result = prime * result
				+ ((personNum == null) ? 0 : personNum.hashCode());
		result = prime * result + ((prompt == null) ? 0 : prompt.hashCode());
		result = prime * result + ((sname == null) ? 0 : sname.hashCode());
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result
				+ ((this.temp == null) ? 0 : this.temp.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		GoodsActicity other = (GoodsActicity) obj;
		if (aname == null) {
			if (other.aname != null)
				return false;
		} else if (!aname.equals(other.aname))
			return false;
		if (Double.doubleToLongBits(aprice) != Double
				.doubleToLongBits(other.aprice))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (flag == null) {
			if (other.flag != null)
				return false;
		} else if (!flag.equals(other.flag))
			return false;
		if (gaid == null) {
			if (other.gaid != null)
				return false;
		} else if (!gaid.equals(other.gaid))
			return false;
		if (gid == null) {
			if (other.gid != null)
				return false;
		} else if (!gid.equals(other.gid))
			return false;
		if (gname == null) {
			if (other.gname != null)
				return false;
		} else if (!gname.equals(other.gname))
			return false;
		if (mark == null) {
			if (other.mark != null)
				return false;
		} else if (!mark.equals(other.mark))
			return false;
		if (personNum == null) {
			if (other.personNum != null)
				return false;
		} else if (!personNum.equals(other.personNum))
			return false;
		if (prompt == null) {
			if (other.prompt != null)
				return false;
		} else if (!prompt.equals(other.prompt))
			return false;
		if (sname == null) {
			if (other.sname != null)
				return false;
		} else if (!sname.equals(other.sname))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (temp == null) {
			if (other.temp != null)
				return false;
		} else if (!temp.equals(other.temp))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	public GoodsActicity(Integer gaid, Integer gid, double aprice,
			Integer personNum, String title, Date startDate, Date endDate,
			String prompt, String content, String gname, String sname,
			String aname, String temp, String flag, String mark) {
		super();
		this.gaid = gaid;
		this.gid = gid;
		this.aprice = aprice;
		this.personNum = personNum;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.prompt = prompt;
		this.content = content;
		this.gname = gname;
		this.sname = sname;
		this.aname = aname;
		this.temp = temp;
		this.flag = flag;
		this.mark = mark;
	}

	public GoodsActicity() {
		super();
	}
	
	
}

