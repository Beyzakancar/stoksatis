package entity;

public class User {	
	private int id;
	private String kullaniciad;
	private String sifre;
	private String name;
	
	public User(int id, String kullaniciad, String sifre, String name) {
		super();
		this.id = id;
		this.kullaniciad = kullaniciad;
		this.sifre = sifre;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKullaniciad() {
		return kullaniciad;
	}
	public void setKullaniciad(String kullaniciad) {
		this.kullaniciad = kullaniciad;
	}
	public String getSifre() {
		return sifre;
	}
	public void setSifre(String sifre) {
		this.sifre = sifre;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}

