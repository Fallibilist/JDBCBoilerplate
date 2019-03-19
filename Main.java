package jdbcboilerplate.JDBCBoilerplate;

public class Main {

	public static void main(String[] args) {
		
		EmployeeDAO employeeDAO = new EmployeeDAO();
		
		Employee employee = new Employee();
		
//		employee.setId(6);
		employee.setFirstName("Test12");
		employee.setLastName("Test22");
		employee.setAge(1011);
		
		employeeDAO.createEmployee(employee);
//		employeeDAO.updateEmployee(employee);
//		employeeDAO.readEmployee();
//		employeeDAO.deleteEmployee(5);
//		employeeDAO.readEmployee();
		
	}
	
}
