package entity;

public class Product {
	private int id;
	private String name;
	private String code;
	private int price;
	private int stock;
	
	public Product() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stoct) {
		this.stock = stoct;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + 
				", name=" + name + 
				", code=" + code + 
				", price=" + price + 
				", stock=" + stock
				+ "]";
	}

	public Object getString(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getInt(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
