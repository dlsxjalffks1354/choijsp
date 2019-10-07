package com.study.jsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.study.jsp.dto.BDto;

public class BDao {
	
	DataSource dataSource;
	
	
	public BDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void write(String bName, String bTitle, String bContent) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "insert into mvc_board2" +
							"	(bId, bName,bTitle,bContent, bHit,bGroup,bStep,bIndent) " +
							"	values" +
							" (mvc_board2_seq.nextval, ?, ?, ?, 0, mvc_board2_seq.currval, 0, 0 )";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			int rn = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con !=null)con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public ArrayList<BDto> list() {
		
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet =null;
		
		try {
			con = dataSource.getConnection();
			
			String query = "select * " +
							" from mvc_board2 " +
							" order by bGroup desc, bStep asc";
			pstmt = con.prepareStatement(query);
			resultSet = pstmt.executeQuery();
			
			while (resultSet.next()) {
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit =resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				
				BDto dto = new BDto(bId,bName,bTitle,bContent,bDate,
							bHit,bGroup,bStep,bIndent);
				dtos.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con !=null)con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return dtos;
	}
	
	public BDto contentView(String strID)
	{
		upHit(strID);
		
		BDto dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet =null;
		
		try {
			con = dataSource.getConnection();
			
			String query = "select * from mvc_board2 where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(strID));
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next()) {
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit =resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				
				dto = new BDto(bId,bName,bTitle,bContent,bDate,
						bHit,bGroup,bStep,bIndent);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con !=null)con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return dto;
	}
	
	public void modify(String bId,String bName,String bTitle, String bContent)
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String query = "update mvc_board2" +
						"	set bName = ?, " +
						"		bTitle= ?, " +
						"		bContent = ? "+
						" where bId = ? ";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setString(4, bId);
			int rn = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con !=null)con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	private void upHit(String bId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query ="update mvc_board2 set bHit= bHit + 1 where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bId);
			
			int rn = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con !=null)con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void delete(String bId) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "delete from mvc_board2 where bId = ?";
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(bId));
			int rn =pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con !=null)con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}	
		}
	}
	
	public BDto reply_view(String str) {
		BDto dto =null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		try {
			con = dataSource.getConnection();
			String query = "select * from mvc_board2 where bId= ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(str));
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next()) {
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit =resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				
				dto = new BDto(bId,bName,bTitle,bContent,bDate,
						bHit,bGroup,bStep,bIndent);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con !=null)con.close();
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
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "insert into mvc_board2 " +
						" (bId,bName,bTitle,bContent,bGroup,bStep,bIndent) " +
						"values (mvc_board2_seq.nextval, ?, ?, ?, ?, ? ,?)";
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setInt(4, Integer.parseInt(bGroup));
			pstmt.setInt(5, Integer.parseInt(bStep) + 1);
			pstmt.setInt(6, Integer.parseInt(bIndent) + 1);
			
			int rn = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con !=null)con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}	
		}
	}
	
	private void replyShape(String strGroup, String strStep) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "update mvc_board2 " +
							"	set bStep = bStep + 1 " +
							" where bGroup = ? and bStep > ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(strGroup));
			pstmt.setInt(2, Integer.parseInt(strStep));
			
			int rn = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con !=null)con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}	
		}
	}
}

