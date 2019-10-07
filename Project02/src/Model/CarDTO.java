package Model;

public class CarDTO
{
	private String brand;
	private String car_name;
	private String car_type;
	private String year;
	private String transmission;
	private String km;
	// user_id를 입력하기 위한 회원의 아이디
	private String id;
	
	// 수리내역을 출력하기 위한 변수선언
	private String day;
	private String parts_name;
	private String parts_cnt;
	private String parts_money;
	private String fix_money;
	private String tax;
	private String money;
	private String shop_name;
	private String pay;
	private String gita;
	
	//==========================================================//
	
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String getCar_name() {
		return car_name;
	}
	public void setCar_name(String car_name) {
		this.car_name = car_name;
	}
	
	public String getCar_type() {
		return car_type;
	}
	public void setCar_type(String car_type) {
		this.car_type = car_type;
	}
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
	public String getTransmission() {
		return transmission;
	}
	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}
	
	public String getKm() {
		return km;
	}
	public void setKm(String km) {
		this.km = km;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	//==========================================================//
	
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getParts_name() {
		return parts_name;
	}
	public void setParts_name(String parts_name) {
		this.parts_name = parts_name;
	}
	public String getParts_cnt() {
		return parts_cnt;
	}
	public void setParts_cnt(String parts_cnt) {
		this.parts_cnt = parts_cnt;
	}
	public String getParts_money() {
		return parts_money;
	}
	public void setParts_money(String parts_money) {
		this.parts_money = parts_money;
	}
	public String getFix_money() {
		return fix_money;
	}
	public void setFix_money(String fix_money) {
		this.fix_money = fix_money;
	}
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	public String getPay() {
		return pay;
	}
	public void setPay(String pay) {
		this.pay = pay;
	}
	public String getGita() {
		return gita;
	}
	public void setGita(String gita) {
		this.gita = gita;
	}
}
