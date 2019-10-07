package com.study.jsp.dto;

import java.sql.Timestamp;

public class MDto {
	
	private String id;
	private String pw;
	private String name;
	private String nickname;
	private String eMail;
	private Timestamp rDate;
	private String address;
	private String manager;
	private String ban;
	private int mContent;	
	private int mReply;		
	private String bId;
	private String bCount;
	
	public MDto() 
	{
		
	}
	
	
	
	public MDto(String id, String pw, String name, String nickname, 
			String eMail, Timestamp rDate, String address,
			String manager, String ban) 
	{
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.nickname = nickname;
		this.eMail = eMail;
		this.rDate = rDate;
		this.address = address;
		this.manager = manager;
		this.ban = ban;
	}
	
	public MDto(String id, String pw, String name, String nickname, 
			String eMail, Timestamp rDate, String address,
			String manager, String ban,int mContent,int mReply) 
	{
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.nickname = nickname;
		this.eMail = eMail;
		this.rDate = rDate;
		this.address = address;
		this.manager = manager;
		this.ban = ban;
		this.mContent=mContent;
		this.mReply=mReply;
	}
	
	
	public MDto(String id, String pw, String name, String nickname, String eMail, Timestamp rDate, String address,
			String manager, String ban, int mContent, int mReply, String bId, String bCount) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.nickname = nickname;
		this.eMail = eMail;
		this.rDate = rDate;
		this.address = address;
		this.manager = manager;
		this.ban = ban;
		this.mContent = mContent;
		this.mReply = mReply;
		this.bId = bId;
		this.bCount = bCount;
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



	public int getmContent() {
		return mContent;
	}



	public void setmContent(int mContent) {
		this.mContent = mContent;
	}



	public int getmReply() {
		return mReply;
	}



	public void setmReply(int mReply) {
		this.mReply = mReply;
	}



	public String getbId() {
		return bId;
	}



	public void setbId(String bId) {
		this.bId = bId;
	}



	public String getbCount() {
		return bCount;
	}



	public void setbCount(String bCount) {
		this.bCount = bCount;
	}
	
	
	
}
