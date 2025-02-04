package view;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import business.BasketController;
import business.CartController;
import business.ProductController;
import core.Helper;
import entity.Basket;
import entity.Cart;
import entity.Customer;
import entity.Product;

import javax.swing.JLabel;
import java.awt.Font;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.Component;

public class CartUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel container;
	private Customer customer;
	private BasketController basketController;
	private CartController cartController;
	private JLabel lbl_customer_name;
	private JFormattedTextField fld_cart_date;
	private JTextArea tarea_cart_note;
	private ProductController productController;

	public CartUI(Customer customer) {
		this.customer = customer;
		this.basketController = new BasketController();
		this.cartController = new CartController();
		this.productController = new ProductController();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 355, 407);
		container = new JPanel();
		container.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(container);
		container.setLayout(null);
		
		JLabel lbl_title = new JLabel("Sipariş Oluştur");
		lbl_title.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 16));
		lbl_title.setBounds(0, 0, 337, 19);
		container.add(lbl_title);
		
		lbl_customer_name = new JLabel("Müşteri:");
		lbl_customer_name.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 14));
		lbl_customer_name.setBounds(0, 20, 106, 31);
		container.add(lbl_customer_name);
		
		JLabel lblNewLabel = new JLabel("Sipariş Tarihi");
		lblNewLabel.setBounds(0, 51, 337, 47);
		container.add(lblNewLabel);
		
		try {
            fld_cart_date = new JFormattedTextField(new MaskFormatter("##/##/####"));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            fld_cart_date.setText(formatter.format(LocalDate.now()));
            fld_cart_date.setBounds(0, 98, 337, 47);
            container.add(fld_cart_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
		
		JLabel lblNewLabel_1 = new JLabel("Sipariş Notu");
		lblNewLabel_1.setBounds(0, 144, 337, 56);
		container.add(lblNewLabel_1);
		
		tarea_cart_note = new JTextArea();
		tarea_cart_note.setBounds(0, 199, 337, 104);
		container.add(tarea_cart_note);
		
		JButton btn_cart = new JButton("Sipariş Oluştur");
		btn_cart.setBounds(109, 313, 122, 37);
		container.add(btn_cart);
		
		if (customer.getId() == 0) {
			Helper.showMsg("Lütfen geçerli bir müşteri seçiniz");
			dispose();
		}
		
		ArrayList<Basket> baskets = this.basketController.findAll();
		if (baskets.size() == 0){
			Helper.showMsg("Lütfen sepete ürün ekleyiniz !");
			dispose();
		}
		
		this.lbl_customer_name.setText("Müşteri : " + this.customer.getName());
		
		btn_cart.addActionListener(e -> {
            if (Helper.isFieldEmpty(this.fld_cart_date)) {
				Helper.showMsg("fill");
			} else {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				for (Basket basket : baskets) {
					if (basket.getProduct().getStock() <= 0) continue;
					Cart cart = new Cart();
					cart.setCustomerId(this.customer.getId());
					cart.setProductId(basket.getProductId());
					cart.setPrice(basket.getProduct().getPrice());
					cart.setDate(LocalDate.parse(this.fld_cart_date.getText(), formatter));
					cart.setNote(this.tarea_cart_note.getText());
					this.cartController.save(cart);
					Product unStockProduct = basket.getProduct();
					unStockProduct.setStock(unStockProduct.getStock() - 1);
					this.productController.update(unStockProduct);					
			}
			this.basketController.clear();
			Helper.showMsg("done");
			dispose();
		}
		});      
		
	}
	
	private void createUIComponents() throws ParseException {
        fld_cart_date = new JFormattedTextField(new MaskFormatter("##/##/####"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		fld_cart_date.setText(formatter.format(LocalDate.now()));
	}
}
