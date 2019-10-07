package com.study.jsp.sign;

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

import com.study.jsp.sign.MemberDAO;
import com.study.jsp.PageInfo.MPageInfo;
import com.study.jsp.PageInfo.NPageInfo;
import com.study.jsp.dao.NDao;
import com.study.jsp.dto.BDto;
import com.study.jsp.dto.MDto;
import com.study.jsp.dto.NDto;

public class MemberDAO 
{
	public static final int MEMBER_NONEXISTENT = 0;
	public static final int MEMBER_EXISTENT =1;
	public static final int MEMBER_JOIN_FAIL = 0;
	public static final int MEMBER_JOIN_SUCCESS =1;
	public static final int MEMBER_LOGIN_PW_NO_GOOD =0;
	public static final int MEMBER_LOGIN_SUCCESS=1;
	public static final int MEMBER_LOGIN_IS_NOT=-1;
	public static final int MEMBER_WITHDRAW_SUCCESS=1;
	public static final int MEMBER_WITHDRAW_FAIL=0;
	public static final int MEMBER_LOGIN_BANNED = -2;
	
	
	
	
	private static MemberDAO instance = new MemberDAO();
	DataSource dataSource =null;
	
	int listCount =5; //한 페이지당 보여줄 게시물의 갯수
	int pageCount =10;//하단에 보여줄 페이지 리스트의 갯수
	
	
	public MemberDAO() 
	{
		
	}
	
	public static MemberDAO getInstance() 
	{
		return instance;
	}
	
