package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO 
{
	Connection con;
	PreparedStatement psmt;
	ResultSet rs;
	
	public MemberDAO()
	{
		/*try
		{
			// 1. 드라이버 로딩
			Class.forName(driver);
			String id = "root";
			String pw = "1234";
			// 2. DB 커넥션 객체생성
			con = DriverManager.getConnection(url, id, pw);
			
			System.out.println("DB연결성공");
		}
		catch(Exception e)
		{
			System.out.println("DB연결실패");
		}*/
		
		try
		{
			Context ctx = new InitialContext();
			DataSource source = (DataSource)ctx.lookup("java:comp/env/jdbc/mysql");
			con = source.getConnection();
			System.out.println("커넥션풀 이용한 연결성공");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	// 자원반납
	public void dbClose()
	{
		try
		{
			if(rs!=null) rs.close();
			if(psmt!=null) psmt.close();
			if(con!=null) con.close();
		}
		catch(Exception e)
		{
			System.out.println("자원반납실패");
		}
	}
	
	// 회원가입(Signup)
	public int insert(MemberDTO dto)
	{
		int affected = 0;	// 적용된 행의 갯수
		
		try
		{
			String sql = "INSERT INTO car_member "
					+ " (id, pass, name, birth, gender, phonenumber, email, address) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPass());
			psmt.setString(3, dto.getName());
			psmt.setString(4, dto.getBirth());
			psmt.setString(5, dto.getGender());
			psmt.setString(6, dto.getPhonenumber());
			psmt.setString(7, dto.getEmail());
			psmt.setString(8, dto.getAddress());
			
			affected = psmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return affected;
	}
	
	public List<MemberDTO> selectMember(String id)
	{
		List<MemberDTO> lists = new Vector<MemberDTO>();
		
		String sql = "SELECT * FROM car_member WHERE id = ?";

		// 쿼리문을 찍어보기 위한 문장(Log)
		System.out.println("쿼리문 : " + sql);

		try
		{
			// 3. prepare 객체 생성 및 실행
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			
			rs = psmt.executeQuery();
			
			while(rs.next())
			{
				// 4. 결과셋을 DTO객체에 담는다.
				MemberDTO dto = new MemberDTO();
				
				dto.setId(rs.getString(1));
				dto.setName(rs.getString(3));
				dto.setBirth(rs.getString(4));
				dto.setGender(rs.getString(5));
				dto.setPhonenumber(rs.getString(6));
				dto.setEmail(rs.getString(7));
				dto.setAddress(rs.getString(8));
				dto.setDate(rs.getDate(9));
				
				// 5. DTO객체를 컬렉션에 추가한다.
				lists.add(dto); 
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		return lists;
	}
	
	public boolean isMember(String uid, String upw)
	{
		int memberCount = 0;
		String sql = "SELECT count(*) FROM car_member "
				+ " WHERE id = ? AND pass = ? ";
		try
		{
			// 쿼리문을 기반으로 객체 생성
			psmt = con.prepareStatement(sql);
			
			// 매개변수를 셋팅
			psmt.setString(1, uid);
			psmt.setString(2, upw);
			
			// 쿼리문 실행
			rs = psmt.executeQuery();
			rs.next();
			
			// 결과 반환 후 변수에 할당
			memberCount = rs.getInt(1);
			System.out.println("조회된회원수:" + memberCount);
			
			if(memberCount==0) return false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return true;
	}
	
	// 등록된 회원정보 찾기(id)
	public MemberDTO selectView(String id)
	{
		MemberDTO dto = new MemberDTO();
		
		String sql = "SELECT * FROM car_member "
				+ " WHERE id = ? ";
		try
		{
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			if(rs.next())
			{
				dto.setId(rs.getString(1));
				dto.setPass(rs.getString(2));
				dto.setName(rs.getString(3));
				dto.setBirth(rs.getString(4));
				dto.setGender(rs.getString(5));
				dto.setPhonenumber(rs.getString(6));
				dto.setEmail(rs.getString(7));
				dto.setAddress(rs.getString(8));
				dto.setDate(rs.getDate(9));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return dto;
	}
	
	public int updateMember(MemberDTO dto)
	{
		int affected = 0;	// 적용된 행의 갯수
		String sql = null;
		try
		{
			if(dto.getPassModify()=="")
			{
				sql = "UPDATE car_member SET "
					+ " phonenumber = ?, email = ?, address = ? "
					+ " WHERE id = ? AND pass = ?";
				
				System.out.println("쿼리문 : " + sql);
				
				psmt = con.prepareStatement(sql);
				
				psmt.setString(1, dto.getPhonenumber());
				psmt.setString(2, dto.getEmail());
				psmt.setString(3, dto.getAddress());
				psmt.setString(4, dto.getId());
				psmt.setString(5, dto.getPass());
			}
			else
			{
				sql = "UPDATE car_member SET "
					+ " phonenumber = ?, email = ?, address = ?, pass = ?"
					+ " WHERE id = ? AND pass = ?";
				
				System.out.println("쿼리문 : " + sql);
				
				psmt = con.prepareStatement(sql);
				
				psmt.setString(1, dto.getPhonenumber());
				psmt.setString(2, dto.getEmail());
				psmt.setString(3, dto.getAddress());
				psmt.setString(4, dto.getPassModify());
				psmt.setString(5, dto.getId());
				psmt.setString(6, dto.getPass());
			}
			
			

			affected = psmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return affected;
	}
	
	public int deleteMember(String id, String pass)
	{
		int affected = 0;
		
		try
		{
			String sql = "DELETE FROM car_member WHERE id = ? and pass = ?";
			
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pass);

			affected = psmt.executeUpdate();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return affected;
	}
}
