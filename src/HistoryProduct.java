

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

public class HistoryProduct extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{        
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			
			Cookie clientCookies[] = request.getCookies();
			
			
			
			String pid = request.getParameter("id");
			String cookieid = "history0";
			
			//String value = cookieid + ";" + pid;
			
			if(clientCookies != null)
			{
				cookieid = "history" + Integer.toString(clientCookies.length);
			}
 
			
			// Create cookies for product id
			Cookie id = new Cookie(cookieid, pid);
			
			// Set the cookie to expire after 24 hours 
			//id.setMaxAge(60*60*24);
			
			// Add cookie in the response header
			response.addCookie(id);
			response.sendRedirect("productinfo?id=" + pid);
		}
	}
}

