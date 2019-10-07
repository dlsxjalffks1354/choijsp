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

import com.study.jsp.dto.PeriodAsearchDto;
import com.study.jsp.dto.PeriodCsearchDto;
import com.study.jsp.PageInfo.BPageInfo;
import com.study.jsp.PageInfo.MPageInfo;
import com.study.jsp.dto.BDto;
import com.study.jsp.dto.CDto;
import com.study.jsp.dto.MDto;

public class MDao {

	private static MDao instance = new MDao();
	DataSource dataSource =null;
	
	int listCount =5; //한 페이지당 보여줄 게시물의 갯수
	int pageCount =10;//하단에 보여줄 페이지 리스트의 갯수
	
	
	public MDao() {
		try {
			//lookup 함수의 파라메터는 context.xml에 설정된
			// name(jdbc/Oracle11g)과 동일해야한다.
			Context context = new InitialContext();
			dataSource =(DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static MDao getInstance() {
		return instance;
	}
	
	
	public BPageInfo ArticlePage(int curPage) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int listCount = 5;
		int pageCount = 10;
		
		// 총 게시물의 갯수
		int totalCount = 0;
		
		try {			
			con = dataSource.getConnection();
			
			String query = "select count(*) as total from mvc_board";
			pstmt = con.prepareStatement(query);
			
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
		
		BPageInfo pinfo = new BPageInfo();
		pinfo.setTotalCount(totalCount);
		pinfo.setListCount(listCount);
		pinfo.setTotalPage(totalPage);
		pinfo.setCurPage(curPage);
		pinfo.setPageCount(pageCount);
		pinfo.setStartPage(startPage);
		pinfo.setEndPage(endPage);
		return pinfo;
	}
	
	public BPageInfo SearchPageArticle(int curPage, String searchoption, String search) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int listCount = 5;
		int pageCount = 5;
		
		// 총 게시물의 갯수
		int totalCount = 0;
		
		String query = "";
		String temp;
		
		try {			
			con = dataSource.getConnection();
			
			if(searchoption.equals("title")) {
				query = "select count(*) as total from mvc_board where btitle like ?";
				temp = search;
				search = "%"+temp+"%";
			} else if(searchoption.equals("writer")) {
				query = "select count(*) as total from mvc_board where bname = ?";
			} else if(searchoption.equals("content")) {
				query = "select count(*) as total from mvc_board where bcontent like ?";
				temp = search;
				search = "%"+temp+"%";
			}
			
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, search);
			
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
		
		BPageInfo pinfo = new BPageInfo();
		pinfo.setTotalCount(totalCount);
		pinfo.setListCount(listCount);
		pinfo.setTotalPage(totalPage);
		pinfo.setCurPage(curPage);
		pinfo.setPageCount(pageCount);
		pinfo.setStartPage(startPage);
		pinfo.setEndPage(endPage);
		
		return pinfo;
	}
	
	
	
	public BPageInfo userArticle(int curPage, String Id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int listCount = 5;
		int pageCount = 5;
		
		// 총 게시물의 갯수
		int totalCount = 0;
		
		try {			
			con = dataSource.getConnection();
			
			String query = "select count(*) as total from mvc_board where bUser = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, Id);
			
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
		
		BPageInfo pinfo = new BPageInfo();
		pinfo.setTotalCount(totalCount);
		pinfo.setListCount(listCount);
		pinfo.setTotalPage(totalPage);
		pinfo.setCurPage(curPage);
		pinfo.setPageCount(pageCount);
		pinfo.setStartPage(startPage);
		pinfo.setEndPage(endPage);
		return pinfo;
	}
	
	public BPageInfo articleComment(int curPage) {
		
		Connection con =null;
		PreparedStatement pstmt=null;
		ResultSet resultSet =null;
		
		int listCount =5; //한 페이지당 보여줄 게시물의 갯수
		int pageCount =10; //하단에 보여줄 페이지 리스트의 갯수
		
		//총 게시물의 갯수
		int totalCount = 0;
		try {
			con = dataSource.getConnection();
			
			String query = "select count(*) as total from boardcomment";
			pstmt = con.prepareStatement(query);
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
		
		BPageInfo pinfo = new BPageInfo();
		pinfo.setTotalCount(totalCount);
		pinfo.setListCount(listCount);
		pinfo.setTotalPage(totalPage);
		pinfo.setCurPage(curPage);
		pinfo.setPageCount(pageCount);
		pinfo.setStartPage(startPage);
		pinfo.setEndPage(endPage);
		
		return pinfo;
	}
	
	
	public ArrayList<BDto> Articlelist(int curPage, String Id){
		
	ArrayList<BDto> dtos = new ArrayList<BDto>();
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	int nStart = (curPage - 1) * listCount + 1;
	int nEnd = (curPage - 1) * listCount + listCount;
	
	try {			
		con = dataSource.getConnection();
		/*String query = "select * " +
					   "from mvc_board "+
					   "order by bGroup desc, bStep asc";*/
		String query = "select * " +
				   	   "	from ( "+
				   	   "		select rownum num, A.* " +
				   	   "			from ( " +
				   	   "				select * " +
				   	   "				from mvc_board where bUser = ? "+
				       "			order by bGroup desc, bStep asc ) A "+
				   	   "	where rownum <= ? ) B " +
				       "where B.num >= ?";
		pstmt = con.prepareStatement(query);
		pstmt.setString(1, Id);
		pstmt.setInt(2, nEnd);
		pstmt.setInt(3, nStart);
		
					
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			int bId = rs.getInt("bId");
			String bName = rs.getString("bName");
			String bTitle = rs.getString("bTitle");
			String bContent = rs.getString("bContent");
			Timestamp bDate = rs.getTimestamp("bDate");
			
			SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd kk:mm:ss");
			Calendar cal = Calendar.getInstance();
			String today = null;
			today = formatter.format(cal.getTime());
			Timestamp ts = Timestamp.valueOf(today);				
			long diff = ts.getTime() - bDate.getTime();
			
			
			int bHit = rs.getInt("bHit");
			int bGroup = rs.getInt("bGroup");
			int bStep = rs.getInt("bStep");
			int bindent = rs.getInt("bindent");				
			String bUser = rs.getString("bUser");
			String type = rs.getString("type");
			int bLike = rs.getInt("bLike");
			
			BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bindent, bUser, type, bLike);
			
			if((diff / (1000 * 60 * 60)) < 1) {
				//System.out.println((diff / (1000 * 60 * 60)) + " 시간 전 작성");
				dto.setbNew(true);
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
	
	
	public ArrayList<MDto> mlist(int curPage) {
		
		ArrayList<MDto> dtos = new ArrayList<MDto>();
		Connection con = null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs =null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		
		int nStart = (curPage -1) * listCount +1;
		int nEnd = (curPage -1) * listCount + listCount;
		
		try {
			con = dataSource.getConnection();
			
			String query = "select * " +
					" 		from ( " +
					" 		select rownum num, A.* " +
					" 		from ( " +
					" 			select * " +
					" 			 from members1 " +
					"						 ) A " +
					" 				where rownum <= ? ) B " +
					" 			where B.num >= ? ";
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, nEnd);
			pstmt.setInt(2, nStart);
			String query2 = "";
			String query3 = "";
			
			int mContent = 0;
			int mReply = 0;
			
			rs=pstmt.executeQuery();
			
			
			while(rs.next())
			{
				String id = rs.getString("id");
				String pw =rs.getString("pw");
				String name = rs.getString("name");
				Timestamp rDate = rs.getTimestamp("rDate");
				String Nickname =rs.getString("Nickname");
				String eMail =rs.getString("eMail");
				String address =rs.getString("address");
				String manager =rs.getString("manager");
				String ban =rs.getString("ban");

				query2 = "select count(*) as mcount from mvc_board where bUser = ?";
				pstmt2 = con.prepareStatement(query2);
				pstmt2.setString(1, id);
				
				query3 = "select count(*) as mcount from boardcomment where cid = ?";
				pstmt3 = con.prepareStatement(query3);
				pstmt3.setString(1, id);
				
				rs2 = pstmt2.executeQuery() ;	
				if(rs2.next()) {
					mContent = rs2.getInt("mcount");
				}
				rs3 = pstmt3.executeQuery() ;	
				if(rs3.next()) {
					mReply = rs3.getInt("mcount");
				}
				
				MDto dto = new MDto(id,pw,name,Nickname,eMail,rDate,address,manager,ban,mContent,mReply);

				dtos.add(dto);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(rs2 != null) rs2.close();
				if(pstmt != null) pstmt.close();
				if(pstmt2 != null) pstmt2.close();
				if(pstmt3 != null) pstmt3.close();
				if(con != null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return dtos;
	}
	
	public ArrayList<BDto> mlist2(int nPage) 
	{
		
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet resultSet =null;
		
		int nStart = (nPage -1) * listCount +1;
		int nEnd = (nPage -1) * listCount + listCount;
		
		try {
			con = dataSource.getConnection();
			
			String query = "select * " +
							" from ( " +
							" 	select rownum num, A.* " +
							" 		from ( " +
							" 			select * " +
							" 			 from mvc_board" +
							"				order by bGroup desc, bStep asc ) A " +
							" 				where rownum <= ? ) B " +
							" 			where B.num >= ? ";
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, nEnd);
			pstmt.setInt(2, nStart);
			resultSet=pstmt.executeQuery();
			
			while(resultSet.next())
			{
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				
				SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
	            Calendar cal = Calendar.getInstance();
	            String today = null;
	            today = formatter.format(cal.getTime());
	            Timestamp ts = Timestamp.valueOf(today);            
	            long diff = ts.getTime() - bDate.getTime();
	            
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				String bUser =resultSet.getString("bUser");
				String Type = resultSet.getString("Type");
				int bLike = resultSet.getInt("bLike");
				
				BDto dto = new BDto(bId,bName,bTitle,bContent,bDate,
							bHit,bGroup,bStep,bIndent,bUser,Type,bLike);
				  if((diff /(1000 * 60 * 60)) < 24) {
		               //System.out.println((diff / (1000 * 60 * 60)) + " 시간 전 작성");
		               dto.setbNew(true);
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
	
	public BPageInfo CommentPage(int curPage) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int listCount = 5;
		int pageCount = 10;
		
		// 총 게시물의 갯수
		int totalCount = 0;
		
		try {			
			con = dataSource.getConnection();
			
			String query = "select count(*) as total from boardcomment";
			pstmt = con.prepareStatement(query);
			
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
		
		BPageInfo pinfo = new BPageInfo();
		pinfo.setTotalCount(totalCount);
		pinfo.setListCount(listCount);
		pinfo.setTotalPage(totalPage);
		pinfo.setCurPage(curPage);
		pinfo.setPageCount(pageCount);
		pinfo.setStartPage(startPage);
		pinfo.setEndPage(endPage);
		return pinfo;
	}
	
	
	
	
		
	public MDto contentView(String strID)
	{
			
			MDto dto = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			PreparedStatement pstmt2 = null;
			PreparedStatement pstmt3 = null;
			ResultSet rs = null;
			ResultSet rs2 = null;
			ResultSet rs3 = null;
			
			try
			{
				con=dataSource.getConnection();
				
				String query= "select * from members1 where id= ?";
				pstmt=con.prepareStatement(query);
				pstmt.setString(1, strID);

				String query2 = "";
				String query3 = "";
				
				int mContent = 0;
				int mReply = 0;
							
				
				rs = pstmt.executeQuery();
				
				if(rs.next())
				{
					String id = rs.getString("id");
					String pw =rs.getString("pw");
					String name = rs.getString("name");
					Timestamp rDate = rs.getTimestamp("rDate");
					String Nickname =rs.getString("Nickname");
					String eMail =rs.getString("eMail");
					String address =rs.getString("address");
					String manager =rs.getString("manager");
					String ban =rs.getString("ban");
					
					query2 = "select count(*) as mCount from mvc_board where bUser = ?";
					pstmt2 = con.prepareStatement(query2);
					pstmt2.setString(1, id);		
					
					query3 = "select count(*) as mCount from boardcomment where cid = ?";
					pstmt3 = con.prepareStatement(query3);
					pstmt3.setString(1, id);
					
					rs2 = pstmt2.executeQuery() ;	// 쓴 개시글 갯수
					if(rs2.next()) {
						mContent = rs2.getInt("mCount");
					}
					System.out.println(mContent);
					rs3 = pstmt3.executeQuery() ;	// 쓴 댓글 갯수
					if(rs3.next()) {
						mReply = rs3.getInt("mCount");
					}
					System.out.println(mReply);
					
					dto = new MDto(id,pw,name,eMail,Nickname,rDate,address,manager,ban,mContent,mReply);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(rs != null) rs.close();
					if(rs2 != null) rs2.close();
					if(pstmt != null) pstmt.close();
					if(pstmt2 != null) pstmt2.close();
					if(pstmt3 != null) pstmt3.close();
					if(con != null) con.close();
					}catch(Exception e2) {
						e2.printStackTrace();
						}
					}
				return dto;
		}
	
	public BDto contentView2(String Id)
	{
		
		
		BDto dto = null;
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet resultSet =null;
		
		try
		{
			con=dataSource.getConnection();
			
			String query= "select * from mvc_board where bId= ?";
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(Id));
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next())
			{
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				String bUser = resultSet.getString("bUser");
				int bLike =resultSet.getInt("bLike");
				
				dto = new BDto(bId,bName,bTitle,bContent,bDate,
						bHit,bGroup,bStep,bIndent,bUser,bLike);
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
	
	public void modify(String Id,String Pw,String Name,String Nickname,String eMail,String Address)
	{
		Connection con = null;
		PreparedStatement pstmt=null;
		
		String query="update member1" +
					"	set Id =?, " +
					"    Pw = ?,  " +
					"    Name = ?, " +
					"    Nickname = ?, " +
					"    eMail = ?, " +
					"    Address = ? " +
					"where Id = ?";
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, Id);
			pstmt.setString(2, Pw);
			pstmt.setString(3, Name);
			pstmt.setString(4, Nickname);
			pstmt.setString(5, eMail);
			pstmt.setString(6, Address);
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
	
	public void delete(String Id)
	{
		Connection con = null;
		PreparedStatement pstmt=null;
		
		try {
			con = dataSource.getConnection();
			String query = "delete from members1 where Id =?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, Id);
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
	
	public ArrayList<MDto> Search(int curPage, String searchoption, String search){
		
		ArrayList<MDto> dtos = new ArrayList<MDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "";
		String temp = "";
		
		int nStart = (curPage - 1) * listCount + 1;
		int nEnd = (curPage - 1) * listCount + listCount;
		
		try {			
			con = dataSource.getConnection();
			
			
			if(searchoption.equals("id")) {
				query = "select * " +
						" 		from ( " +
						" 		select rownum num, A.* " +
						" 		from ( " +
						" 			select * " +
						" 			 from members1 where id like ?" +
						"						 ) A " +
						" 				where rownum <= ? ) B " +
						" 			where B.num >= ? ";
				temp = search;
				search = "%"+temp+"%";
			} else if(searchoption.equals("nickname")) {
				query = "select * " +
						" 		from ( " +
						" 		select rownum num, A.* " +
						" 		from ( " +
						" 			select * " +
						" 			 from members1 where nickname like ? " +
						"						 ) A " +
						" 				where rownum <= ? ) B " +
						" 			where B.num >= ? ";
				temp = search;
				search = "%"+temp+"%";
			}else if(searchoption.equals("name")) {
				query = "select * " +
						" 		from ( " +
						" 		select rownum num, A.* " +
						" 		from ( " +
						" 			select * " +
						" 			 from members1 where name like ? " +
						"						 ) A " +
						" 				where rownum <= ? ) B " +
						" 			where B.num >= ? ";
				temp = search;
				search = "%"+temp+"%";
			}  
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, search);
			pstmt.setInt(2, nEnd);
			pstmt.setInt(3, nStart);
			
						
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String Id = rs.getString("Id");
				String Pw = rs.getString("Pw");
				String Name = rs.getString("Name");
				String Nickname = rs.getString("Nickname");
				String eMail = rs.getString("eMail");
				Timestamp rDate = rs.getTimestamp("rDate");    
				String Address = rs.getString("Address");
				String manager = rs.getString("manager");
				String ban = rs.getString("ban");

				
				MDto dto = new MDto(Id, Pw, Name, Nickname, eMail, rDate, Address, manager,ban);
				 
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
	
		
	
	public MPageInfo SearchPage(int curPage, String searchoption, String search) {
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
			
			if(searchoption.equals("id")) 
			{
				query = "select count(*) as total from members1 where id like ?";
				temp = search;
				search = "%"+temp+"%";
			} 
			else if(searchoption.equals("name")) 
			{
				query = "select count(*) as total from members1 where name = ?";
				temp = search;
				search = "%"+temp+"%";
			}
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, search);
			
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
		
		MPageInfo pinfo = new MPageInfo();
		pinfo.setTotalCount(totalCount);
		pinfo.setListCount(listCount);
		pinfo.setTotalPage(totalPage);
		pinfo.setCurPage(curPage);
		pinfo.setPageCount(pageCount);
		pinfo.setStartPage(startPage);
		pinfo.setEndPage(endPage);
		
		return pinfo;
	}
		
		
	private Connection getConnection() 
	{
			Context context =null;
			DataSource dataSource =null;
			Connection con =null;
			try 
			{
				//lookup 함수의 파라메터는 context.xml에 설정된
				//name(jdbc/Oracel11g과 동일해야한다.
				context =new InitialContext();
				dataSource =(DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
				con = dataSource.getConnection();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			return con;
	}
	
	
	public String ban(String id) {				
		String result = "n";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		String query_update = "";
		//System.out.println("a : " + id);
		try {			
			con = dataSource.getConnection();
			String query = "select ban from members1 where id = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				//System.out.println("b : " + rs.getString("ban"));
				if(rs.getString("ban").equals("n")) {
					query_update = "update members1 set ban = ? where id = ?";
					pstmt2 = con.prepareStatement(query_update);
					
					pstmt2.setString(1, "y");
					pstmt2.setString(2, id);
					
					int temp = pstmt2.executeUpdate();
					result = "y";
				} else {
					query_update = "update members1 set ban = ? where id = ?";
					pstmt2 = con.prepareStatement(query_update);
					
					pstmt2.setString(1, "n");
					pstmt2.setString(2, id);
					
					int temp = pstmt2.executeUpdate();
					result = "n";
				}								
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null)	rs.close();
				if(pstmt != null) pstmt.close();
				if(pstmt2 != null) pstmt2.close();
				if(con != null) con.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public ArrayList<BDto> AllArticlelist(int curPage){
		
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int nStart = (curPage - 1) * listCount + 1;
		int nEnd = (curPage - 1) * listCount + listCount;
		
		try {			
			con = dataSource.getConnection();
			/*String query = "select * " +
						   "from mvc_board "+
						   "order by bGroup desc, bStep asc";*/
			String query = "select * " +
					   	   "	from ( "+
					   	   "		select rownum num, A.* " +
					   	   "			from ( " +
					   	   "				select * " +
					   	   "				from mvc_board where Type != ?"+
					       "			order by bGroup desc, bStep asc ) A "+
					   	   "	where rownum <= ? ) B " +
					       "where B.num >= ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "notice");
			pstmt.setInt(2, nEnd);
			pstmt.setInt(3, nStart);
			
						
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int bId = rs.getInt("bId");
				String bName = rs.getString("bName");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				Timestamp bDate = rs.getTimestamp("bDate");
				
				SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd kk:mm:ss");
				Calendar cal = Calendar.getInstance();
				String today = null;
				today = formatter.format(cal.getTime());
				Timestamp ts = Timestamp.valueOf(today);				
				long diff = ts.getTime() - bDate.getTime();
				
				
				int bHit = rs.getInt("bHit");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bindent = rs.getInt("bindent");				
				String bUser = rs.getString("bUser");
				String Type = rs.getString("Type");
				int bLike = rs.getInt("bLike");
				BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bindent, bUser, Type, bLike);
				
				if((diff / (1000 * 60 * 60)) < 1) {
					//System.out.println((diff / (1000 * 60 * 60)) + " 시간 전 작성");
					dto.setbNew(true);
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
	
	public BPageInfo userPage(int curPage) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int listCount = 5;
		int pageCount = 10;
		
		// 총 게시물의 갯수
		int totalCount = 0;
		
		try {			
			con = dataSource.getConnection();
			
			String query = "select count(*) as total from members1";
			pstmt = con.prepareStatement(query);
			
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
		
		BPageInfo pinfo = new BPageInfo();
		pinfo.setTotalCount(totalCount);
		pinfo.setListCount(listCount);
		pinfo.setTotalPage(totalPage);
		pinfo.setCurPage(curPage);
		pinfo.setPageCount(pageCount);
		pinfo.setStartPage(startPage);
		pinfo.setEndPage(endPage);
		return pinfo;
	}
	
	
	public BPageInfo SearchPagePeriod(int curPage, String dayoption) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int listCount = 5;
		int pageCount = 10;
		
		// 총 게시물의 갯수
		int totalCount = 0;
		
		String query = "";
		System.out.println();
		
		try {			
			con = dataSource.getConnection();
			
			if(dayoption.equals("day")) {
				query = "select count(*) as total from mvc_board where bDate > sysdate-2";
			} else if(dayoption.equals("week")) {
				query = "select count(*) as total from mvc_board where bDate > sysdate-8";
			} else if(dayoption.equals("month")) {
				query = "select count(*) as total from mvc_board where bDate > sysdate-32";
			} else if(dayoption.equals("3month")) {
				query = "select count(*) as total from mvc_board where bDate > sysdate-94";
			} else if(dayoption.equals("6month")) {
				query = "select count(*) as total from mvc_board where bDate > sysdate-187";
			}
			
			
			pstmt = con.prepareStatement(query);
			
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
		
		BPageInfo pinfo = new BPageInfo();
		pinfo.setTotalCount(totalCount);
		pinfo.setListCount(listCount);
		pinfo.setTotalPage(totalPage);
		pinfo.setCurPage(curPage);
		pinfo.setPageCount(pageCount);
		pinfo.setStartPage(startPage);
		pinfo.setEndPage(endPage);
		
		return pinfo;
	} 
	
	public ArrayList<BDto> SearchPeriod(int curPage, String dayoption){
		
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "";
		String temp = "";
		
		int nStart = (curPage - 1) * listCount + 1;
		int nEnd = (curPage - 1) * listCount + listCount;
		
		try {			
			con = dataSource.getConnection();
			if(dayoption.equals("day")) {
				query = "select * " +
					   	   "	from ( "+
					   	   "		select rownum num, A.* " +
					   	   "			from ( " +
					   	   "				select * " +
					   	   "				from mvc_board where bDate >= sysdate-2"+
					       "			order by bGroup desc, bStep asc ) A "+
					   	   "	where rownum <= ? ) B " +
					       "where B.num >= ?";
			} else if(dayoption.equals("week")) {
				System.out.println("aaaa : zzz");
				query = "select * " +
					   	   "	from ( "+
					   	   "		select rownum num, A.* " +
					   	   "			from ( " +
					   	   "				select * " +
					   	   "				from mvc_board where bDate > sysdate-8"+
					       "			order by bGroup desc, bStep asc ) A "+
					   	   "	where rownum <= ? ) B " +
					       "where B.num >= ?";
			} else if(dayoption.equals("month")) {
				query = "select * " +
					   	   "	from ( "+
					   	   "		select rownum num, A.* " +
					   	   "			from ( " +
					   	   "				select * " +
					   	   "				from mvc_board where bDate > sysdate-32"+
					       "			order by bGroup desc, bStep asc ) A "+
					   	   "	where rownum <= ? ) B " +
					       "where B.num >= ?";
			} else if(dayoption.equals("3month")) {
				query = "select * " +
					   	   "	from ( "+
					   	   "		select rownum num, A.* " +
					   	   "			from ( " +
					   	   "				select * " +
					   	   "				from mvc_board where bDate > sysdate-94"+
					       "			order by bGroup desc, bStep asc ) A "+
					   	   "	where rownum <= ? ) B " +
					       "where B.num >= ?";
			} else if(dayoption.equals("6month")) {
				System.out.println("aaaa : 187");
				query = "select * " +
					   	   "	from ( "+
					   	   "		select rownum num, A.* " +
					   	   "			from ( " +
					   	   "				select * " +
					   	   "				from mvc_board where bDate > sysdate-187"+
					       "			order by bGroup desc, bStep asc ) A "+
					   	   "	where rownum <= ? ) B " +
					       "where B.num >= ?";
			}
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, nEnd);
			pstmt.setInt(2, nStart);
			
						
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int bId = rs.getInt("bId");
				String bName = rs.getString("bName");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				Timestamp bDate = rs.getTimestamp("bDate");
				
				SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd kk:mm:ss");
				Calendar cal = Calendar.getInstance();
				String today = null;
				today = formatter.format(cal.getTime());
				Timestamp ts = Timestamp.valueOf(today);				
				long diff = ts.getTime() - bDate.getTime();
				
				int bHit = rs.getInt("bHit");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bindent = rs.getInt("bindent");				
				String bUser = rs.getString("bUser");
				String Type = rs.getString("Type");
				int bLike = rs.getInt("bLike");	
				
				BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bindent, bUser, Type, bLike);
				
				if((diff /(1000 * 60 * 60)) < 1) {
					//System.out.println((diff / (1000 * 60 * 60)) + " 시간 전 작성");
					dto.setbNew(true);
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
	
	
	public ArrayList<PeriodAsearchDto> SearchPeriodUser(String dayoption) {
		
		ArrayList<PeriodAsearchDto> dtos = new ArrayList<PeriodAsearchDto>();
		PeriodAsearchDto dto = new PeriodAsearchDto();
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		String id = "";
		String query = "";
		
		try {			
			con = dataSource.getConnection();
			if(dayoption.equals("day")) {
				query = "select * from ( select count(*) as total, bUser from mvc_board where bdate > sysdate-2 group by bUser order by total desc) where rownum < 4";
			} else if(dayoption.equals("week")) {
				query = "select * from ( select count(*) as total, bUser from mvc_board where bdate > sysdate-8 group by bUser order by total desc) where rownum < 4";
			} else if(dayoption.equals("month")) {
				query = "select * from ( select count(*) as total, bUser from mvc_board where bdate > sysdate-32 group by bUser order by total desc) where rownum < 4";
			} else if(dayoption.equals("3month")) {
				query = "select * from ( select count(*) as total, bUser from mvc_board where bdate > sysdate-64 group by bUser order by total desc) where rownum < 4";
			} else if(dayoption.equals("6month")) {
				query = "select * from ( select count(*) as total, bUser from mvc_board where bdate > sysdate-187 group by bUser order by total desc) where rownum < 4";
			} 
			
			
			//System.out.println("1차 쿼리 : " + query);
			pstmt = con.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				id = rs.getString("buser");
				//System.out.println("bbbbb : " + id);
				
				String query2 = "select * from members1 where id = ?";
				pstmt2 = con.prepareStatement(query2);
				pstmt2.setString(1, id);
				
				rs2 = pstmt2.executeQuery();
				
				while(rs2.next()) {
					//System.out.println("cccc : " + rs2.getString("id"));
					
					dto = new PeriodAsearchDto(rs2.getString("id"),rs2.getString("name"),rs2.getString("nickname"),rs2.getString("ban"),rs.getInt("total"));
					
					dtos.add(dto);
				}
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
	
	public ArrayList<BDto> SearchArticle(int curPage, String searchoption, String search){
		
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "";
		String temp = "";
		
		int nStart = (curPage - 1) * listCount + 1;
		int nEnd = (curPage - 1) * listCount + listCount;
		
		try {			
			con = dataSource.getConnection();
			/*String query = "select * " +
						   "from mvc_board "+
						   "order by bGroup desc, bStep asc";*/
			
			if(searchoption.equals("title")) {
				query = "select * " +
					   	   "	from ( "+
					   	   "		select rownum num, A.* " +
					   	   "			from ( " +
					   	   "				select * " +
					   	   "				from mvc_board where btitle like ?"+
					       "			order by bGroup desc, bStep asc ) A "+
					   	   "	where rownum <= ? ) B " +
					       "where B.num >= ?";
				temp = search;
				search = "%"+temp+"%";
			} else if(searchoption.equals("writer")) {
				query = "select * " +
					   	   "	from ( "+
					   	   "		select rownum num, A.* " +
					   	   "			from ( " +
					   	   "				select * " +
					   	   "				from mvc_board where bname = ?"+
					       "			order by bGroup desc, bStep asc ) A "+
					   	   "	where rownum <= ? ) B " +
					       "where B.num >= ?";
			} else if(searchoption.equals("content")) {
				query = "select * " +
					   	   "	from ( "+
					   	   "		select rownum num, A.* " +
					   	   "			from ( " +
					   	   "				select * " +
					   	   "				from mvc_board where bcontent like ?"+
					       "			order by bGroup desc, bStep asc ) A "+
					   	   "	where rownum <= ? ) B " +
					       "where B.num >= ?";
				temp = search;
				search = "%"+temp+"%";
			}
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, search);
			pstmt.setInt(2, nEnd);
			pstmt.setInt(3, nStart);
			
						
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int bId = rs.getInt("bId");
				String bName = rs.getString("bName");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				Timestamp bDate = rs.getTimestamp("bDate");
				
				SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd kk:mm:ss");
				Calendar cal = Calendar.getInstance();
				String today = null;
				today = formatter.format(cal.getTime());
				Timestamp ts = Timestamp.valueOf(today);				
				long diff = ts.getTime() - bDate.getTime();
				
				int bHit = rs.getInt("bHit");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bindent = rs.getInt("bindent");				
				String bUser = rs.getString("bUser");
				String Type = rs.getString("Type");
				
				BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bindent, bUser, Type);
				
				if((diff /(1000 * 60 * 60)) < 1) {
					//System.out.println((diff / (1000 * 60 * 60)) + " 시간 전 작성");
					dto.setbNew(true);
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
	
	public ArrayList<CDto> Commentlist(int curPage){
		
		ArrayList<CDto> dtos = new ArrayList<CDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int nStart = (curPage - 1) * listCount + 1;
		int nEnd = (curPage - 1) * listCount + listCount;
		
		try {			
			con = dataSource.getConnection();
			/*String query = "select * " +
						   "from mvc_board "+
						   "order by bGroup desc, bStep asc";*/
			String query = "select * " +
					   	   "	from ( "+
					   	   "		select rownum num, A.* " +
					   	   "			from ( " +
					   	   "				select * " +
					   	   "				from boardcomment) A "+
					   	   "	where rownum <= ? ) B " +
					       "where B.num >= ?";
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, nEnd);
			pstmt.setInt(2, nStart);
			
						
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CDto comment = new CDto();
                comment.setClevel(rs.getInt("LEVEL"));
                comment.setCnum(rs.getInt("CNUM"));
                comment.setCboard(rs.getInt("CBOARD"));
                comment.setCid(rs.getString("CID"));
                comment.setcDate(rs.getTimestamp("CDATE"));
                comment.setCparent(rs.getInt("CPARENT"));
                comment.setCcontent(rs.getString("CCONTENT"));
                dtos.add(comment);
					
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
	
	public ArrayList<CDto> AllCommentlist(int curPage){
		
		
		ArrayList<CDto> dtos = new ArrayList<CDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		
		int nStart = (curPage - 1) * listCount + 1;
		int nEnd = (curPage - 1) * listCount + listCount;
		
		try {			
			con = dataSource.getConnection();
			String query = "select max(level) as max_level, cnum from boardcomment CONNECT BY prior cnum = cparent group by cnum";
			String query2 = "select * " +
					   	   "	from ( "+
					   	   "		select rownum num, A.* " +
					   	   "			from ( " +
					   	   "				select * " +
					   	   "				from boardcomment where cnum = ? ) A "+
					   	   "	where rownum <= ? ) B " +
					       "where B.num >= ?";
			String query3 = "select bTitle from mvc_board where bId = ?";
			
			
			pstmt = con.prepareStatement(query);
			
			
						
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
               	
               	pstmt2 = con.prepareStatement(query2);
               	pstmt2.setString(1, rs.getString("cnum"));
    			pstmt2.setInt(2, nEnd);
    			pstmt2.setInt(3, nStart);
               
               	
               	rs2 = pstmt2.executeQuery();
               	
               	while(rs2.next()) {
               		CDto comment = new CDto();
    				comment.setClevel(rs.getInt("max_level"));
    				comment.setCnum(rs2.getInt("CNUM"));
    				comment.setCboard(rs2.getInt("CBOARD"));
    				comment.setCid(rs2.getString("CID"));
                   	comment.setcDate(rs2.getTimestamp("CDATE"));
                   	comment.setCparent(rs2.getInt("CPARENT"));
                   	comment.setCcontent(rs2.getString("CCONTENT"));
                   	
                   	pstmt3 = con.prepareStatement(query3);
                	pstmt3.setInt(1, rs2.getInt("CBOARD"));
                	
                	rs3 = pstmt3.executeQuery();
                	if(rs3.next()) {
                		comment.setPtitle(rs3.getString("bTitle"));
                	}
                	
                	dtos.add(comment);
               	}       
			}
		} catch(Exception e) {
				e.printStackTrace();
		} finally {
			try {		
				if(rs != null) rs.close();
				if(rs2 != null) rs2.close();
				if(rs3 != null) rs3.close();
				if(pstmt != null) pstmt.close();
				if(pstmt2 != null) pstmt2.close();
				if(pstmt3 != null) pstmt3.close();
				if(con != null) con.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return dtos;
	}
	
	public BPageInfo SearchPageComment(int curPage, String searchoption, String search) {
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
			
			if(searchoption.equals("id")) {
				query = "select count(*) as total from boardcomment where cid = ?";
			} else if(searchoption.equals("name")) {
				query = "select count(*) as total from boardcomment where ccontent like ?";
				temp = search;
				search = "%"+temp+"%";
			}
			
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, search);
			
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
		
		BPageInfo pinfo = new BPageInfo();
		pinfo.setTotalCount(totalCount);
		pinfo.setListCount(listCount);
		pinfo.setTotalPage(totalPage);
		pinfo.setCurPage(curPage);
		pinfo.setPageCount(pageCount);
		pinfo.setStartPage(startPage);
		pinfo.setEndPage(endPage);
		
		return pinfo;
	}
	
	public BPageInfo SearchCommentPeriod(int curPage, String dayoption) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int listCount = 10;
		int pageCount = 10;
		
		// 총 게시물의 갯수
		int totalCount = 0;
		
		String query = "";
		
		try {			
			con = dataSource.getConnection();
			
			if(dayoption.equals("day")) {
				query = "select count(*) as total from boardcomment where cDate > sysdate-2";
			} else if(dayoption.equals("week")) {
				query = "select count(*) as total from boardcomment where cDate > sysdate-8";
			} else if(dayoption.equals("month")) {
				query = "select count(*) as total from boardcomment where cDate > sysdate-32";
			} else if(dayoption.equals("3month")) {
				query = "select count(*) as total from boardcomment where cDate > sysdate-94";
			} else if(dayoption.equals("6month")) {
				query = "select count(*) as total from boardcomment where cDate > sysdate-187";
			}
			
			
			pstmt = con.prepareStatement(query);
			
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
		
		BPageInfo pinfo = new BPageInfo();
		pinfo.setTotalCount(totalCount);
		pinfo.setListCount(listCount);
		pinfo.setTotalPage(totalPage);
		pinfo.setCurPage(curPage);
		pinfo.setPageCount(pageCount);
		pinfo.setStartPage(startPage);
		pinfo.setEndPage(endPage);
		
		return pinfo;
	}
	
	public ArrayList<CDto> SearchCommentListPeriod(int curPage, String dayoption){
		
		ArrayList<CDto> dtos = new ArrayList<CDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		String query = "select max(level) as max_level, cnum from boardcomment CONNECT BY prior cnum = cparent group by cnum";
		String query2 = "";
		String query3 = "select bTitle from mvc_board where bId = ?";
		String temp = "";
		
		int nStart = (curPage - 1) * listCount + 1;
		int nEnd = (curPage - 1) * listCount + listCount;
		
		try {			
			con = dataSource.getConnection();
			/*String query = "select * " +
						   "from mvc_board "+
						   "order by bGroup desc, bStep asc";*/
			
			if(dayoption.equals("day")) {
				query2 = "select * " +
					   	   "	from ( "+
					   	   "		select rownum num, A.* " +
					   	   "			from ( " +
					   	   "				select * " +
					   	   "				from boardcomment where cDate > sysdate-2 and cnum = ? ) A "+
					   	   "	where rownum <= ? ) B " +
					       "where B.num >= ?";
			} else if(dayoption.equals("week")) {
				query2 = "select * " +
					   	   "	from ( "+
					   	   "		select rownum num, A.* " +
					   	   "			from ( " +
					   	   "				select * " +
					   	   "				from boardcomment where cDate > sysdate-7 and cnum = ? ) A "+
					   	   "	where rownum <= ? ) B " +
					       "where B.num >= ?";
			} else if(dayoption.equals("1month")) {
				query2 = "select * " +
					   	   "	from ( "+
					   	   "		select rownum num, A.* " +
					   	   "			from ( " +
					   	   "				select * " +
					   	   "				from boardcomment where cDate > sysdate-32 and cnum = ? ) A "+
					   	   "	where rownum <= ? ) B " +
					       "where B.num >= ?";
			} else if(dayoption.equals("3month")) {
				query2 = "select * " +
					   	   "	from ( "+
					   	   "		select rownum num, A.* " +
					   	   "			from ( " +
					   	   "				select * " +
					   	   "				from boardcomment where cDate > sysdate-64 and cnum = ? ) A "+
					   	   "	where rownum <= ? ) B " +
					       "where B.num >= ?";
			} else if(dayoption.equals("6month")) {
				query2 = "select * " +
					   	   "	from ( "+
					   	   "		select rownum num, A.* " +
					   	   "			from ( " +
					   	   "				select * " +
					   	   "				from boardcomment where cDate > sysdate-187 and cnum = ? ) A "+
					   	   "	where rownum <= ? ) B " +
					       "where B.num >= ?";
			}
			
			pstmt = con.prepareStatement(query);
			pstmt2 = con.prepareStatement(query2);
			pstmt3 = con.prepareStatement(query3);	
			
					
						
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				pstmt2.setString(1, rs.getString("cnum"));
				pstmt2.setInt(2, nEnd);
				pstmt2.setInt(3, nStart);
				
				rs2 = pstmt2.executeQuery();
				
				while(rs2.next()) {
					pstmt3.setString(1, rs2.getString("cboard"));
					
					rs3 = pstmt3.executeQuery();
					
					CDto comment = new CDto();
    				comment.setClevel(rs.getInt("max_level"));
    				comment.setCnum(rs2.getInt("CNUM"));
    				comment.setCboard(rs2.getInt("CBOARD"));
    				comment.setCid(rs2.getString("CID"));
                   	comment.setcDate(rs2.getTimestamp("CDATE"));
                   	comment.setCparent(rs2.getInt("CPARENT"));
                   	comment.setCcontent(rs2.getString("CCONTENT"));                   	
                   	
                	pstmt3.setInt(1, rs2.getInt("CBOARD"));
                	
                	if(rs3.next()) {
                		comment.setPtitle(rs3.getString("bTitle"));
                	}
                	
                	dtos.add(comment);
				}
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

	public ArrayList<PeriodCsearchDto> SearchPeriodUserComment(String dayoption) {
	
	ArrayList<PeriodCsearchDto> dtos = new ArrayList<PeriodCsearchDto>();
	PeriodCsearchDto dto = new PeriodCsearchDto();
	Connection con = null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmt2 = null;
	ResultSet rs = null;
	ResultSet rs2 = null;
	String id = "";
	String query = "";
	
	try {			
		con = dataSource.getConnection();
		if(dayoption.equals("day")) {
			query = "select * from ( select count(*) as total, cid from boardcomment where cdate > sysdate-2 group by cid order by total desc) where rownum < 4";
		} else if(dayoption.equals("week")) {
			query = "select * from ( select count(*) as total, cid from boardcomment where cdate > sysdate-8 group by cid order by total desc) where rownum < 4";
		} else if(dayoption.equals("month")) {
			query = "select * from ( select count(*) as total, cid from boardcomment where cdate > sysdate-32 group by cid order by total desc) where rownum < 4";
		} else if(dayoption.equals("3month")) {
			query = "select * from ( select count(*) as total, cid from boardcomment where cdate > sysdate-64 group by cid order by total desc) where rownum < 4";
		} else if(dayoption.equals("6month")) {
			query = "select * from ( select count(*) as total, cid from boardcomment where cdate > sysdate-187 group by cid order by total desc) where rownum < 4";
		} 
		
		
		//System.out.println("1차 쿼리 : " + query);
		pstmt = con.prepareStatement(query);
		
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			id = rs.getString("cid");
			System.out.println("bbbbb : " + id);
			
			String query2 = "select * from members1 where id = ?";
			pstmt2 = con.prepareStatement(query2);
			pstmt2.setString(1, id);
			
			rs2 = pstmt2.executeQuery();
			
			while(rs2.next()) {
				//System.out.println("cccc : " + rs2.getString("id"));
				
				dto = new PeriodCsearchDto(rs2.getString("id"),rs2.getString("name"),rs2.getString("nickname"),rs2.getString("ban"),rs.getInt("total"));
				
				dtos.add(dto);
			}
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
	
	public ArrayList<CDto> SearchComment(int curPage, String searchoption, String search){
		
		ArrayList<CDto> dtos = new ArrayList<CDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		String query = "";
		String query2 = "";
		String query3 = "";
		String temp = "";
		
		int nStart = (curPage - 1) * listCount + 1;
		int nEnd = (curPage - 1) * listCount + listCount;
		
		try {			
			con = dataSource.getConnection();
			/*String query = "select * " +
						   "from mvc_board "+
						   "order by bGroup desc, bStep asc";*/
			query = "select max(level) as max_level, cnum from boardcomment CONNECT BY prior cnum = cparent group by cnum";
			
			query3 = "select bTitle from mvc_board where bId = ?";
			
			if(searchoption.equals("id")) {
				query2 = "select * " +
					   	   "	from ( "+
					   	   "		select rownum num, A.* " +
					   	   "			from ( " +
					   	   "				select * " +
					   	   "				from boardcomment where cid = ? and cnum = ? ) A "+
					   	   "	where rownum <= ? ) B " +
					       "where B.num >= ?";
			} else if(searchoption.equals("name")) {
				query2 = "select * " +
					   	   "	from ( "+
					   	   "		select rownum num, A.* " +
					   	   "			from ( " +
					   	   "				select * " +
					   	   "				from boardcomment where ccontent like ? and cnum = ? ) A "+
					   	   "	where rownum <= ? ) B " +
					       "where B.num >= ?";
				temp = search;
				search = "%"+temp+"%";
			}
			
			pstmt = con.prepareStatement(query);
			pstmt2 = con.prepareStatement(query2);
			pstmt3 = con.prepareStatement(query3);
			
			
					
						
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				pstmt2.setString(1, search);
				pstmt2.setString(2, rs.getString("Cnum"));
				pstmt2.setInt(3, nEnd);
				pstmt2.setInt(4, nStart);
				
				rs2 = pstmt2.executeQuery();
				
				while(rs2.next()) {
					pstmt3.setString(1, rs2.getString("Cboard"));
					
					rs3 = pstmt3.executeQuery();
					
					CDto comment = new CDto();
    				comment.setClevel(rs.getInt("max_level"));
    				comment.setCnum(rs2.getInt("Cnum"));
    				comment.setCboard(rs2.getInt("Cboard"));
    				comment.setCid(rs2.getString("Cid"));
                   	comment.setcDate(rs2.getTimestamp("cDate"));
                   	comment.setCparent(rs2.getInt("Cparent"));
                   	comment.setCcontent(rs2.getString("Ccontent"));                   	
                   	
                	pstmt3.setInt(1, rs2.getInt("Cboard"));
                	
                	if(rs3.next()) {
                		comment.setPtitle(rs3.getString("bTitle"));
                	}
                	
                	dtos.add(comment);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {		
				if(rs != null) rs.close();
				if(rs2 != null) rs2.close();
				if(rs3 != null) rs3.close();
				if(pstmt != null) pstmt.close();
				if(pstmt2 != null) pstmt2.close();
				if(pstmt3 != null) pstmt3.close();
				if(con != null) con.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return dtos;
	}
	
	
}
