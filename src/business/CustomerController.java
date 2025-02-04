//müşteri verilerinin yönetimini düzenlemek

package business;

import java.util.ArrayList;

import core.Helper;
import dao.Customerdao;
import entity.Customer;

public class CustomerController {
	private final Customerdao customerdao = new Customerdao();
	
	public ArrayList<Customer> findAll() {
	return this.customerdao.findAll();
	}
	
	public boolean save(Customer customer) {
		return this.customerdao.save(customer);
		
	}
	public Customer getById(int id) {
		return this.customerdao.getById(id);
	}
	
	public boolean update(Customer customer) {
		if(this.getById(customer.getId()) == null) {
			Helper.showMsg(customer.getId() + "ID kayıtlı müşteri bulunamadı!");
			return false;
		}
		return this.customerdao.update(customer);
	}
	
	public boolean delete(int id) {
		if(this.getById(id) == null) {
			Helper.showMsg(id + "ID kayıtlı müşteri bulunamadı");
			return false;
		}
		return this.customerdao.delete(id);
	}
	
	public ArrayList<Customer> filter(String name ,Customer.TYPE type) {
		String query = "SELECT * FROM customer";
		ArrayList<String> whereList = new ArrayList<>();
		
		if (name.length() > 0) {
			whereList.add("name LIKE '%" + name + "%'");
		}
		if(type != null) {
			whereList.add("type = '"+ type +"'");
		}
		if(whereList.size() > 0) {
			String whereQuery = String.join(" AND ",whereList);
			query += " WHERE " + whereQuery;
		}
			return this.customerdao.query(query);	
		
		
		
	}
}
