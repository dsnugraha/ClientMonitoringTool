package dsn.cmon.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class DBUtil {
	static Logger log = Logger.getLogger(DBUtil.class);
	private static Connection conn;
	private static String url;
	private static String usr;
	private static String pwd;
	
	public static Connection getConnection() {	
		if( conn != null )
            return conn;

		ConfigProperty configProperty = new ConfigProperty();

		try {
			//url = "jdbc:mysql://localhost:3306/bman";
			url = configProperty.getConfigProperty("URL_DB");
    		Class.forName("com.mysql.jdbc.Driver");

			try {
				usr = configProperty.getConfigProperty("USERNAME_DB");
				pwd = configProperty.getConfigProperty("PASSWORD_DB");
				conn = DriverManager.getConnection(url, usr, pwd);

			} catch (SQLException se) {
				log.error("SQLState: " + se.getSQLState());
				log.error("ErrorCode: " + se.getErrorCode());
				log.error("Message: " + se.getMessage());
				se.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			log.error("Message: " + e.getMessage());
			e.printStackTrace();
		}
		
		return conn;
	}
	
}
