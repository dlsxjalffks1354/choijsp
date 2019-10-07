package com.study.jsp.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.study.jsp.PageInfo.BPageInfo;
import com.study.jsp.PageInfo.NPageInfo;
import com.study.jsp.dto.BDto;
import com.study.jsp.dto.NDto;

public class NDao {

	private static NDao instance = new NDao();
	DataSource dataSource =null;
	
	int listCount =5; //한 페이지당 보여줄 게시물의 갯수
	int pageCount =10;//하단에 보여줄 페이지 리스트의 갯수
	
	
	public NDao() {
		try {
			//lookup 함수의 파라메터는 context.xml에 설정된
			// name(jdbc/Oracle11g)과 동일해야한다.
			Context context = new InitialContext();
			dataSource =(DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static NDao getInstance() {
		return instance;
	}
	
	public void write(String nName, String nTitle,String nContent, String nUser) 
	{
		Connection con=null;
		PreparedStatement pstmt =null;
		
		try {
			con=dataSource.getConnection();
			String query= "insert into mvc_board " +
			"(bId,bName,bTitle,bContent,bHit,bGroup,bStep,bIndent,bUser,type) " +
			" values" +
			"(mvc_board_seq.nextval,?,?,?,0,mvc_board_seq.currval,0,0,?,?)";
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, nName);
			pstmt.setString(2, nTitle);
			pstmt.setString(3, nContent);
			pstmt.setString(4, nUser);
			pstmt.setString(5, "notice");
			int rn = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt !=null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public ArrayList<NDto> nlist(int curPage) {
		
		ArrayList<NDto> dtos = new ArrayList<NDto>();
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet resultSet =null;
		
		int nStart = (curPage -1) * listCount +1;
		int nEnd = (curPage -1) * listCount + listCount;
		
		try {
			con = dataSource.getConnection();
			
			String query = "select * " +
							" from ( " +
							" 	select rownum num, A.* " +
							" 		from ( " +
							" 			select * " +
							" 			 from mvc_board where type = ? " +
							"				order by bGroup desc, bStep asc ) A " +
							" 				where rownum <= ? ) B " +
							" 			where B.num >= ? ";
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, "notice");
			pstmt.setInt(2, nEnd);
			pstmt.setInt(3, nStart);
			resultSet=pstmt.executeQuery();
			
			while(resultSet.next())
			{
				int nId = resultSet.getInt("bId");
				String nName = resultSet.getString("bName");
				String nTitle = resultSet.getString("bTitle");
				String nContent = resultSet.getString("bContent");
				Timestamp nDate = resultSet.getTimestamp("bDate");
				
				SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
	            Calendar cal = Calendar.getInstance();
	            String today = null;
	            today = formatter.format(cal.getTime());
	            Timestamp ts = Timestamp.valueOf(today);            
	            long diff = ts.getTime() - nDate.getTime();
	            
				int nHit = resultSet.getInt("bHit");
				int nGroup = resultSet.getInt("bGroup");
				int nStep = resultSet.getInt("bStep");
				int nIndent = resultSet.getInt("bIndent");
				String nUser =resultSet.getString("bUser");
				String type =resultSet.getString("type");
				NDto dto = new NDto(nId,nName,nTitle,nContent,nDate,
							nHit,nGroup,nStep,nIndent,nUser,type);
				 if((diff /(1000 * 60 * 60)) < 24) {
		               //System.out.println((diff / (1000 * 60 * 60)) + " 시간 전 작성");
		               dto.setnNew(true);
		           }
				dtos.add(dto);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			try {
				if(resultSet != null) resultSet.close();
				if(pstmt != null) pstmt.close();
				if(con !=null)con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return dtos;
	}
	
	public ArrayList<NDto> Search(int curPage, String searchoption, String search){
		
		ArrayList<NDto> dtos = new ArrayList<NDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "";
		String temp = "";
		
		int nStart = (curPage - 1) * listCount + 1;
		int nEnd = (curPage - 1) * listCount + listCount;
		
		try {			
			con = dataSource.getConnection();
			
			
			if(searchoption.equals("title")) {
				query = "select * " +
					   	   "	from ( "+
					   	   "		select rownum num, A.* " +
					   	   "			from ( " +
					   	   "				select * " +
					   	   "				from mvc_board where type = ? and btitle like ?"+
					       "			order by bGroup desc, bStep asc ) A "+
					   	   "	where rownum <= ? ) B " +
					       "where B.num >= ?";
				temp = search;
				search = "%"+temp+"%";
			}else if(searchoption.equals("content")) {
				query = "select * " +
					   	   "	from ( "+
					   	   "		select rownum num, A.* " +
					   	   "			from ( " +
					   	   "				select * " +
					   	   "				from mvc_board where type = ? and bcontent like ?"+
					       "			order by bGroup desc, bStep asc ) A "+
					   	   "	where rownum <= ? ) B " +
					       "where B.num >= ?";
				temp = search;
				search = "%"+temp+"%";
			}
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "notice");
			pstmt.setString(2, search);
			pstmt.setInt(3, nEnd);
			pstmt.setInt(4, nStart);
			
						
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int nId = rs.getInt("bId");
				String nName = rs.getString("bName");
				String nTitle = rs.getString("bTitle");
				String nContent = rs.getString("bContent");
				Timestamp nDate = rs.getTimestamp("bDate");
				
				SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
	            Calendar cal = Calendar.getInstance();
	            String today = null;
	            today = formatter.format(cal.getTime());
	            Timestamp ts = Timestamp.valueOf(today);            
	            long diff = ts.getTime() - nDate.getTime();
	            
				int nHit = rs.getInt("bHit");
				int nGroup = rs.getInt("bGroup");
				int nStep = rs.getInt("bStep");
				int nindent = rs.getInt("bindent");				
				String nUser = rs.getString("bUser");
				String type =rs.getString("type");
				
				NDto dto = new NDto(nId, nName, nTitle, nContent, nDate, nHit, nGroup, nStep, nindent, nUser,type);
				 
				if((diff /(1000 * 60 * 60)) < 24) {
		               //System.out.println((diff / (1000 * 60 * 60)) + " 시간 전 작성");
		               dto.setnNew(true);
		           }
				dtos.add(dto);
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {		
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return dtos;
	}
	
	public NPageInfo articlePage(int curPage) {
		
		Connection con =null;
		PreparedStatement pstmt=null;
		ResultSet resultSet =null;
		
		int listCount =5; //한 페이지당 보여줄 게시물의 갯수
		int pageCount =5; //하단에 보여줄 페이지 리스트의 갯수
		
		//총 게시물의 갯수
		int totalCount = 0;
		try {
			con = dataSource.getConnection();
			
			String query = "select count(*) as total from mvc_board where Type = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "notice");
			
			resultSet=pstmt.executeQuery();
			
			if(resultSet.next()) {
				totalCount = resultSet.getInt("total");
				System.out.println(totalCount);
			}
			System.out.println(totalCount);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet != null) resultSet.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		//총 페이지 수
		int totalPage = totalCount / listCount;
		if(totalCount % listCount > 0)
			totalPage++;
		
		//현재 페이지
		int myCurPage = curPage;
		if(myCurPage >curPage)
			myCurPage = curPage;
		if(myCurPage <1)
			myCurPage =1;
		
		//시작페이지
		int startPage =((myCurPage-1) / pageCount) * pageCount +1;
		
		//끝페이지
		int endPage = startPage +pageCount -1;
		if(endPage > totalPage)
			endPage = totalPage;
		
		NPageInfo pinfo = new NPageInfo();
		pinfo.setTotalCount(totalCount);
		pinfo.setListCount(listCount);
		pinfo.setTotalPage(totalPage);
		pinfo.setCurPage(curPage);
		pinfo.setPageCount(pageCount);
		pinfo.setStartPage(startPage);
		pinfo.setEndPage(endPage);
		
		return pinfo;
	}
	
	public NDto contentView(String strID)
	{
		upHit(strID);
		
		
		NDto dto = null;
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet resultSet =null;
		
		try
		{
			con=dataSource.getConnection();
			
			String query= "select * from mvc_board where bId= ?";
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(strID));
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next())
			{
				int nId = resultSet.getInt("bId");
				String nName = resultSet.getString("bName");
				String nTitle = resultSet.getString("bTitle");
				String nContent = resultSet.getString("bContent");
				Timestamp nDate = resultSet.getTimestamp("bDate");
				int nHit = resultSet.getInt("bHit");
				int nGroup = resultSet.getInt("bGroup");
				int nStep = resultSet.getInt("bStep");
				int nIndent = resultSet.getInt("bIndent");
				String nUser = resultSet.getString("bUser");
				String Type = resultSet.getString("type");
				
				dto = new NDto(nId,nName,nTitle,nContent,nDate,
						nHit,nGroup,nStep,nIndent,nUser,Type);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet != null) resultSet.close();
				if(pstmt != null) pstmt.close();
				if(con !=null)con.close();
				}catch(Exception e2) {
					e2.printStackTrace();
					}
				}
			return dto;
	}
	
	
	
	public void modify(String nId, String nName, String nTitle, String nContent)
	{
		Connection con = null;
		PreparedStatement pstmt=null;
		
		String query="update mvc_board" +
					"	set bName =?, " +
					"    bTitle = ?,  " +
					"    bContent = ? " +
					"where bId = ?";
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, nName);
			pstmt.setString(2, nTitle);
			pstmt.setString(3, nContent);
			pstmt.setString(4, nId);
			int rn =pstmt.executeUpdate();
			
		}catch(Exception e) 
		{
			e.printStackTrace();
		}finally {
			try {
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
		}catch(Exception e2)
			{
			e2.printStackTrace();
			}
		}
	}
	
	private void upHit(String nId) 
	{
		Connection con = null;
		PreparedStatement pstmt=null;
		
		try {
			con = dataSource.getConnection();
			String query = "update mvc_board set bHit = bHit + 1 where bId= ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, nId);
			
			int rn =pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();

			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	public void delete(String nId)
	{
		Connection con = null;
		PreparedStatement pstmt=null;
		try {
			con = dataSource.getConnection();
			String query = "delete from mvc_board where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(nId));
			int rn = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();

			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public NPageInfo SearchPage(int curPage, String searchoption, String search) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int listCount = 10;
		int pageCount = 10;
		
		// 총 게시물의 갯수
		int totalCount = 0;
		
		String query = "";
		String temp;
		
		try {			
			con = dataSource.getConnection();
			
			if(searchoption.equals("title")) {
				query = "select count(*) as total from mvc_board where type = ? and btitle like ?";
				temp = search;
				search = "%"+temp+"%";
			}else if(searchoption.equals("content")) {
				query = "select count(*) as total from mvc_board where type = ? and bcontent like ?";
				temp = search;
				search = "%"+temp+"%";
			}
			
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "notice");
			pstmt.setString(2, search);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				totalCount = rs.getInt("total");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {			
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		int totalPage = totalCount / listCount;
		if((totalCount % listCount) > 0) {
			totalPage ++;
		}
		
		int myCurPage = curPage;
		if(myCurPage > totalPage) {
			myCurPage = totalPage;
		}
		if(myCurPage < 1) {
			myCurPage = 1;
		}
		
		// 시작 페이지
		int startPage = ((myCurPage - 1) / pageCount) * pageCount + 1;
		
		// 끝 페이지
		int endPage = startPage + pageCount - 1;
		if(endPage > totalPage) {
			endPage = totalPage;
		}
		
		NPageInfo pinfo = new NPageInfo();
		pinfo.setTotalCount(totalCount);
		pinfo.setListCount(listCount);
		pinfo.setTotalPage(totalPage);
		pinfo.setCurPage(curPage);
		pinfo.setPageCount(pageCount);
		pinfo.setStartPage(startPage);
		pinfo.setEndPage(endPage);
		
		return pinfo;
	}
	
	
}