	public int insertMember(MemberDTO dto)
	{
		
		int ri =0;
		
		Connection con =null;
		PreparedStatement pstmt =null;
		String query = "insert into members1 values (?,?,?,?,?,?,?,?,?)";
		
		try
		{
			con =getConnection();
			pstmt =con.prepareStatement(query);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.geteMail());
			pstmt.setTimestamp(5, dto.getrDate());
			pstmt.setString(6, dto.getAddress());
			pstmt.setString(7, dto.getNickname());
			pstmt.setString(8, dto.getManager());
			pstmt.setString(9, dto.getBan());
			pstmt.executeUpdate();
			ri = MemberDAO.MEMBER_JOIN_SUCCESS;
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e2)
			{
				e2.printStackTrace();
			}
		}
		
		return ri;
	}
	
	
	public boolean snsUserCheck(String id) {
		boolean flag = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select count(*) as num from members1 where id = ?";
				
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();	
			
			if(rs.next()) {				
				if(rs.getInt("num") >= 1) {
					System.out.println("dddddd");
					flag = true;
				}				
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	
	
	
	public boolean snsUserBan(String id) {
		boolean flag = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select ban from members1 where id = ?";		
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();	
			
			if(rs.next()) {				
				if(rs.getString("ban").equals("y")) {
					flag = true;
				}				
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	
	public int confirmId(String id)
	{
		int ri = 0;
		
		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet set =null;
		
		String query ="select id from members1 where id =? ";
		
		try
		{
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			set=pstmt.executeQuery();
			if(set.next())
			{
				ri=MemberDAO.MEMBER_EXISTENT;
			}
			else
			{
				ri = MemberDAO.MEMBER_NONEXISTENT;
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				set.close();
				pstmt.close();
				con.close();
			}catch(Exception e2)
			{
				e2.printStackTrace();
			}
		}
		return ri;
	}
	
	
	public int userCheck (String id,String pw)
	{
		int ri = 0;
		String dbPw;
		
		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet set =null;
		String query ="select pw,ban from members1 where id=?";
		
		try
		{
			con=getConnection();
			pstmt= con.prepareStatement(query);
			pstmt.setString(1,id);
			set = pstmt.executeQuery();
			
			if(set.next())
			{
				dbPw=set.getString("pw");
				if(dbPw.equals(pw))
				{
					if(set.getString("ban").equals("y")) {
						ri = MemberDAO.MEMBER_LOGIN_BANNED;
					}else {
						System.out.println("login ok");
						ri = MemberDAO.MEMBER_LOGIN_SUCCESS;
					}
				}else
				{
					System.out.println("login fail");
					ri = MemberDAO.MEMBER_LOGIN_PW_NO_GOOD;
				}
			}else 
			{
				System.out.println("login fail");
				ri = MemberDAO.MEMBER_LOGIN_IS_NOT;
			}
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				set.close();
				pstmt.close();
				con.close();
			}
			catch(Exception e2)
			{
				e2.printStackTrace();
			}
		}
		return ri;
	}
	
	
	
	public MemberDTO getMember(String id)
	{
		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet set =null;
		String query ="select * from members1 where id =? ";
		MemberDTO dto =null;
		
		try 
		{
			con=getConnection();
			pstmt= con.prepareStatement(query);
			pstmt.setString(1,id);
			set = pstmt.executeQuery();
			
			if(set.next())
			{
				dto = new MemberDTO();
				dto.setId(set.getString("id"));
				dto.setPw(set.getString("pw"));
				dto.setName(set.getString("name"));
				dto.seteMail(set.getString("eMail"));
				dto.setrDate(set.getTimestamp("rDate"));
				dto.setAddress(set.getString("address"));	
				dto.setNickname(set.getString("Nickname"));
				dto.setManager(set.getString("manager"));
				dto.setBan(set.getString("ban"));
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				set.close();
				pstmt.close();
				con.close();
			}catch(Exception e2)
			{
				e2.printStackTrace();
			}
		}
		
		return dto;
	}
	
	public int updateMember(MemberDTO dto)
	{
		int ri =0;
		Connection con =null;
		PreparedStatement pstmt =null;
		String query ="update members1 set pw=?, eMail=?,address=?,nickname=? where id=?";
		
		
		try 
		{
			con=getConnection();
			pstmt= con.prepareStatement(query);
			pstmt.setString(1, dto.getPw());
			pstmt.setString(2, dto.geteMail());
			pstmt.setString(3, dto.getAddress());
			pstmt.setString(4, dto.getNickname());
			pstmt.setString(5, dto.getId());
			ri=pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				pstmt.close();
				con.close();
			}
			catch(Exception e2)
			{
				e2.printStackTrace();
			}
		}
		return ri;
	}
	
	
	public int userCheck2(String id,String pw)
	{
		int ri = 0;
		String dbPw;
		
		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet set =null;
		String query ="select pw from members1 where id=?";
		
		try
		{
			con=getConnection();
			pstmt= con.prepareStatement(query);
			pstmt.setString(1,id);
			set = pstmt.executeQuery();
			
			if(set.next())
			{
				dbPw=set.getString("pw");
				if(dbPw.equals(pw))
				{
					System.out.println("check ok");
					ri = MemberDAO.MEMBER_LOGIN_SUCCESS;
				}else
				{
					System.out.println("check fail");
					ri = MemberDAO.MEMBER_LOGIN_PW_NO_GOOD;
				}
			}else 
			{
				System.out.println("check fail");
				ri = MemberDAO.MEMBER_LOGIN_IS_NOT;
			}
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				set.close();
				pstmt.close();
				con.close();
			}
			catch(Exception e2)
			{
				e2.printStackTrace();
			}
		}
		return ri;
	}
	
	
	public MemberDTO deletemember(String id, String pw)
	{
		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet set =null;
		
		String query ="delete from members1 where id =? and pw=?";
		MemberDTO dto =null;
		
		try 
		{
			con=getConnection();
			pstmt= con.prepareStatement(query);
			pstmt.setString(1,id);
			pstmt.setString(2,pw);
			set = pstmt.executeQuery();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				set.close();
				pstmt.close();
				con.close();
			}catch(Exception e2)
			{
				e2.printStackTrace();
			}
		}
		
		return dto;
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

}
