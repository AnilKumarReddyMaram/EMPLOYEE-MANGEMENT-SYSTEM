

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String name =request.getParameter("uname");
		String password = request.getParameter("psw");
		String email = request.getParameter("mail");
		String gender= request.getParameter("gend");
		long mobileno=Long.parseLong(request.getParameter("mobno"));
		String state= request.getParameter("state");
		String country= request.getParameter("country");
		String address = request.getParameter("addr");
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","kumar","kumar");
			PreparedStatement ps=con.prepareStatement("insert into ems values(?,?,?,?,?,?,?,?)");
			ps.setString(1, name);
			ps.setString(2, password);
			ps.setString(3, email);
			ps.setString(4, gender);
			ps.setLong(5, mobileno);
			ps.setString(6, state);
			ps.setString(7, country);
			ps.setString(8, address);
			int i=ps.executeUpdate();
			out.print(i+"New Record Successfully Inserted");
			con.close();	
		}
		catch (Exception ex)
		{
			out.print(ex);
		}
	}
}
