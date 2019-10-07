package com.study.jsp.dto;

import java.sql.Timestamp;

public class RDto {
	
	int bId;
	String bName;
	String bTitle;
	String bContent;
	String bUser;
	Timestamp bDate;
	int bHit;
	int bGroup;
	int bStep;
	int bIndent;
	String type;
	String fileName;
	boolean bNew;
	int bLike;
	
	
	public RDto() 
	{

		
	}

	
	
	public RDto(int bId, String bName, String bTitle, String bContent, 
			Timestamp bDate, int bHit, int bGroup, int bStep,int bIndent,String bUser) 
	{
		super();
		this.bId = bId;
		this.bName = bName;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bDate = bDate;
		this.bHit = bHit;
		this.bGroup = bGroup;
		this.bStep = bStep;
		this.bIndent = bIndent;
		this.bUser=bUser;
		this.bNew=false;
	}
	
	public RDto(int bId, String bName, String bTitle, String bContent, 
			Timestamp bDate, int bHit, int bGroup, int bStep,int bIndent,String bUser,String type,String fileName,int bLike) 
	{
		super();
		this.bId = bId;
		this.bName = bName;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bDate = bDate;
		this.bHit = bHit;
		this.bGroup = bGroup;
		this.bStep = bStep;
		this.bIndent = bIndent;
		this.bUser=bUser;
		this.bNew=false;
		this.type=type;
		this.fileName=fileName;
		this.bLike=bLike;
	}
	
	public RDto(int bId, String bName, String bTitle, String bContent, 
			Timestamp bDate, int bHit, int bGroup, int bStep,int bIndent,String bUser,String type) 
	{
		super();
		this.bId = bId;
		this.bName = bName;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bDate = bDate;
		this.bHit = bHit;
		this.bGroup = bGroup;
		this.bStep = bStep;
		this.bIndent = bIndent;
		this.bUser=bUser;
		this.bNew=false;
		this.type=type;
	}
	
	public RDto(int bId, String bName, String bTitle, String bContent, 
			Timestamp bDate, int bHit, int bGroup, int bStep,int bIndent,String bUser,String type,int bLike) 
	{
		super();
		this.bId = bId;
		this.bName = bName;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bDate = bDate;
		this.bHit = bHit;
		this.bGroup = bGroup;
		this.bStep = bStep;
		this.bIndent = bIndent;
		this.bUser=bUser;
		this.bNew=false;
		this.type=type;
		this.bLike=bLike;
	}



	public int getbId() {
		return bId;
	}


	public void setbId(int bId) {
		this.bId = bId;
	}


	public String getbName() {
		return bName;
	}


	public void setbName(String bName) {
		this.bName = bName;
	}


	public String getbTitle() {
		return bTitle;
	}


	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}


	public String getbContent() {
		return bContent;
	}


	public void setbContent(String bContent) {
		this.bContent = bContent;
	}


	public Timestamp getbDate() {
		return bDate;
	}


	public void setbDate(Timestamp bDate) {
		this.bDate = bDate;
	}


	public int getbHit() {
		return bHit;
	}


	public void setbHit(int bHit) {
		this.bHit = bHit;
	}


	public int getbGroup() {
		return bGroup;
	}


	public void setbGroup(int bGroup) {
		this.bGroup = bGroup;
	}


	public int getbStep() {
		return bStep;
	}


	public void setbStep(int bStep) {
		this.bStep = bStep;
	}


	public int getbIndent() {
		return bIndent;
	}


	public void setbIndent(int bIndent) {
		this.bIndent = bIndent;
	}



	public String getbUser() {
		return bUser;
	}



	public void setbUser(String bUser) {
		this.bUser = bUser;
	}



	public boolean getbNew() {
		return bNew;
	}



	public void setbNew(boolean bNew) {
		this.bNew = bNew;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public String getFileName() {
		return fileName;
	}



	public void setFileName(String fileName) {
		this.fileName = fileName;
	}



	public int getbLike() {
		return bLike;
	}



	public void setbLike(int bLike) {
		this.bLike = bLike;
	}

	
	
	
	
	
	
	
	
	

}
