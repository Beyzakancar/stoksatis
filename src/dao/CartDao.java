package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import core.db;
import entity.Cart;
import entity.Customer;

public class CartDao {
	private Connection connection;
	private ProductDao productDao;
	private Customerdao customerdao;

	public CartDao() {
		this.connection = db.getInstance();
		this.productDao = new ProductDao();
		this.customerdao = new Customerdao();
	}
	
	public ArrayList<Cart> findAll() {
		ArrayList<Cart> carts = new ArrayList<>();
		try {
			ResultSet rs = this.connection.createStatement().executeQuery("SELECT * FROM cart");
			while (rs.next()) {
				carts.add(this.match(rs));
			}
		}
			catch (SQLException exception) {
				exception.printStackTrace();
			}
			return carts;
	}
	public boolean save(Cart cart) {
		String query = "INSERT INTO cart " +
	         "(" +
			 "customer_id," +
	         "product_id," +
			 "price," +
	         "date,"+
			 "note"+
	         ")" + 
			 "VALUES (?,?,?,?,?)";
	try {
		PreparedStatement pr = this.connection.prepareStatement(query);
	pr.setInt(1, cart.getCustomerId());
	pr.setInt(2, cart.getProductId());
	pr.setInt(3, cart.getPrice());
	pr.setDate(4, Date.valueOf(cart.getDate()));
	pr.setString(5, cart.getNote());
	
	return pr.executeUpdate() != -1;
	}catch (SQLException exception) {
		exception.printStackTrace();
	}
	return true;
	}
	
	public Cart match(ResultSet rs) throws SQLException {
		Cart cart = new Cart();
		cart.setId(rs.getInt("id"));
		cart.setPrice(rs.getInt("price"));
		cart.setCustomerId(rs.getInt("customer_id"));
		cart.setProductId(rs.getInt("product_id"));
		cart.setNote(rs.getString("note"));
		cart.setDate(LocalDate.parse(rs.getString("date")));
		cart.setCustomer(this.customerdao.getById(cart.getCustomerId()));
		cart.setProduct(this.productDao.getById(cart.getProductId()));
		
		return cart;
	}
}
