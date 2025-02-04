package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class db {
	
	/*Bu kod, Singleton Tasarım Deseni kullanılarak MySQL veritabanı bağlantısı oluşturur. 
	 * Singleton deseni, bir sınıfın yalnızca bir örneğinin (instance) oluşturulmasına izin verir. 
	 * Bu, özellikle veritabanı bağlantıları gibi, birden fazla bağlantının gereksiz yere açılmasını önlemek için kullanılır.*/
	private static db instance = null;
	private Connection connection = null;
	private final String DB_URL ="jdbc:mysql://localhost:3306/stoksatış";
	private final String DB_USERNAME = "root";
	private final String DB_PASSWORD = "";
	
	private db() {
		try {
			this.connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
		} catch (SQLException exception) {
			exception.printStackTrace();
		
		}
		
	}
	// Connection nesnesini döndüren bir getter metodu.
		// Bu, bağlantıya sınıfın içinden erişmek için kullanılır.
	private Connection getConnection() {
		return connection;
	}
	// Singleton deseni kullanılarak, tek bir veritabanı bağlantısı oluşturulur ve tüm uygulama genelinde kullanılır.
		// Dışarıdan çağrılan bu statik yöntem, her zaman aynı veritabanı bağlantısını döndürür.
	public static Connection getInstance() {
		try {
			if (instance == null || instance.getConnection().isClosed()) {
				instance = new db();
			}
			} catch (SQLException exception) {
				exception.printStackTrace();
		}
		// Bağlantıyı döndürür, tüm sınıflar bu bağlantıyı kullanabilir.
			return instance.getConnection();
	}
 }

