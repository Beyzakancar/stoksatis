//Customer sınıfı, uygulamada müşteri bilgilerini modellemek için kullanılan bir veri nesnesidir.
//Bu sınıf, müşteri verilerinin okunabilir ve yönetilebilir olmasını sağlar.

package entity;

public class Customer {
	private int id;
	private String name;
	private String phone;
	private String mail;
	private String address;
	private TYPE type;	
	public enum TYPE{
		PERSON, 
	    COMPANY
	}//kodun daha okunabilir ve bakımı daha kolay olmasını sağlar çünkü değerler genellikle bir sınıfın sabitleri olarak tanımlanır
	public Customer(int id, String name, String phone, String mail, String address, TYPE type) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.mail = mail;
		this.address = address;
		this.type = type;
	}
	public Customer() {
	
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public TYPE getType() {
		return type;
	}
	public void setType(TYPE type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + 
				", name=" + name + 
				", phone=" + phone +
				", mail=" + mail +
				", address=" + address+ 
				", type=" + type + "]";
	}

}
