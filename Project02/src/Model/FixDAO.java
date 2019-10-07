package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class FixDAO
{
	Connection con;
	PreparedStatement psmt;
	ResultSet rs;
	
	public FixDAO()
	{
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
	
	// 차량수리내역 등록하기(fix)
	public int insert(FixDTO dto)
	{
		int affected = 0;	// 적용된 행의 갯수

		try
		{
			String sql = "INSERT INTO car_fix "
					+ " (day, parts_name, parts_cnt, parts_money, fix_money, tax, money, shop_name, pay, gita, user_id) "
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, dto.getDay());
			psmt.setString(2, dto.getParts_name());
			psmt.setString(3, dto.getParts_cnt());
			psmt.setString(4, dto.getParts_money());
			psmt.setString(5, dto.getFix_money());
			psmt.setString(6, dto.getTax());
			psmt.setString(7, dto.getMoney());
			psmt.setString(8, dto.getShop_name());
			psmt.setString(9, dto.getPay());
			psmt.setString(10, dto.getGita());
			psmt.setString(11, dto.getUser_id());

			affected = psmt.executeUpdate();
			System.out.println(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return affected;
	}
	
	// 수리내역 가져오기
	public List<FixDTO> selectInfo(String id)
	{
		List<FixDTO> lists = new Vector<FixDTO>();
		
		String sql = "SELECT * FROM car_fix WHERE user_id = ?";

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
				FixDTO dto = new FixDTO();
				
				dto.setDay(rs.getString(1));
				dto.setParts_name(rs.getString(2));
				dto.setParts_cnt(rs.getString(3));
				dto.setParts_money(rs.getString(4));
				dto.setFix_money(rs.getString(5));
				dto.setTax(rs.getString(6));
				dto.setMoney(rs.getString(7));
				dto.setShop_name(rs.getString(8));
				dto.setPay(rs.getString(9));
				dto.setGita(rs.getString(10));
				
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
	
	// 등록된 수리내역 찾기 읽기(이름으로)
	public FixDTO selectView(String id, String name)
	{
		FixDTO dto = new FixDTO();
		
		String sql = "SELECT * FROM car_fix "
				+ " WHERE user_id = ? and parts_name = ? ";
		try
		{
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, name);
			rs = psmt.executeQuery();
			
			if(rs.next())
			{
				dto.setDay(rs.getString(1));
				dto.setParts_name(rs.getString(2));
				dto.setParts_cnt(rs.getString(3));
				dto.setParts_money(rs.getString(4));
				dto.setFix_money(rs.getString(5));
				dto.setTax(rs.getString(6));
				dto.setMoney(rs.getString(7));
				dto.setShop_name(rs.getString(8));
				dto.setPay(rs.getString(9));
				dto.setGita(rs.getString(10));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return dto;
	}
	
	// 자동차 수리내역 수정
	public int updateFix(FixDTO dto)
	{
		int affected = 0;	// 적용된 행의 갯수

		try
		{
			String sql = "UPDATE car_fix SET "
					+ " day = ?, parts_cnt = ?, parts_money = ?, fix_money = ?, tax = ?, "
					+ " money = ?, shop_name = ?, pay = ?, gita = ? "
					+ " WHERE user_id = ? AND parts_name = ?";
			
			System.out.println("쿼리문 : " + sql);
			
			psmt = con.prepareStatement(sql);
			
			psmt.setString(1, dto.getDay());
			psmt.setString(2, dto.getParts_cnt());
			psmt.setString(3, dto.getParts_money());
			psmt.setString(4, dto.getFix_money());
			psmt.setString(5, dto.getTax());
			psmt.setString(6, dto.getMoney());
			psmt.setString(7, dto.getShop_name());
			psmt.setString(8, dto.getPay());
			psmt.setString(9, dto.getGita());
			psmt.setString(10, dto.getUser_id());
			psmt.setString(11, dto.getParts_name());

			affected = psmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return affected;
	}
	
	// 자동차 수리내역 삭제
	public int deleteFix(String parts_name)
	{
		int affected = 0;
		
		try
		{
			String sql = "DELETE FROM car_fix WHERE parts_name = ?";
			
			psmt = con.prepareStatement(sql);
			psmt.setString(1, parts_name);

			affected = psmt.executeUpdate();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return affected;
	}
}
