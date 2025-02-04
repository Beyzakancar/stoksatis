package business;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import core.Helper;
import dao.Userdao;
import entity.User;


public class UserController {
	
	private final Userdao userdao = new Userdao();

	public User findbyLogin(String kullaniciAdı,String sifre) {
	  if (!Helper.isKullaniciadValid(kullaniciAdı)) {
	  System.out.println("Geçersiz kullanıcı adı!");
      return null;  
	}
      System.out.println("Kullanıcı Adı: " + kullaniciAdı + ", Şifre: " + sifre);
      
    
      return this.userdao.findByLogin(kullaniciAdı, sifre);
      
	}
}
