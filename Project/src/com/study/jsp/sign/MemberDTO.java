package com.study.jsp.sign;

import java.sql.Timestamp;

public class MemberDTO 
{
	private String id;
	private String pw;
	private String name;
	private String nickname;
	private String eMail;
	private Timestamp rDate;
	private String address;
	private String manager;
	private String ban;
	
	public MemberDTO() 
	{
		
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public Timestamp getrDate() {
		return rDate;
	}

	public void setrDate(Timestamp rDate) {
		this.rDate = rDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}


	public String getBan() {
		return ban;
	}


	public void setBan(String ban) {
		this.ban = ban;
	}
	
	

	
	
	
	
	
	
	
}
