

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class DisplayProduct extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{        
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			
			// Connect to database
			java.sql.Connection conn = null;
			Class.forName("org.gjt.mm.mysql.Driver");
			conn = DriverManager.getConnection ("jdbc:mysql://matt-smith-v4.ics.uci.edu/inf124db023", "inf124db023", "pristine");
			
			
			// Get the product with the matching id
			java.sql.Statement stmt = conn.createStatement();
			
			for (int i = 1; i <= 10; i++)
			{
				String id = String.valueOf(i), name = null, category = null, price = null;
				
				ResultSet rs = stmt.executeQuery("SELECT * FROM product_info WHERE id = " + id);
				
				while(rs.next())
				{
					id = rs.getString("id");
					name = rs.getString("name");
					category = rs.getString("category");
					price = rs.getString("price");
				}
				
//				String new_name = null;
//			    new_name = name.toLowerCase();
//			    new_name = new_name.charAt(1) + new_name.substring(3);
			    
				//out.println("<a href='/Keyboard/productinfo?id=" + id + "&name=" + new_name + "' onlick='saveCookie(" + id + ")'");
//			    out.println("<a onlick='saveCookie('10')' href='productinfo?id=" + id + "'>");
				out.println("<a href='historyproduct?id=" + id + "'>");
				out.println("<div class='cell'>");
				out.println("<div class='img'><img src='img/keyboard_" + id + ".jpg'></div>");
				out.println("<p class='product_name'>" + name + "</p>");
				out.println("<p class='product_category'>" + category + "</p>");
				out.println("<p class='product_price'>$" + price + "</p>");
				out.println("</div></a>");
			}
			out.println("<div>");
			request.getRequestDispatcher("readcookies").include(request, response);
			out.println("</div>");
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

