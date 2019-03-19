package jdbcboilerplate.JDBCBoilerplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO implements BaseDAO {

	public static final String CREATE = "INSERT INTO public.\"Employee\" (first_name, last_name, age) VALUES(?,?,?)";
	public static final String READ = "SELECT * FROM public.\"Employee\"";
	public static final String UPDATE = "UPDATE public.\"Employee\" SET first_name = ?, last_name = ?, age = ? WHERE id = ?";
	public static final String DELETE = "DELETE FROM public.\"Employee\" WHERE id = ?";

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

	public List<Employee> readEmployee() {
		List<Employee> employees = new ArrayList<>();
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(READ);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while(resultSet.next()) {
				Employee employee = new Employee();

				employee.setId(resultSet.getInt(1));
				employee.setFirstName(resultSet.getString(2));
				employee.setLastName(resultSet.getString(3));
				employee.setAge(resultSet.getInt(4));

//				OR
//				employee.setFirstName(resultSet.getString("first_name"));
//				employee.setLastName(resultSet.getString("last_name"));
//				employee.setAge(resultSet.getInt("age"));
				
				employees.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return employees;
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
