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
import com.study.jsp.dto.RDto;

public class RDao {

	private static RDao instance = new RDao();
	DataSource dataSource =null;
	
	int listCount =5; //한 페이지당 보여줄 게시물의 갯수
	int pageCount =10;//하단에 보여줄 페이지 리스트의 갯수
	
	
	public RDao() {
		try {
			//lookup 함수의 파라메터는 context.xml에 설정된
			// name(jdbc/Oracle11g)과 동일해야한다.
			Context context = new InitialContext();
			dataSource =(DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static RDao getInstance() {
		return instance;
	}
	
	public void write(RDto dto) 
	{
		Connection con=null;
		PreparedStatement pstmt =null;
		
		try {
			con=dataSource.getConnection();
			String query= "insert into mvc_board " +
			"(bId,bName,bTitle,bContent,bHit,bGroup,bStep,bIndent,bUser,type,filename) " +
			" values" +
			"(mvc_board_seq.nextval,?,?,?,0,mvc_board_seq.currval,0,0,?,?,?)";
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, dto.getbName());
			pstmt.setString(2, dto.getbTitle());
			pstmt.setString(3, dto.getbContent());
			pstmt.setString(4, dto.getbUser());
			pstmt.setString(5, "reference");
			pstmt.setString(6, dto.getFileName());
			
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
	
	public void FileDB(String Realname,String Sysname, String bId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {			
			con = dataSource.getConnection();
			String query = "insert into FILENAME " +
						   "values " +
						   "(?, ?, ?)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, Realname);
			pstmt.setString(2, Sysname);
			pstmt.setString(3, bId);
			
			int rm = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {				
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<RDto> rlist(int curPage) {
		
		ArrayList<RDto> dtos = new ArrayList<RDto>();
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
							" 			 from mvc_board " +
							"				order by bGroup desc, bStep asc ) A " +
							" 				where rownum <= ? and type= ?) B " +
							" 			where B.num >= ? ";
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, nEnd);
			pstmt.setString(2, "reference");
			pstmt.setInt(3, nStart);
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
				String fileName = resultSet.getString("fileName");
				int bLike = resultSet.getInt("bLike");
				
				RDto dto = new RDto(bId,bName,bTitle,bContent,bDate,
							bHit,bGroup,bStep,bIndent,bUser,Type,fileName,bLike);
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
	
	public ArrayList<RDto> Search(int curPage, String searchoption, String search){
		
		ArrayList<RDto> dtos = new ArrayList<RDto>();
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
			} else if(searchoption.equals("writer")) {
				query = "select * " +
					   	   "	from ( "+
					   	   "		select rownum num, A.* " +
					   	   "			from ( " +
					   	   "				select * " +
					   	   "				from mvc_board where type = ? and bname = ?"+
					       "			order by bGroup desc, bStep asc ) A "+
					   	   "	where rownum <= ? ) B " +
					       "where B.num >= ?";
			} else if(searchoption.equals("content")) {
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
			pstmt.setString(1, "reference");
			pstmt.setString(2, search);
			pstmt.setInt(3, nEnd);
			pstmt.setInt(4, nStart);
			
						
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int bId = rs.getInt("bId");
				String bName = rs.getString("bName");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				Timestamp bDate = rs.getTimestamp("bDate");
				
				SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
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
				

				
				RDto dto = new RDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bindent, bUser,Type);
				 if((diff /(1000 * 60 * 60)) < 24) {
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
	
	
	public BPageInfo articlePage(int curPage,String Type) {
		
		Connection con =null;
		PreparedStatement pstmt=null;
		ResultSet resultSet =null;
		
		int listCount =5; //한 페이지당 보여줄 게시물의 갯수
		int pageCount =10; //하단에 보여줄 페이지 리스트의 갯수
		
		//총 게시물의 갯수
		int totalCount = 0;
		try {
			con = dataSource.getConnection();
			
			String query = "select count(*) as total from mvc_board where Type = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, Type);
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
	
	public RDto contentView(String strID)
	{
		upHit(strID);
		
		
		RDto dto = null;
		Connection con = null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt2=null;
		ResultSet resultSet =null;
		ResultSet resultSet2 =null;
		
		String realname ="";
		
		try
		{
			con=dataSource.getConnection();
			
			String query= "select * from mvc_board where bId= ?";
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(strID));
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
				String Type = resultSet.getString("Type");
				String fileName =resultSet.getString("fileName");
				int bLike =resultSet.getInt("bLike");
				String query2 = "select realname from filename where sysname = ?";
				pstmt2 = con.prepareStatement(query2);
				pstmt2.setString(1, resultSet.getString("fileName"));
				
				resultSet2 = pstmt2.executeQuery();
				
				if(resultSet2.next()) {
					realname = resultSet2.getString("realname");
				}
				
				dto = new RDto(bId,bName,bTitle,bContent,bDate,
						bHit,bGroup,bStep,bIndent,bUser,Type,realname,bLike);
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
	
	
	
	public void modify(String bId, String bName, String bTitle, String bContent)
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
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setString(4, bId);
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
	
	private void upHit(String bId) 
	{
		Connection con = null;
		PreparedStatement pstmt=null;
		
		try {
			con = dataSource.getConnection();
			String query = "update mvc_board set bHit = bHit + 1 where bId= ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bId);
			
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
	public void delete(String bId)
	{
		Connection con = null;
		PreparedStatement pstmt=null;
		try {
			con = dataSource.getConnection();
			String query = "delete from mvc_board where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(bId));
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
	
	public RDto reply_view(String str)
	{
		RDto dto =null;
		
		
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet resultSet =null;
		
		try {
			con = dataSource.getConnection();
			String query = "select * from mvc_board where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(str));
			resultSet =pstmt.executeQuery();
			
			if(resultSet.next())
			{
				int bId= resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				String bUser = resultSet.getString("bUser");
				String Type = resultSet.getString("Type");
				
				dto = new RDto(bId,bName,bTitle,bContent,bDate,
						bHit,bGroup,bStep,bIndent,bUser,Type);
			}
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
		return dto;
	}
	
	public void reply(String bId,String bName,String bTitle,String bContent,
					String bGroup,String bStep,String bIndent)
	{
		replyShape(bGroup,bStep);
		
		Connection con = null;
		PreparedStatement pstmt=null;
		
		try {
			con = dataSource.getConnection();
			String query = "insert into mvc_board" +
							"(bId, bName, bTitle, bContent, bGroup, bStep, bIndent)  " +
							" values (mvc_board_seq.nextval, ?, ?, ?, ?, ?, ?)";
			pstmt=con.prepareStatement(query);
			
			pstmt.setString(1, bName);
			pstmt.setString(2, bName);
			pstmt.setString(3, bName);
			pstmt.setInt(4, Integer.parseInt(bGroup));
			pstmt.setInt(5, Integer.parseInt(bStep) + 1);
			pstmt.setInt(6, Integer.parseInt(bIndent) + 1);
			
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
	
	private void replyShape( String strGroup, String strStep)
	{
		Connection con = null;
		PreparedStatement pstmt=null;
		
		try {
			con = dataSource.getConnection();
			String query="update mvc_board" +
						"	set bStep = bStep+1 " +
						"where bGroup= ? and bStep>?";
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(strGroup));
			pstmt.setInt(2, Integer.parseInt(strStep));
			
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
	
	public BPageInfo SearchPage(int curPage, String searchoption, String search) {
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
			} else if(searchoption.equals("writer")) {
				query = "select count(*) as total from mvc_board where type = ? and bname = ?";
			} else if(searchoption.equals("content")) {
				query = "select count(*) as total from mvc_board where  type = ? and bcontent like ?";
				temp = search;
				search = "%"+temp+"%";
			}
			
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "reference");
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
	
	public String getSysname(int bId) {
		String Sysname = "";
		
		Connection con = null;
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		String query = "select fileName from mvc_board where bId = ?";
		
		try {			
			con = dataSource.getConnection();
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, bId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				Sysname = rs.getString("fileName");
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
		
		return Sysname;
	}
	
	
	public String getrealname(String sysName) {
		String realname = "";
		
		Connection con = null;
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		String query = "select realname from filename where sysname = ?";
		
		try {			
			con = dataSource.getConnection();
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, sysName);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				realname = rs.getString("realname");
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
		
		return realname;
	}
	
	
	
	public int upLike(String bId, String bUser) {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;	
		int result = 0;
		
		
		if(LikeCheck(bId, bUser)) {
			return 2;
		}
		
		String query = "update mvc_board set bLike = bLike + 1 where bid = ?";
		String query2 = "insert into likeboard values(?, ?)";
		try {			
			con = dataSource.getConnection();
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bId);
			
			pstmt2 = con.prepareStatement(query2);
			pstmt2.setString(1, bId);
			pstmt2.setString(2, bUser);
			
			int rm = pstmt.executeUpdate();
			int rm2 = pstmt2.executeUpdate();
			
			result = 1;
					
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {				
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	
	public boolean LikeCheck(String bId, String bUser) {
		Connection con = null;
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		boolean flag = false;
		String query = "select bUser from likeboard where bid = ?";
		
		try {			
			con = dataSource.getConnection();
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("bUser").equals(bUser)) {
					flag = true;
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
		
		return flag;
	}
	
	
	
	
}
