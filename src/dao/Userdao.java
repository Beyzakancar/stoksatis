package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import core.db;
import entity.User;

public class Userdao {	
	private Connection connection;

	public Userdao() {
		super();
		this.connection = db.getInstance();
		if (this.connection != null) {
            System.out.println("Veritabanı bağlantısı başarılı!");
        } else {
            System.out.println("Veritabanı bağlantısı başarısız!");
        }
	}
	
	public User findByLogin(String kullaniciad, String sifre){
		
		User user = null;
		
		String query = "SELECT * FROM user WHERE kullaniciad = ? AND sifre = ?";
	    try {
	    	PreparedStatement pr = this.connection.prepareStatement(query);
	    	pr.setString(1, kullaniciad);
	    	pr.setString(2, sifre);
	    	ResultSet rs = pr.executeQuery();
	    	if (rs.next()) { 
	    		 	 user = this.match(rs);   			    		
	    	}	 else {
	            System.out.println("Kullanıcı bulunamadı.");
	        }   		    		    	
	    }catch (SQLException exception) {
	    	exception.printStackTrace();
	    }				
	return user;		
	}
	
	public ArrayList<User> findAll() {
		ArrayList<User> users = new ArrayList<>();
		try {
			ResultSet rs = this.connection.createStatement().executeQuery("SELECT * FROM user");
			while (rs.next()) {
				users.add(this.match(rs));
			}
		}
			catch (SQLException exception) {
				exception.printStackTrace();
			}
			return users;
	    }
		
		public User match(ResultSet rs) throws SQLException {
			User user = new User(0, null, null, null);
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setSifre(rs.getString("sifre"));
			user.setKullaniciad(rs.getString("kullaniciad"));
			return user;
		}
	
}
	
	
	
	

