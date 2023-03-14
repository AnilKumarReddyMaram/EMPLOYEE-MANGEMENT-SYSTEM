

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String name=request.getParameter("uname");
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","kumar","kumar");
			PreparedStatement Ps=con.prepareStatement("select * from ems where name=?");
			Ps.setString(1, name);
			ResultSet rs=Ps.executeQuery();
			ResultSetMetaData rsmd=rs.getMetaData();
			out.print("<table border='1'>");
			int n=rsmd.getColumnCount();
			for (int i=1; i<=n; i++)
				out.print("<td><font color=blue size=3>"+"<br>"+rsmd.getColumnName(i));
			out.print("<tr>");
			while (rs.next())
			{
				for (int i=1; i<=n; i++)
					out.print("<td><br>"+rs.getString(i));
				out.print("<tr>");
			}
			out.print("</table></body></html>");
			con.close();
		}	
		catch (Exception ex)
		{
			out.print(ex);
		}
	}

}
