import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Singleconnection {
	String db = "Smarthome_bd";
	String user = "root";
	String pwd = "12345689";
	String url = "jdbc:mysql://localhost:3306/" + db;
	private static Connection connection ;

	private Singleconnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, pwd);
			System.out.println("instance cree!!");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static Connection getConnection() {
		if (connection == null)
			new Singleconnection();
		return connection;
	}
}
