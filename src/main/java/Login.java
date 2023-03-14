

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String email =request.getParameter("mail");
		String password = request.getParameter("psw");
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","kumar","kumar");
		    PreparedStatement Ps = con.prepareStatement("select * from ems where email=? and password=?");
		    Ps.setString(1, email);
		    Ps.setString(2, password);
		    ResultSet rs=Ps.executeQuery();
		    if (rs.next())
		    {
		    	response.sendRedirect("emphome.html");
		    }
		    else
		    {
		    	out.print("Please Enter valid email and password");
		    }
		    con.close();
		}
		catch (Exception ex)
		{
			out.print(ex);
		}
	}

}
