package Employee_JSP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class EmployeeCRUD {
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/studentdb?user=root&password=1234");
		return connection;
}
	
	public int saveEmployee(Employee employee) throws ClassNotFoundException, SQLException {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement("INSERT INTO EMPLOYEE VALUES(?,?,?,?,?,?)");
		preparedStatement.setInt(1, employee.getId());
		preparedStatement.setString(2, employee.getName());
		preparedStatement.setLong(3, employee.getPhone());
		preparedStatement.setString(4, employee.getAddress());
		preparedStatement.setString(5, employee.getEmail());
		preparedStatement.setString(6, employee.getPassword());
		
		int count=preparedStatement.executeUpdate();
		connection.close();
		return count;
		
		
		
				
				
	}
	public String getPassword(String email) throws SQLException, ClassNotFoundException
	{
		Connection connection=getConnection();
		PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM EMPLOYEE WHERE EMAIL=?");
		preparedStatement.setString(1, email);
		String password=null;
		ResultSet resultSet=preparedStatement.executeQuery();
		while(resultSet.next()) {
			password=resultSet.getString("password");
		}
		connection.close();
		return password;
		
	}
	public List<Employee>getAllEmployees() throws SQLException, ClassNotFoundException
	{
		Connection connection=getConnection();
		PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM EMPLOYEE");
		List<Employee> employees=new ArrayList<>();
		ResultSet resultSet=preparedStatement.executeQuery();
		while(resultSet.next()) {
		Employee employee=new Employee();
		employee.setId(resultSet.getInt("id"));
		employee.setName(resultSet.getString("name"));
		employee.setPhone(resultSet.getLong("phone"));
		employee.setAddress(resultSet.getString("address"));
		employee.setPassword(resultSet.getString("password"));
		 
		employees.add(employee);
		}
		connection.close();
		return employees;
	}
}
