package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.ProductController;
import core.Helper;
import entity.Product;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;

public class ProductUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel container;
	private Product product;
	private ProductController productController;
	private JTextField fld_product_name;
	private JTextField fld_product_code;
	private JTextField fld_product_price;
	private JTextField fld_product_stock;

	public ProductUI(Product product) {
		this.product = product;
		this.productController = new ProductController();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 355, 407);
		container = new JPanel();
		container.setBackground(new Color(0, 128, 128));
		container.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(container);
		container.setLayout(null);
		
		JLabel lbl_title = new JLabel("Ürün Ekle");
		lbl_title.setForeground(new Color(255, 255, 255));
		lbl_title.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lbl_title.setBounds(0, 0, 337, 22);
		container.add(lbl_title);
		
		fld_product_name = new JTextField();
		fld_product_name.setBounds(0, 59, 337, 36);
		container.add(fld_product_name);
		fld_product_name.setColumns(10);
		
		JLabel lbl_product_code = new JLabel("Ürün Kodu");
		lbl_product_code.setForeground(new Color(255, 255, 255));
		lbl_product_code.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		lbl_product_code.setBounds(0, 101, 337, 29);
		container.add(lbl_product_code);
		
		fld_product_code = new JTextField();
		fld_product_code.setBounds(0, 128, 337, 36);
		container.add(fld_product_code);
		fld_product_code.setColumns(10);
		
		JLabel lbl_product_price = new JLabel("Ürün Fiyatı");
		lbl_product_price.setForeground(new Color(255, 255, 255));
		lbl_product_price.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		lbl_product_price.setBounds(0, 174, 337, 29);
		container.add(lbl_product_price);
		
		fld_product_price = new JTextField();
		fld_product_price.setBounds(0, 202, 337, 36);
		container.add(fld_product_price);
		fld_product_price.setColumns(10);
		
		JLabel lbl_product_stock = new JLabel("Ürün Stok Adedi");
		lbl_product_stock.setForeground(new Color(255, 255, 255));
		lbl_product_stock.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		lbl_product_stock.setBounds(0, 248, 337, 36);
		container.add(lbl_product_stock);
		
		fld_product_stock = new JTextField();
		fld_product_stock.setBounds(0, 283, 337, 36);
		container.add(fld_product_stock);
		fld_product_stock.setColumns(10);
		
		JLabel lbl_product_name = new JLabel("Ürün Adı");
		lbl_product_name.setForeground(new Color(255, 255, 255));
		lbl_product_name.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		lbl_product_name.setBackground(new Color(240, 240, 240));
		lbl_product_name.setBounds(0, 32, 337, 27);
		container.add(lbl_product_name);
		
		JButton btn_product = new JButton("Kaydet");
		btn_product.setForeground(new Color(255, 255, 255));
		btn_product.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		btn_product.setBounds(124, 329, 85, 21);
		container.add(btn_product);
		
		if(this.product.getId() == 0) {
			lbl_title.setText("Ürün Ekle");
		}else {
			lbl_title.setText("Ürün Düzenle");
			this.fld_product_name.setText(this.product.getName());
			this.fld_product_code.setText(this.product.getCode());
			this.fld_product_price.setText(String.valueOf(this.product.getPrice()));
			this.fld_product_stock.setText(String.valueOf(this.product.getStock()));
		}
		btn_product.addActionListener(e -> {
			JTextField[] checkList = {
					this.fld_product_name,
					this.fld_product_code,
					this.fld_product_price,
					this.fld_product_stock
			};
			
			if(Helper.isFieldListEmpty(checkList)) {
				Helper.showMsg("fiil");
			}else {
				this.product.setName(this.fld_product_name.getText());
				this.product.setCode(this.fld_product_code.getText());
				this.product.setPrice(Integer.parseInt(this.fld_product_price.getText()));
				this.product.setStock(Integer.parseInt(this.fld_product_stock.getText()));		
			
			boolean result;
			if(this.product.getId() == 0) {
				result = this.productController.save(this.product);
			}else {
				result = this.productController.update(this.product);
			}
			if (result) {
				Helper.showMsg("done");
				dispose();
			}else {
				Helper.showMsg("error");
			}
			}
		});
	}
}
