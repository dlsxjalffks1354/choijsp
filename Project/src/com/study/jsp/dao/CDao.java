package com.study.jsp.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.study.jsp.dto.CDto;


public class CDao {
	
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private static CDao instance = new CDao();
	DataSource dataSource =null;
	
	int listCount =5; //한 페이지당 보여줄 게시물의 갯수
	int pageCount =10;//하단에 보여줄 페이지 리스트의 갯수
	
	
	public CDao() {
		try {
			//lookup 함수의 파라메터는 context.xml에 설정된
			// name(jdbc/Oracle11g)과 동일해야한다.
			Context context = new InitialContext();
			dataSource =(DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static CDao getInstance() 
	{
		return instance;
	}
	
	public int getSeq() 
	{
		int result = 1;
		try {
			con =dataSource.getConnection();
			
			// 시퀀스 값을 가져온다. (DUAL : 시퀀스 값을 가져오기위한 임시 테이블)
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COMMENT_SEQ.NEXTVAL FROM DUAL");

			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery(); // 쿼리 실행

			if (rs.next())	result = rs.getInt(1);

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

		close();
		return result;
	} // end getSeq
	
	
	
	public boolean insertComment(CDto comment)
	{
		boolean result = false;
		
		try {
			con =dataSource.getConnection();

			// 자동 커밋을 false로 한다.
			con.setAutoCommit(false);
			
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO BOARDCOMMENT");
			sql.append(" (CNUM, CBOARD, CID, CDATE,CNICKNAME");
			sql.append(" , CPARENT, CCONTENT)");
			sql.append(" VALUES(?,?,?,sysdate,?,?,?)");
	
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, comment.getCnum());
			pstmt.setInt(2, comment.getCboard());
			pstmt.setString(3, comment.getCid());
			pstmt.setString(4, comment.getCnickname());
			pstmt.setInt(5, comment.getCparent());
			pstmt.setString(6, comment.getCcontent());
			
			int flag = pstmt.executeUpdate();
			if(flag > 0){
				result = true;
				con.commit(); // 완료시 커밋
			}
			
		} catch (Exception e) {
			try {
				con.rollback(); // 오류시 롤백
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} 
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		
		close();
		return result;	
	} // end boardInsert();	
	
	// 댓글 목록 가져오기
	public ArrayList<CDto> getCommentList(int boardNum)
	{
		ArrayList<CDto> list = new ArrayList<CDto>();
		
		try {
			con =dataSource.getConnection();
			
			/* 댓글의 페이지 처리를 하고싶다면 이 쿼리를 사용하면 된다.
			 * SELECT * FROM
			 *			(SELECT  ROWNUM AS rnum,
             *  				 data.*
        	 *		 	FROM
             *   				(SELECT LEVEL,
             *           				COMMENT_NUM,
             *             				COMMENT_BOARD,
             *                   		COMMENT_ID,
             *                   		COMMENT_DATE,
             *                   		COMMENT_PARENT,
             *                   		COMMENT_CONTENT
             *   				 FROM BOARD_COMMENT
             *   				 WHERE COMMENT_BOARD = ?
             *      			 START WITH COMMENT_PARENT = 0
             *      			 CONNECT BY PRIOR COMMENT_NUM = COMMENT_PARENT) 
             * 			 data)
			 *	WHERE rnum>=? and rnum<=? ;
			 */
			
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT CNUM, CBOARD,");
			sql.append("			CID,CNICKNAME ,CDATE,");
			sql.append("			CPARENT,CCONTENT");
			sql.append("	FROM BOARDCOMMENT");
			sql.append("	WHERE CBOARD = ?");
			sql.append("	START WITH CPARENT = 0");
			sql.append("	CONNECT BY PRIOR CNUM = CPARENT");         
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, boardNum);
			
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				CDto comment = new CDto();
				comment.setCnum(rs.getInt("CNUM"));
				comment.setCboard(rs.getInt("CBOARD"));
				comment.setCid(rs.getString("CID"));
				comment.setCnickname(rs.getString("CNICKNAME"));
				comment.setcDate(rs.getTimestamp("CDATE"));
				comment.setCparent(rs.getInt("CPARENT"));
				comment.setCcontent(rs.getString("CCONTENT"));
				list.add(comment);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		
		close();
		return list;
	} // end getCommentList
	
	
	 public boolean deleteComment(int cnum) 
	    {
	        boolean result = false;
	 
	        try {
	            con =dataSource.getConnection();
	            con.setAutoCommit(false); // 자동 커밋을 false로 한다.
	 
	            StringBuffer sql = new StringBuffer();
	            sql.append("DELETE FROM BOARDCOMMENT");
	            sql.append(" WHERE CNUM IN");
	            sql.append(" (SELECT CNUM");
	            sql.append(" FROM BOARDCOMMENT");
	            sql.append(" START WITH CNUM = ?");
	            sql.append(" CONNECT BY PRIOR CNUM = CPARENT) ");
	            
	            pstmt = con.prepareStatement(sql.toString());
	            pstmt.setInt(1, cnum);
	            
	            int flag = pstmt.executeUpdate();
	            if(flag > 0){
	                result = true;
	                con.commit(); // 완료시 커밋
	            }    
	            
	        } catch (Exception e) {
	            try {
	                con.rollback(); // 오류시 롤백
	            } catch (SQLException sqle) {
	                sqle.printStackTrace();
	            }
	            throw new RuntimeException(e.getMessage());
	        }
	 
	        close();
	        return result;
	    } // end deleteComment    
	 
	 
	 public boolean updateComment(CDto dto) 
	    {
	        boolean result = false;
	        
	        try{
	        	con =dataSource.getConnection();
	            con.setAutoCommit(false); // 자동 커밋을 false로 한다.
	            
	            StringBuffer sql = new StringBuffer();
	            sql.append("UPDATE BOARDCOMMENT SET");
	            sql.append(" CCONTENT = ?");
	            sql.append(" WHERE CNUM = ?");
	 
	            pstmt = con.prepareStatement(sql.toString());
	            pstmt.setString(1, dto.getCcontent());
	            pstmt.setInt(2, dto.getCnum());
	 
	            int flag = pstmt.executeUpdate();
	            if(flag > 0){
	                result = true;
	                con.commit(); // 완료시 커밋
	            }
	            
	        } catch (Exception e) {
	            try {
	                con.rollback(); // 오류시 롤백
	            } catch (SQLException sqle) {
	                sqle.printStackTrace();
	            }
	            e.printStackTrace();
	            throw new RuntimeException(e.getMessage());
	        }
	    
	        close();
	        return result;
	    } // end updateComment    

	    
	 public CDto getComment(int cnum)
	    {
	        CDto dto = null;
	        
	        try {
	        	con =dataSource.getConnection();
	            
	            StringBuffer sql = new StringBuffer();
	            sql.append("SELECT * FROM BOARDCOMMENT WHERE CNUM = ?");
	            
	            pstmt = con.prepareStatement(sql.toString());
	            pstmt.setInt(1, cnum);
	            
	            rs = pstmt.executeQuery();
	            while(rs.next())
	            {
	            	dto = new CDto();
	            	dto.setCnum(rs.getInt("CNUM"));
	            	dto.setCboard(rs.getInt("CBOARD"));
	            	dto.setCid(rs.getString("CID"));
	            	dto.setCnickname(rs.getString("CNICKNAME"));
	            	dto.setcDate(rs.getTimestamp("CDATE"));
	            	dto.setCparent(rs.getInt("CPARENT"));
	            	dto.setCcontent(rs.getString("CCONTENT"));
	            }
	        } catch (Exception e) {
	            throw new RuntimeException(e.getMessage());
	        }
	        
	        close();
	        return dto; 
	    } // end getGuestbook



	
	private void close()
	{
		try {
			if ( pstmt != null ){ pstmt.close(); pstmt=null; }
			if ( con != null ){ con.close(); con=null;	}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	} // end close()	
	
	
}
