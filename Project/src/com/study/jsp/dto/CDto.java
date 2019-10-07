package com.study.jsp.dto;

import java.sql.Timestamp;

public class CDto {
	
	private int cnum;
	private int cboard;
	private String cid;
	private String cnickname;
	private Timestamp cDate;
	private int cparent;
	private String ccontent;
	private int clevel;
	private String Ptitle;
	
	

	public CDto() 
	{
		
	}
	

	public CDto(int cnum, int cboard, String cid, String cnickname, Timestamp cDate, int cparent, String ccontent
			) {
		super();
		this.cnum = cnum;
		this.cboard = cboard;
		this.cid = cid;
		this.cnickname = cnickname;
		this.cDate = cDate;
		this.cparent = cparent;
		this.ccontent = ccontent;
	}


	public int getCnum() {
		return cnum;
	}

	public void setCnum(int cnum) {
		this.cnum = cnum;
	}

	public int getCboard() {
		return cboard;
	}

	public void setCboard(int cboard) {
		this.cboard = cboard;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCnickname() {
		return cnickname;
	}

	public void setCnickname(String cnickname) {
		this.cnickname = cnickname;
	}

	public Timestamp getcDate() {
		return cDate;
	}

	public void setcDate(Timestamp cDate) {
		this.cDate = cDate;
	}

	public int getCparent() {
		return cparent;
	}

	public void setCparent(int cparent) {
		this.cparent = cparent;
	}

	public String getCcontent() {
		return ccontent;
	}

	public void setCcontent(String ccontent) {
		this.ccontent = ccontent;
	}


	public int getClevel() {
		return clevel;
	}



	public void setClevel(int clevel) {
		this.clevel = clevel;
	}


	public String getPtitle() {
		return Ptitle;
	}


	public void setPtitle(String ptitle) {
		Ptitle = ptitle;
	}


	
	
	
	
	
	
}
