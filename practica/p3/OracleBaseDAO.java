package p3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleBaseDAO {
	
	private static final String DB_DRIV = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_URL = "jdbc:oracle:thin:@//localhost:1521/xe";
	private static final String DB_USER = "OVchipkaart";
	private static final String DB_PASS = "manu741";
	
	//mag maar 1 connection gebruiken, daarom is die static
	private static Connection conn;
	
	protected Connection getConnection() throws SQLException{
		if(conn == null) {
			//create connection with the library database 
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
		}
		return conn;
	}
	
	public void closeConnection() throws SQLException {
		if(conn != null) {
			conn.close();
		}
	}
}
