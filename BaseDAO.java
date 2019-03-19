package jdbcboilerplate.JDBCBoilerplate;

import java.sql.Connection;
import java.sql.SQLException;

public interface BaseDAO {

	public default Connection getConnection() throws SQLException {
		Connection connection;
		connection = JDBCConnectionFactory.getInstance().getConnection();
		return connection;
	}
	
}
