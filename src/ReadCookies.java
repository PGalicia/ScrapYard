

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class ReadCookies extends HttpServlet {
	
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
		
			
			// Get an array of Cookies associated with this domain
			Cookie cookies[] = request.getCookies();
			
			out.println("<br>");
			out.println("<h1>History</h1>");
			
			int historyCount = 0;
			
//			if(cookies.length < 6)
//			{
				for (int i = cookies.length - 1; i >= 0 && historyCount < 5; i--)
				{
					if(cookies[i].getName().contains("history"))
					{
						// Get product id (adn initialize the rest)
						String id = cookies[i].getValue(), name = "", category = "", price = "";
						
						ResultSet rs = stmt.executeQuery("SELECT * FROM product_info WHERE id = " + id);
						
						while(rs.next())
						{
							id = rs.getString("id");
							name = rs.getString("name");
							category = rs.getString("category");
							price = rs.getString("price");
						}
						
	//					String new_name = "";
	//				    new_name = name.toLowerCase();
	//				    new_name = new_name.charAt(1) + new_name.substring(3);
					    
						out.println("<a href='historyproduct?id=" + id + "'>");
						out.println("<div class=''>");
						out.println("<div class='img'><img src='img/keyboard_" + id + ".jpg'></div>");
						out.println("<p class='product_name'>" + name + "</p>");
						out.println("<p class='product_category'>" + category + "</p>");
						out.println("<p class='product_price'>$" + price + "</p>");
						out.println("</div></a>");
						
						historyCount++;
					}
					
				}
//			}
//			else {
//			for (int i = cookies.length - 1; i >= cookies.length - 5; i--)
//			//for (int i = 0; i < cookies.length; i++)
//			{
//				// Get product id (adn initialize the rest)
//				String id = cookies[i].getValue(), name = "", category = "", price = "";
//				
//				ResultSet rs = stmt.executeQuery("SELECT * FROM product_info WHERE id = " + id);
//				
//				while(rs.next())
//				{
//					id = rs.getString("id");
//					name = rs.getString("name");
//					category = rs.getString("category");
//					price = rs.getString("price");
//				}
//				
////				String new_name = "";
////			    new_name = name.toLowerCase();
////			    new_name = new_name.charAt(1) + new_name.substring(3);
//			    
//				out.println("<a href='historyproduct?id=" + id + "'>");
//				out.println("<div class='cell'>");
//				out.println("<div class='img'><img src='img/keyboard_" + id + ".jpg'></div>");
//				out.println("<p class='product_name'>" + name + "</p>");
//				out.println("<p class='product_category'>" + category + "</p>");
//				out.println("<p class='product_price'>$" + price + "</p>");
//				out.println("</div></a>");
//				
//				
//			}
			
//			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

