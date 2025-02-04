package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.UserController;
import core.Helper;
import entity.User;

import javax.swing.UIManager;


import javax.swing.JLabel;
import java.awt.Font;



import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import javax.swing.SwingConstants;
import java.awt.GridLayout;

public class loginUI extends JFrame {

	private UserController userController;
	private static final long serialVersionUID = 1L;
	private JPanel container;
	private JTextField fldkullanıcı_adı;
	private JPasswordField fldşifre;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginUI frame = new loginUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	   
	public loginUI() {
	
		this.userController = new UserController();
		setTitle("StokSatış");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 502);
		setUndecorated(false);
		setResizable(true);	
		
		container = new JPanel();
		container.setBackground(new Color(255, 255, 255));
		setContentPane(container);
		container.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 228, 225));
		container.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Beyz Kuru Gıda");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(138, 51, 298, 125);
		panel.add(lblNewLabel);
		
		JPanel panel_botom = new JPanel();
		panel_botom.setBackground(new Color(255, 255, 255));
		container.add(panel_botom);
		panel_botom.setLayout(null);
		
		JLabel lblkullanıcıadı = new JLabel("Kullanıcı Adı");
		lblkullanıcıadı.setBounds(179, 67, 100, 23);
		lblkullanıcıadı.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
		panel_botom.add(lblkullanıcıadı);
		
		fldkullanıcı_adı = new JTextField();
		fldkullanıcı_adı.setBounds(312, 68, 133, 23);
		fldkullanıcı_adı.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		panel_botom.add(fldkullanıcı_adı);
		fldkullanıcı_adı.setColumns(10);
		
		JLabel lblşifre = new JLabel("Şifreniz");
		lblşifre.setBounds(179, 125, 100, 23);
		lblşifre.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
		panel_botom.add(lblşifre);
		
		fldşifre = new JPasswordField();
		fldşifre.setBounds(312, 126, 133, 23);
		fldşifre.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		panel_botom.add(fldşifre);
		
		JLabel label = new JLabel("");
		label.setBounds(0, 192, 279, 96);
		panel_botom.add(label);
		
		JButton btngiriş = new JButton("Giriş Yap");
		btngiriş.setBounds(345, 181, 100, 34);
		btngiriş.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
		btngiriş.setBackground(new Color(255, 192, 203));
		btngiriş.setForeground(new Color(0, 0, 0));
		btngiriş.setFocusPainted(false); 
		btngiriş.setBorderPainted(false);
		btngiriş.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		panel_botom.add(btngiriş);
		
		btngiriş.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				String kullaniciAdı = fldkullanıcı_adı.getText();
		        String sifre = String.valueOf(fldşifre.getPassword());
		        
				JTextField[] checkList = {fldkullanıcı_adı,fldşifre};	 
		        if (!Helper.isKullaniciadValid(fldkullanıcı_adı.getText())) {
		        	Helper.showMsg("Geçerli bir Kullanıcı adı giriniz !");		        		        	
		        }												
		        else if(Helper.isFieldListEmpty(checkList))  {	        	        
		      
		        	Helper.showMsg("fiil");
		        }else { 	
		        	User user = loginUI.this.userController.findbyLogin(kullaniciAdı, sifre);
		        	if (user == null) {
		        		Helper.showMsg("Girdiğiniz bilgilere göre kullanıcı bulunamadı!");
		        	} else {
		        		dispose();
		        		DashboardUI dashboardUI = new DashboardUI(user);
		        		dashboardUI.setVisible(true);
		        	}
			    }									
			}

			
			
		});
								
	}
}
