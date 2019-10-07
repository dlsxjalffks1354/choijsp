package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CarDAO
{
	Connection con;
	PreparedStatement psmt;
	ResultSet rs;
	
	public CarDAO()
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
	
	// 차량정보 등록하기(MyCar_Input)
	public int insert(CarDTO dto)
	{
		int affected = 0;	// 적용된 행의 갯수

		try
		{
			String sql = "INSERT INTO car_info "
					+ " (brand, car_name, car_type, year, trans, km, user_id) "
					+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
			
			psmt = con.prepareStatement(sql);
			psmt.setString(1, dto.getBrand());
			psmt.setString(2, dto.getCar_name());
			psmt.setString(3, dto.getCar_type());
			psmt.setString(4, dto.getYear());
			psmt.setString(5, dto.getTransmission());
			psmt.setString(6, dto.getKm());
			psmt.setString(7, dto.getId());

			affected = psmt.executeUpdate();
			System.out.println(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return affected;
	}
	
	public List<CarDTO> selectInfo(String id)
	{
		List<CarDTO> lists = new Vector<CarDTO>();
		
		String sql = "SELECT * FROM car_info WHERE user_id = ?";
		
		try
		{
	
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			
			rs = psmt.executeQuery();
			
			while(rs.next())
			{
				// 4. 결과셋을 DTO객체에 담는다.
				CarDTO dto = new CarDTO();
				
				dto.setBrand(rs.getString(1));
				dto.setCar_name(rs.getString(2));
				dto.setCar_type(rs.getString(3));
				dto.setYear(rs.getString(4));
				dto.setTransmission(rs.getString(5));
				dto.setKm(rs.getString(6));
				
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
	
	// 등록된 차량 찾기 읽기(이름으로)
	public CarDTO selectView(String id, String name)
	{
		CarDTO dto = new CarDTO();
		
		String sql = "SELECT * FROM car_info "
				+ " WHERE user_id = ? and car_name = ? ";
		try
		{
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, name);
			rs = psmt.executeQuery();
			
			if(rs.next())
			{
				dto.setBrand(rs.getString(1));
				dto.setCar_name(rs.getString(2));
				dto.setCar_type(rs.getString(3));
				dto.setYear(rs.getString(4));
				dto.setTransmission(rs.getString(5));
				dto.setKm(rs.getString(6));
				dto.setId(rs.getString(7));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return dto;
	}
	
	public int updateCar(CarDTO dto)
	{
		int affected = 0;	// 적용된 행의 갯수

		try
		{
			String sql = "UPDATE car_info SET "
					+ " car_type = ?, year = ?, trans = ?, km = ? "
					+ " WHERE user_id = ? AND car_name = ?";
			
			System.out.println("쿼리문 : " + sql);
			
			psmt = con.prepareStatement(sql);
			
			psmt.setString(1, dto.getCar_type());
			psmt.setString(2, dto.getYear());
			psmt.setString(3, dto.getTransmission());
			psmt.setString(4, dto.getKm());
			psmt.setString(5, dto.getId());
			psmt.setString(6, dto.getCar_name());

			affected = psmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return affected;
	}
	
	public int deleteCar(String car_name)
	{
		int affected = 0;
		
		try
		{
			String sql = "DELETE FROM car_info WHERE car_name = ?";
			
			psmt = con.prepareStatement(sql);
			psmt.setString(1, car_name);

			affected = psmt.executeUpdate();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return affected;
	}
}
