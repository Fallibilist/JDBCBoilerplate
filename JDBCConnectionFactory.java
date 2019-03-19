package jdbcboilerplate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnectionFactory {
	
	String DRIVERCLASSNAME = "org.postgresql.Driver";
	String CONNECTIONURL = "jdbc:postgresql://localhost:8080/employeedb";
	String USER = "";
	String PASSWORD = "";

	private static JDBCConnectionFactory connectionFactory = null;

	private JDBCConnectionFactory() {
		try {
			Class.forName(DRIVERCLASSNAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static JDBCConnectionFactory getInstance() {
		if (connectionFactory == null) {
			connectionFactory = new JDBCConnectionFactory();
		}
		return connectionFactory;
	}

	public Connection getConnection() throws SQLException {
		Connection connection = null;
		connection = DriverManager.getConnection(CONNECTIONURL, USER, PASSWORD);
		return connection;
	}
}

