package Employee_JSP;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmployeeLoginController extends HttpServlet {
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String email=req.getParameter("email");
	String password=req.getParameter("password");
	
	
	EmployeeCRUD crud=new EmployeeCRUD();
	try {
		String dbPassword=crud.getPassword("email");
		if(password.equals(dbPassword)){
			List<Employee> employees=crud.getAllEmployees();
			req.setAttribute("list", employees);
			RequestDispatcher dispatcher=req.getRequestDispatcher("success.jsp");
			dispatcher.forward(req, resp);
		}else {
			    req.setAttribute("message","Invalid redentials");
				RequestDispatcher dispatcher=req.getRequestDispatcher("login.jsp");
				dispatcher.forward(req, resp);
			}
		
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}