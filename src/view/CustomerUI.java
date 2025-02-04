package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.CustomerController;
import core.Helper;
import entity.Customer;
import entity.Customer.TYPE;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;

public class CustomerUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel container;
	private Customer customer;
	private CustomerController customerController;
	private JTextField fld_customer_name;
	private JTextField fld_customer_phone;
	private JTextField fld_customer_mail;
	private JComboBox<Customer.TYPE>cmb_customer_type;
	

	
	public CustomerUI(Customer customer) {	
		this.customer = customer;
		this.customerController = new CustomerController();		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 450);
		container = new JPanel();
		container.setBackground(new Color(25, 25, 112));
		container.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(container);
		container.setLayout(null);
		
		JLabel lbl_title = new JLabel("Müşteri Düzenle");
		lbl_title.setBackground(new Color(255, 255, 255));
		lbl_title.setForeground(new Color(255, 255, 255));
		lbl_title.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lbl_title.setBounds(0, 0, 382, 29);
		container.add(lbl_title);
		
		JLabel lbl_name = new JLabel("Müşteri Adı");
		lbl_name.setForeground(new Color(255, 255, 255));
		lbl_name.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		lbl_name.setBackground(new Color(255, 255, 255));
		lbl_name.setBounds(0, 30, 382, 29);
		container.add(lbl_name);
		
		fld_customer_name = new JTextField();
		fld_customer_name.setBounds(0, 58, 382, 36);
		container.add(fld_customer_name);
		fld_customer_name.setColumns(10);
		
		JLabel lbl_type = new JLabel("Müşteri tipi");
		lbl_type.setForeground(new Color(255, 255, 255));
		lbl_type.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		lbl_type.setBounds(0, 86, 382, 36);
		container.add(lbl_type);
		
		cmb_customer_type = new JComboBox<>();
		cmb_customer_type.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 11));
		cmb_customer_type.setBounds(0, 121, 382, 36);
		container.add(cmb_customer_type);
		
		JLabel lbl_customer_phone = new JLabel("Telefon");
		lbl_customer_phone.setForeground(new Color(255, 255, 255));
		lbl_customer_phone.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		lbl_customer_phone.setBounds(0, 153, 382, 36);
		container.add(lbl_customer_phone);
		
		fld_customer_phone = new JTextField();
		fld_customer_phone.setBounds(0, 186, 382, 36);
		container.add(fld_customer_phone);
		fld_customer_phone.setColumns(10);
		
		JLabel lbl_customer_mail = new JLabel("E posta");
		lbl_customer_mail.setForeground(new Color(255, 255, 255));
		lbl_customer_mail.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		lbl_customer_mail.setBounds(0, 228, 382, 21);
		container.add(lbl_customer_mail);
		
		fld_customer_mail = new JTextField();
		fld_customer_mail.setBounds(0, 247, 382, 40);
		container.add(fld_customer_mail);
		fld_customer_mail.setColumns(10);
		
		JLabel lbl_customer_adress = new JLabel("Müşteri Adresi");
		lbl_customer_adress.setForeground(new Color(255, 255, 255));
		lbl_customer_adress.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		lbl_customer_adress.setBounds(0, 297, 382, 21);
		container.add(lbl_customer_adress);
		
		JTextArea tarea_customer_adress = new JTextArea();
		tarea_customer_adress.setBounds(0, 322, 382, 40);
		container.add(tarea_customer_adress);
		
		JButton btn_customer_save = new JButton("Kaydet");
		btn_customer_save.setForeground(new Color(255, 255, 255));
		btn_customer_save.setBackground(new Color(25, 25, 112));
		btn_customer_save.setBounds(122, 372, 144, 21);
		container.add(btn_customer_save);
		setTitle("Müşteri Ekle/Düzenle");
		
	    this.cmb_customer_type.setModel(new DefaultComboBoxModel<>(Customer.TYPE.values()));
		
		if(this.customer.getId() == 0) {
			lbl_title.setText("Müşteri Ekle");
		}else {
			lbl_title.setText("Müşteri Düzenle");
			this.fld_customer_name.setText(this.customer.getName());
			this.fld_customer_mail.setText(this.customer.getMail());
			this.fld_customer_phone.setText(this.customer.getPhone());
			tarea_customer_adress.setText(this.customer.getAddress());
			this.cmb_customer_type.getModel().setSelectedItem(this.customer.getType());
		}
		
		btn_customer_save.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JTextField[] checkList = {fld_customer_name, fld_customer_phone};
			if (Helper.isFieldListEmpty(checkList)) {
				Helper.showMsg("fiil");
			}else if(!Helper.isFieldEmpty(fld_customer_mail) && !Helper.isEmailValid(fld_customer_mail.getText())) {
				Helper.showMsg("Lütfen Geçerli Müşteri Giriniz");
			}else {
				boolean result = false ;
				customer.setName(fld_customer_name.getText());
				customer.setPhone(fld_customer_phone.getText());
				customer.setMail(fld_customer_mail.getText());
				customer.setAddress(tarea_customer_adress.getText());
				customer.setType((Customer.TYPE) cmb_customer_type.getSelectedItem());
				
				if (customer.getId() == 0) {
					result = customerController.save(customer);	
				}else {
					result = customerController.update(customer);
				}
				
				if (result) {
					Helper.showMsg("done");
					dispose();
				}else {
					Helper.showMsg("error");
				}
			
			}
		}		
     });					
   }
}
