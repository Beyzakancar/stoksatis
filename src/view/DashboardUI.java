package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import business.BasketController;
import business.CartController;
import business.CustomerController;
import business.ProductController;
import core.Helper;
import core.Item;
import dao.Userdao;
import entity.Basket;
import entity.Cart;
import entity.Customer;
import entity.Product;
import entity.User;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.AbstractButton;
import javax.swing.Box;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import java.awt.Component;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.JCheckBox;

public class DashboardUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel container;
	private User user ;
	private JTable tbl_customer;
	private JTextField fld_f_customer_name;
	private CustomerController customerController;
	private ProductController productController;
	private CartController cartController;
	private DefaultTableModel tmdl_customer = new DefaultTableModel();
	private DefaultTableModel tmdl_product = new DefaultTableModel();
	private DefaultTableModel tmdl_basket = new DefaultTableModel();
	private DefaultTableModel tmdl_cart = new DefaultTableModel();
	private JPopupMenu popup_customer = new JPopupMenu();
	private JPopupMenu popup_product = new JPopupMenu();
	private JButton btn_customer_new;
	private JButton btn_product_new1;
	private JButton btn_customer_filter_reset;
	private JButton btn_customer_filter;
	private JButton btn_product_filter;
	private JButton btn_product_filter_reset;
	private JButton btn_basket_new;
	private JButton btn_basket_reset;
	private JComboBox<Customer.TYPE>cmb_f_customer_type;
	private JComboBox<Item> cmb_basket_customer;
	private JTable tbl_product;
	private JTextField fld_f_product_code;
	private JTextField fld_f_product_name;
	private JComboBox<Item> cmb_f_product_stock;
	private BasketController basketController;
	private JTable tbl_basket;
	private JLabel lbl_basket_count;
	private JLabel lbl_basket_price;
	private JTable tbl_cart;
	
 
	public DashboardUI(User user) {				
		this.user = user;
		this.customerController = new CustomerController();
		this.productController = new ProductController();
		this.basketController = new BasketController();
		this.cartController = new CartController();
		
			if (user == null) {
				Helper.showMsg("error");
				dispose();
				return;
			}	
			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Müşteri Yönetim Sistemi");
		setSize(800,500);
		container = new JPanel();
		container.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(container);
		setVisible(true);
		container.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 794, 38);
		container.add(panel);
		panel.setLayout(null);	
		panel.setBackground(SystemColor.activeCaption);
	
		
		JLabel lbl_welcome = new JLabel("Hoşgeldiniz");
		lbl_welcome.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		lbl_welcome.setForeground(SystemColor.text);
		lbl_welcome.setBounds(20, 10, 300, 30);
		panel.add(lbl_welcome);	
		lbl_welcome.setText("Hoşgeldin :" + this.user.getName());
		
		JButton btn_logout = new JButton("Çıkış Yap");		
		btn_logout.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		btn_logout.setBackground(new Color(0, 0, 128));
		btn_logout.setForeground(Color.WHITE);
		btn_logout.setFocusPainted(false);
        btn_logout.setBorderPainted(false);
        btn_logout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btn_logout.setBounds(670, 10, 102, 30);
		panel.add(btn_logout);
		
		
		JPanel pnl_customer = new JPanel();
        pnl_customer.setLayout(null);
        JScrollPane scrl_customer = new JScrollPane();
        scrl_customer.setBounds(0, 56, 788, 345);
        pnl_customer.add(scrl_customer);
        
        tbl_customer = new JTable();
        scrl_customer.setViewportView(tbl_customer);
		
		JPanel pnl_customer_filter = new JPanel();
		pnl_customer_filter.setBounds(0, 0, 788, 56);
		pnl_customer.add(pnl_customer_filter);
		pnl_customer_filter.setLayout(null);
		pnl_customer_filter.setBackground(new Color(230, 230, 230));
		
		JLabel lbl_f_customer_name = new JLabel("Müşteri Adı");
		lbl_f_customer_name.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 13));
		lbl_f_customer_name.setBounds(0, 0, 103, 27);
		pnl_customer_filter.add(lbl_f_customer_name);
		
		fld_f_customer_name = new JTextField();
		fld_f_customer_name.setFont(new Font("Arial", Font.PLAIN, 12));
		fld_f_customer_name.setBounds(0, 27, 184, 29);
		fld_f_customer_name.setBackground(new Color(250, 250, 250));
		pnl_customer_filter.add(fld_f_customer_name);
		fld_f_customer_name.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(200, 200, 200)));
		fld_f_customer_name.setColumns(10);
		
		JLabel lbl_f_customer_type = new JLabel("Müşteri Tipi");
		lbl_f_customer_type.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 13));
		lbl_f_customer_type.setBounds(194, 0, 103, 27);
		pnl_customer_filter.add(lbl_f_customer_type);
		
		cmb_f_customer_type = new JComboBox<>();
		cmb_f_customer_type.setFont(new Font("Arial", Font.PLAIN, 12));
		cmb_f_customer_type.setBounds(194, 27, 173, 29);
		pnl_customer_filter.add(cmb_f_customer_type);
		
		btn_customer_filter = new JButton("Arama Yap");		
		btn_customer_filter.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 11));
		btn_customer_filter.setBackground(new Color(0, 139, 139)); // Blue
        btn_customer_filter.setForeground(new Color(0, 0, 0));
        btn_customer_filter.setFocusPainted(true);
        btn_customer_filter.setBorderPainted(true);
        btn_customer_filter.setBounds(406, 28, 104, 27);
        btn_customer_filter.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		pnl_customer_filter.add(btn_customer_filter);
		
		btn_customer_filter_reset = new JButton("Temizle");
		btn_customer_filter_reset.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 11));
		btn_customer_filter_reset.setBackground(new Color(30, 144, 255)); // Green
        btn_customer_filter_reset.setForeground(new Color(0, 0, 0));
        btn_customer_filter_reset.setFocusPainted(true);
        btn_customer_filter_reset.setBorderPainted(true);
		btn_customer_filter_reset.setBounds(530, 27, 104, 28);
		btn_customer_filter_reset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		pnl_customer_filter.add(btn_customer_filter_reset);
		
		
		btn_customer_new = new JButton("Yeni Ekle");
		btn_customer_new.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 11));
		btn_customer_new.setBackground(new Color(240, 255, 255)); // Yellow
        btn_customer_new.setForeground(new Color(0, 0, 0));
        btn_customer_new.setFocusPainted(true);
        btn_customer_new.setBorderPainted(true);
        btn_customer_new.setBounds(659, 28, 104, 27);
        btn_customer_new.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
       // btn_customer_new.addActionListener(cmb_customer_type);
		pnl_customer_filter.add(btn_customer_new);
		
		
		JTabbedPane tab_menu = new JTabbedPane(JTabbedPane.TOP);
        tab_menu.setBounds(0, 37, 782, 427);
        tab_menu.addTab("Müşteriler", pnl_customer);
        container.add(tab_menu);
		
		
		
        btn_logout.addActionListener(e -> {
            dispose();
            new loginUI();
        });	
        
      // CUSTOMER TAB
        loadCustomerTable(null);  
        loadCustomerPopupMenu();
        loadCustomerButtonEvent();
        this.cmb_f_customer_type.setModel(new DefaultComboBoxModel<>(Customer.TYPE.values()));
        this.cmb_f_customer_type.setSelectedItem(null);
        
        JPanel pnl_product = new JPanel();
        pnl_product.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        tab_menu.addTab("Ürünler", null, pnl_product, null);
        pnl_product.setLayout(null);
        
        JScrollPane scrl_product = new JScrollPane();
        scrl_product.setBounds(0, 54, 777, 346);
        pnl_product.add(scrl_product);
        
        tbl_product = new JTable();
        scrl_product.setViewportView(tbl_product);
        
        JPanel pnl_filter_product = new JPanel();
        pnl_filter_product.setBounds(0, 0, 777, 54);
        pnl_product.add(pnl_filter_product);
        pnl_filter_product.setLayout(null);
        
        JLabel lbl_f_product_name = new JLabel("Ürün Adı");
        lbl_f_product_name.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 13));
        lbl_f_product_name.setBounds(0, 0, 104, 20);
        pnl_filter_product.add(lbl_f_product_name);
        
        JLabel lbl_f_product_code = new JLabel("Ürün Kodu");
        lbl_f_product_code.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 13));
        lbl_f_product_code.setBounds(160, 0, 97, 20);
        pnl_filter_product.add(lbl_f_product_code);
        
        JLabel lbl_f_product_stock = new JLabel("Stok Durumu");
        lbl_f_product_stock.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 13));
        lbl_f_product_stock.setBounds(321, 0, 110, 20);
        pnl_filter_product.add(lbl_f_product_stock);
        
        cmb_f_product_stock = new JComboBox<>();
        cmb_f_product_stock.setBounds(321, 24, 136, 30);
        pnl_filter_product.add(cmb_f_product_stock);
        
        btn_product_filter_reset = new JButton("Temizle");
        btn_product_filter_reset.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 11));
        btn_product_filter_reset.setForeground(new Color(255, 255, 255));
        btn_product_filter_reset.setBackground(new Color(0, 206, 209));
        btn_product_filter_reset.setFocusPainted(true);
        btn_product_filter_reset.setBorderPainted(true);
        btn_product_filter_reset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_product_filter_reset.setBounds(573, 24, 92, 30);
        pnl_filter_product.add(btn_product_filter_reset);
        
        btn_product_filter = new JButton("Arama Yap");
        btn_product_filter.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 11));
        btn_product_filter.setForeground(new Color(255, 255, 255));
        btn_product_filter.setBackground(new Color(0, 0, 128));
        btn_product_filter.setFocusPainted(true);
        btn_product_filter.setBorderPainted(true);
        btn_product_filter.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_product_filter.setBounds(467, 24, 97, 30);
        pnl_filter_product.add(btn_product_filter);
        
        btn_product_new1 = new JButton("Yeni Ekle");
        btn_product_new1.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 11));
        btn_product_new1.setForeground(new Color(255, 255, 255));
        btn_product_new1.setBackground(new Color(0, 128, 128));
        btn_product_new1.setFocusPainted(true);
        btn_product_new1.setBorderPainted(true);
        btn_product_new1.setBounds(675, 24, 92, 30);
        btn_product_new1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnl_filter_product.add(btn_product_new1);
        
        fld_f_product_code = new JTextField();
        fld_f_product_code.setBounds(160, 24, 151, 30);
        pnl_filter_product.add(fld_f_product_code);
        fld_f_product_code.setColumns(10);
        
        fld_f_product_name = new JTextField();
        fld_f_product_name.setBounds(0, 24, 150, 30);
        pnl_filter_product.add(fld_f_product_name);
        fld_f_product_name.setColumns(10);
        
        
     // PRODUCT TAB
        loadProductTable(null);    
        loadProductPopupMenu();
        loadProductButtonEvent();
        this.cmb_f_product_stock.addItem(new Item(1, "Stokta Var"));
        this.cmb_f_product_stock.addItem(new Item(2, "Stokta Yok"));
        this.cmb_f_product_stock.setSelectedItem(null);
        
        JPanel pnl_basket = new JPanel();
        tab_menu.addTab("Sipariş Oluştur", null, pnl_basket, null);
        pnl_basket.setLayout(null);
        
        JPanel pnl_basket_top = new JPanel();
        pnl_basket_top.setBounds(0, 0, 777, 72);
        pnl_basket.add(pnl_basket_top);
        pnl_basket_top.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Müşteri Seçiniz\r\n");
        lblNewLabel.setBounds(0, 0, 170, 28);
        pnl_basket_top.add(lblNewLabel);
        
        cmb_basket_customer = new JComboBox();
        cmb_basket_customer.setBounds(0, 38, 170, 34);
        pnl_basket_top.add(cmb_basket_customer);
        
        JLabel lblNewLabel_1 = new JLabel("Toplam Tutar");
        lblNewLabel_1.setBounds(180, 0, 150, 28);
        pnl_basket_top.add(lblNewLabel_1);
        
        lbl_basket_price = new JLabel("0 tl");
        lbl_basket_price.setBounds(180, 38, 150, 34);
        pnl_basket_top.add(lbl_basket_price);
        
        JLabel lblNewLabel_3 = new JLabel("Ürün Sayısı");
        lblNewLabel_3.setBounds(340, 0, 135, 28);
        pnl_basket_top.add(lblNewLabel_3);
        
        btn_basket_reset = new JButton("Temizle");
        btn_basket_reset.setBounds(485, 44, 131, 28);
        pnl_basket_top.add(btn_basket_reset);
        
        btn_basket_new = new JButton("Sipariş Oluştur");
        btn_basket_new.setBounds(626, 44, 127, 28);
        pnl_basket_top.add(btn_basket_new);
        
        lbl_basket_count = new JLabel("0 Adet");
        lbl_basket_count.setBounds(340, 38, 135, 34);
        pnl_basket_top.add(lbl_basket_count);
        
        JScrollPane scrl_basket = new JScrollPane();
        scrl_basket.setBounds(0, 72, 777, 318);
        pnl_basket.add(scrl_basket);
        
        tbl_basket = new JTable();
        scrl_basket.setViewportView(tbl_basket);
        
        // BASKET TABLE 
        loadBasketTable();
        loadBasketButtonEvent();
        loadBasketCustomerCombo();
        
        JPanel pnl_cart = new JPanel();
        tab_menu.addTab("Siparişler", null, pnl_cart, null);
        pnl_cart.setLayout(null);
        
        JScrollPane scrl_cart = new JScrollPane();
        scrl_cart.setBounds(0, 0, 777, 390);
        pnl_cart.add(scrl_cart);
        
        tbl_cart = new JTable();
        scrl_cart.setViewportView(tbl_cart);
        
        // CART TAB
        loadCartTable();
   
    }
	private void loadCartTable() {
		
		Object[] columnCart = {"ID" , "Müşteri Adı" , "Ürün Adı" , "Fiyat" , "Sipariş Tarihi" , "Not"};
		ArrayList<Cart> carts = this.cartController.findAll();
		
		DefaultTableModel clearModel = (DefaultTableModel) this.tbl_cart.getModel();
		clearModel.setRowCount(0);
		
		
		this.tmdl_cart.setColumnIdentifiers(columnCart);
		for (Cart cart : carts) {
			Object[] rowObject = {
					cart.getId(),
					cart.getCustomer().getName(),
					cart.getProduct().getName(),
					cart.getPrice(),
					cart.getDate(),
					cart.getNote()
					
			};
			this.tmdl_cart.addRow(rowObject);
			
					
		}
		
		this.tbl_cart.setModel(tmdl_cart); //veritabanından veya başka bir veri kaynağından gelen verileri tabloya yerleştirmek için kullanılır.
		this.tbl_cart.getTableHeader().setReorderingAllowed(false);//kolon başlıklarının sürüklenip değiştirilmesi engellenir.
		this.tbl_cart.getColumnModel().getColumn(0).setMaxWidth(50);
		this.tbl_cart.setEnabled(false);//Tabloyu gösterirken, artık tabloyu seçemez, düzenleyemez veya herhangi bir işlem yapılamaz.
	  }
	

	private void loadBasketCustomerCombo() {
		ArrayList<Customer> customers = this.customerController.findAll();
		this.cmb_basket_customer.removeAllItems();
		for (Customer customer : customers) {
			int comboKey = customer.getId();
			String comboValue = customer.getName();
			this.cmb_basket_customer.addItem(new Item(comboKey, comboValue));
		}
		this.cmb_basket_customer.setSelectedItem(null);
	}
	
	private void loadBasketButtonEvent() {
		this.btn_basket_reset.addActionListener(e -> {
			if (this.basketController.clear()) {
				Helper.showMsg("done");
				loadBasketTable();
			}else {
				Helper.showMsg("error");
			}
		});
		
		this.btn_basket_new.addActionListener(e -> {
        	Item selectedCustomer = (Item) this.cmb_basket_customer.getSelectedItem();
			if (selectedCustomer == null) {
			    Helper.showMsg("Lütfen bir müşteri seçiniz !");
			} else {
			   Customer customer = this.customerController.getById(selectedCustomer.getKey());
			   ArrayList<Basket> baskets = this.basketController.findAll();
			   if (customer.getId() == 0){
				   Helper.showMsg("Böyle  bir müşteri bulunamadı !");
	           } else if (baskets.size() == 0) {
				   Helper.showMsg("Lütfen sepete ürün ekleyiniz !");
			   }else {
				   CartUI cartUI = new CartUI(customer);
				   cartUI.setVisible(true);
				   cartUI.addWindowListener(new WindowAdapter() {
					  @Override
					  public void windowClosed(WindowEvent e) {
						  loadBasketTable();
						  loadProductTable(null);
						  loadCartTable();
					  }
				   });
			   }
			   
			}
        });
	}
	
	private void loadBasketTable() {
		Object[] columnBasket = {"ID" , "Ürün Adı" , "Ürün Kodu" , "Fiyat" , "Stok"};
		ArrayList<Basket> baskets = basketController.findAll();
		
		DefaultTableModel clearModel = (DefaultTableModel) this.tbl_basket.getModel();
		clearModel.setRowCount(0);
		
		
		this.tmdl_basket.setColumnIdentifiers(columnBasket);
		int totalPrice = 0;
		for (Basket basket : baskets) {
			Object[] rowObject = {
					basket.getId(),
					basket.getProduct().getName(),
					basket.getProduct().getCode(),
					basket.getProduct().getPrice(),
					basket.getProduct().getStock(),
					
			};
			this.tmdl_basket.addRow(rowObject);
			
			totalPrice += basket.getProduct().getPrice();
					
		}
		
		this.lbl_basket_price.setText(String.valueOf(totalPrice) + " TL");
		this.lbl_basket_count.setText(String.valueOf(baskets.size()) + " Adet");
		
		this.tbl_basket.setModel(tmdl_basket); //veritabanından veya başka bir veri kaynağından gelen verileri tabloya yerleştirmek için kullanılır.
		this.tbl_basket.getTableHeader().setReorderingAllowed(false);//kolon başlıklarının sürüklenip değiştirilmesi engellenir.
		this.tbl_basket.getColumnModel().getColumn(0).setMaxWidth(50);
		this.tbl_basket.setEnabled(false);//Tabloyu gösterirken, artık tabloyu seçemez, düzenleyemez veya herhangi bir işlem yapılamaz.
	  }
			
	 
	private void loadProductButtonEvent() {
		this.btn_product_new1.addActionListener(e -> {
		    ProductUI productUI = new ProductUI(new Product());
		    productUI.setVisible(true);
		    productUI.addWindowListener(new WindowAdapter() {
		    	@Override
		    	public void windowClosed(WindowEvent e) {
		    		loadProductTable(null);
		    	}
		    });	
		});
		
		this.btn_product_filter.addActionListener(e -> {
			ArrayList<Product> filteredProducts = this.productController.filter(
					this.fld_f_product_name.getText(),
					this.fld_f_product_code.getText(),
					(Item) this.cmb_f_product_stock.getSelectedItem()
             );
			loadProductTable(filteredProducts);
		});
		
		this.btn_product_filter_reset.addActionListener(e -> {
			this.fld_f_product_code.setText(null);
			this.fld_f_product_name.setText(null);
			this.cmb_f_product_stock.setSelectedItem(null);
			loadProductTable(null);
		});
		
		
	}		
	private void loadProductPopupMenu() {
		this.tbl_product.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int selectedRow = tbl_product.rowAtPoint(e.getPoint());
				tbl_product.setRowSelectionInterval(selectedRow, selectedRow);
			}
		});	
		
		this.popup_product.add("Sepete Ekle").addActionListener(e -> {
			int selectId = Integer.parseInt(this.tbl_product.getValueAt(this.tbl_product.getSelectedRow(), 0).toString());
			Product basketProduct = this.productController.getById(selectId);
			if (basketProduct.getStock() <= 0) {
				Helper.showMsg("Bu ürün stokta yoktur !");
			} else {
				Basket basket = new Basket(basketProduct.getId());
				if (this.basketController.save(basket)) {
					Helper.showMsg("done");
					loadBasketTable();					
				} else {
					Helper.showMsg("error");
				}
			}
			
		});
		this.popup_product.add("Güncelle").addActionListener(e ->{
		    int selectedId = Integer.parseInt(this.tbl_product.getValueAt(this.tbl_product.getSelectedRow(), 0).toString());	
			ProductUI productUI = new ProductUI(this.productController.getById(selectedId));
			productUI.setVisible(true);
     		productUI.addWindowListener(new WindowAdapter() {				
				@Override
				public void windowClosed(WindowEvent e) {
                   loadProductTable(null);
                   loadBasketTable();                 
				}
			});
		});		
		
		this.popup_product.add("Sil").addActionListener(e -> {
			int selectedId = Integer.parseInt(this.tbl_product.getValueAt(this.tbl_product.getSelectedRow(), 0).toString());	
		    if (Helper.confirm("sure")) {
		    	if (this.productController.delete(selectedId)) {
		    		Helper.showMsg("done");
		    		loadProductTable(null);
		    		loadBasketTable();
	
		    	}else {
		    		Helper.showMsg("error");
		    	}
		    }
		});
		
		this.tbl_product.setComponentPopupMenu(this.popup_product);
	}	
 	private void loadProductTable(ArrayList<Product> products) {
		Object[] columnProduct = {"ID" , "Ürün Adı" , "Ürün Kodu" , "Fiyat" , "Stok"};
	if (products == null) {
		products = this.productController.findAll();
	}
	
	DefaultTableModel clearModel = (DefaultTableModel) this.tbl_product.getModel();
	clearModel.setRowCount(0);
	
	
	this.tmdl_product.setColumnIdentifiers(columnProduct);
	for (Product product : products) {
		Object[] rowObject = {
				product.getId(),
				product.getName(),
				product.getCode(),
				product.getPrice(),
				product.getStock(),
				
		};
		this.tmdl_product.addRow(rowObject);
	}
	
	this.tbl_product.setModel(tmdl_product); //veritabanından veya başka bir veri kaynağından gelen verileri tabloya yerleştirmek için kullanılır.
	this.tbl_product.getTableHeader().setReorderingAllowed(false);//kolon başlıklarının sürüklenip değiştirilmesi engellenir.
	this.tbl_product.getColumnModel().getColumn(0).setMaxWidth(50);
	this.tbl_product.setEnabled(false);//Tabloyu gösterirken, artık tabloyu seçemez, düzenleyemez veya herhangi bir işlem yapılamaz.
  }	
	private void loadCustomerButtonEvent() {
		this.btn_customer_new.addActionListener(e -> {	
			CustomerUI customerUI = new CustomerUI(new Customer());
			customerUI.setVisible(true);
			customerUI.addWindowListener(new WindowAdapter()
					{
				public void windowClosed(WindowEvent e) {
					loadCustomerTable(null);
					loadBasketCustomerCombo();
				}
			});
			
		});
		this.btn_customer_filter.addActionListener( e -> {
			ArrayList<Customer> filteredCustomers = this.customerController.filter(
					this.fld_f_customer_name.getText(),
					(Customer.TYPE) this.cmb_f_customer_type.getSelectedItem()	
					);
			loadCustomerTable(filteredCustomers);
		});
		this.btn_customer_filter_reset.addActionListener(e -> {
			loadCustomerTable(null);
			this.fld_f_customer_name.setText(null);
			this.cmb_f_customer_type.setSelectedItem(null);
		});
	}	
	private void loadCustomerPopupMenu() {
		this.tbl_customer.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int selectedRow = tbl_customer.rowAtPoint(e.getPoint());
				tbl_customer.setRowSelectionInterval(selectedRow, selectedRow);
			}
		});	
	
		this.popup_customer.add("Güncelle").addActionListener(e -> {
			int selectId = Integer.parseInt(tbl_customer.getValueAt(tbl_customer.getSelectedRow(), 0).toString());
            Customer editedCustomer = this.customerController.getById(selectId);
            CustomerUI customerUI = new CustomerUI(editedCustomer);
            customerUI.setVisible(true);
            customerUI.addWindowListener(new WindowAdapter() {
            	public void windowClosed(WindowEvent e) {
            	loadCustomerTable(null);	
            	loadBasketCustomerCombo();
            	}
            	
            });
		});
		this.popup_customer.add("Sil").addActionListener(e -> {
			int selectId = Integer.parseInt(tbl_customer.getValueAt(tbl_customer.getSelectedRow(), 0).toString());
			if(Helper.confirm("sure")) {
				
			if (this.customerController.delete(selectId)) {
				Helper.showMsg("done");
				loadCustomerTable(null);
				loadBasketCustomerCombo();
			}else {
				Helper.showMsg("error");
			}
			}
		});
		
		this.tbl_customer.setComponentPopupMenu(popup_customer);
	}
	private void loadCustomerTable(ArrayList<Customer> customers) {
		Object[] columnCustomer = {"ID" , "Müşteri Adı" , "Tipi" , "Telefon" , "Eposta" , "Adres"};
	if (customers == null) {
		customers = this.customerController.findAll();
	}
	
	DefaultTableModel clearModel = (DefaultTableModel) tbl_customer.getModel();
	clearModel.setRowCount(0);
	
	
	this.tmdl_customer.setColumnIdentifiers(columnCustomer);
	for (Customer customer : customers) {
		Object[] rowObject = {
				customer.getId(),
				customer.getName(),
				customer.getType(),
				customer.getPhone(),
				customer.getMail(),
				customer.getAddress()
		};
		this.tmdl_customer.addRow(rowObject);
	}
	
	this.tbl_customer.setModel(tmdl_customer); //veritabanından veya başka bir veri kaynağından gelen verileri tabloya yerleştirmek için kullanılır.
	this.tbl_customer.getTableHeader().setReorderingAllowed(false);//kolon başlıklarının sürüklenip değiştirilmesi engellenir.
	this.tbl_customer.getColumnModel().getColumn(0).setMaxWidth(50);
	this.tbl_customer.setEnabled(false);//Tabloyu gösterirken, artık tabloyu seçemez, düzenleyemez veya herhangi bir işlem yapılamaz.
  }
}	