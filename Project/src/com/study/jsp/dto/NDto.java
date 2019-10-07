package com.study.jsp.dto;

import java.sql.Timestamp;

public class NDto {
	
	int nId;
	String nName;
	String nTitle;
	String nContent;
	String nUser;
	Timestamp nDate;
	int nHit;
	int nGroup;
	int nStep;
	int nIndent;
	boolean nNew;
	String type;
	
	
	public NDto() 
	{

		
	}

	
	
	public NDto(int nId, String nName, String nTitle, String nContent, 
			Timestamp nDate, int nHit, int nGroup, int nStep,int nIndent,String nUser) 
	{
		super();
		this.nId = nId;
		this.nName = nName;
		this.nTitle = nTitle;
		this.nContent = nContent;
		this.nDate = nDate;
		this.nHit = nHit;
		this.nGroup = nGroup;
		this.nStep = nStep;
		this.nIndent = nIndent;
		this.nUser=nUser;
		this.nNew=false;
	}
	
	public NDto(int nId, String nName, String nTitle, String nContent, 
			Timestamp nDate, int nHit, int nGroup, int nStep,int nIndent,String nUser,String type) 
	{
		super();
		this.nId = nId;
		this.nName = nName;
		this.nTitle = nTitle;
		this.nContent = nContent;
		this.nDate = nDate;
		this.nHit = nHit;
		this.nGroup = nGroup;
		this.nStep = nStep;
		this.nIndent = nIndent;
		this.nUser=nUser;
		this.nNew=false;
		this.type=type;
	}



	public int getnId() {
		return nId;
	}



	public void setnId(int nId) {
		this.nId = nId;
	}



	public String getnName() {
		return nName;
	}



	public void setnName(String nName) {
		this.nName = nName;
	}



	public String getnTitle() {
		return nTitle;
	}



	public void setnTitle(String nTitle) {
		this.nTitle = nTitle;
	}



	public String getnContent() {
		return nContent;
	}



	public void setnContent(String nContent) {
		this.nContent = nContent;
	}



	public String getnUser() {
		return nUser;
	}



	public void setnUser(String nUser) {
		this.nUser = nUser;
	}



	public Timestamp getnDate() {
		return nDate;
	}



	public void setnDate(Timestamp nDate) {
		this.nDate = nDate;
	}



	public int getnHit() {
		return nHit;
	}



	public void setnHit(int nHit) {
		this.nHit = nHit;
	}



	public int getnGroup() {
		return nGroup;
	}



	public void setnGroup(int nGroup) {
		this.nGroup = nGroup;
	}



	public int getnStep() {
		return nStep;
	}



	public void setnStep(int nStep) {
		this.nStep = nStep;
	}



	public int getnIndent() {
		return nIndent;
	}



	public void setnIndent(int nIndent) {
		this.nIndent = nIndent;
	}



	public boolean getnNew() {
		return nNew;
	}



	public void setnNew(boolean nNew) {
		this.nNew = nNew;
	}

	
	
	
	
	

}
