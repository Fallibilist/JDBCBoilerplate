package jdbcboilerplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO implements BaseDAO {

	public static final String CREATE = "INSERT INTO employee(first_name, last_name, age) VALUES(?,?,?)";
	public static final String READ = "SELECT * FROM employee";
	public static final String UPDATE = "UPDATE employee SET first_name = ?, last_name = ?, age = ? WHERE id = ?";
	public static final String DELETE = "DELETE employee WHERE id = ?";

	public void createEmployee(Employee employee) {
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(CREATE)) {

			preparedStatement.setString(1, employee.getFirstName());
			preparedStatement.setString(2, employee.getLastName());
			preparedStatement.setInt(3, employee.getAge());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void readEmployee() {
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(READ);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			List<Employee> employees = new ArrayList<>();
			while(resultSet.next()) {
				Employee employee = new Employee();
				
				employee.setFirstName(resultSet.getString(1));
				employee.setLastName(resultSet.getString(2));
				employee.setAge(resultSet.getInt(3));

//				OR
//				employee.setFirstName(resultSet.getString("first_name"));
//				employee.setLastName(resultSet.getString("last_name"));
//				employee.setAge(resultSet.getInt("age"));
				
				employees.add(employee);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateEmployee(Employee employee) {
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {

			preparedStatement.setString(1, employee.getFirstName());
			preparedStatement.setString(2, employee.getLastName());
			preparedStatement.setInt(3, employee.getAge());
			preparedStatement.setInt(4, employee.getId());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteEmployee(int id) {
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {

			preparedStatement.setInt(1, id);

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
