

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
 * Servlet implementation class Update
 */
@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update() {
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
		long mobileno=Long.parseLong(request.getParameter("mobno"));
		String address = request.getParameter("addr");
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","kumar","kumar");
		    PreparedStatement Ps = con.prepareStatement("update ems set password=?,email=?,mobileno=?,address=? where name=?");
		    Ps.setString(1, password);
		    Ps.setString(2, email);
		    Ps.setLong(3, mobileno);
		    Ps.setString(4, address);
		    Ps.setString(5, name);
		    int i = Ps.executeUpdate();
		    out.print(i+"one Record Successfully Updated");
		    con.close();
		}
		catch (Exception ex)
		{
			out.print(ex);
		}
	}
}
