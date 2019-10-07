package Model;

public class MemberDTO
{
	private String id;
	private String pass;
	private String name;
	private String birth;
	private String gender;
	private String phonenumber;
	private String email;
	private String address;
	private java.sql.Date date;
	
	// 비밀번호 변경시 사용할 변수
	private String passModify;
	
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	
	public String getPass()
	{
		return pass;
	}
	public void setPass(String pass)
	{
		this.pass = pass;
	}
	
	public String getPassModify()
	{
		return passModify;
	}
	public void setPassModify(String passModify)
	{
		this.passModify = passModify;
	}
	
	public String getName()
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public String getBirth()
	{
		return birth;
	}
	public void setBirth(String birth)
	{
		this.birth = birth;
	}
	
	public String getGender()
	{
		return gender;
	}
	public void setGender(String gender)
	{
		this.gender = gender;
	}
	
	public String getPhonenumber() 
	{
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber)
	{
		this.phonenumber = phonenumber;
	}
	
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}
	
	public java.sql.Date getDate()
	{
		return date;
	}
	public void setDate(java.sql.Date date) 
	{
		this.date = date;
	}
	
	
}
