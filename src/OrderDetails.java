import java.sql.DriverManager;
import java.sql.ResultSet;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class OrderDetails
 */
@WebServlet("/OrderDetails")
public class OrderDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html");
    	PrintWriter out = response.getWriter();
    	
		try {
    		Connection conn = null;
			Class.forName("org.gjt.mm.mysql.Driver");
			conn = DriverManager.getConnection ("jdbc:mysql://matt-smith-v4.ics.uci.edu/inf124db023", "inf124db023", "pristine");

			Statement stmt = conn.createStatement();

			String sameadr = request.getParameter("sameadr");
			String product_id = request.getParameter("productids");
			String quantity = request.getParameter("totalqty");

			String fullname = request.getParameter("fullname");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String streetaddress = request.getParameter("streetaddress");
			String cityname = request.getParameter("cityname");
			String statename = request.getParameter("statename");
			String zipcode = request.getParameter("zipcode");
			String cardname = request.getParameter("cardname");
			String cardnumber = request.getParameter("cardnumber");
			String expmonth = request.getParameter("expmonth");
			String expyear = request.getParameter("expyear");
			String cvv = request.getParameter("cvv");
			String shipping = request.getParameter("shipping");
			String firstname = request.getParameter("firstname");
			String address = request.getParameter("address");
			String city = request.getParameter("city");
			String state = request.getParameter("state");
			String zip = request.getParameter("zip");
			String shipprice = "0";
			
			String sqlship = "Select * FROM shipping WHERE shipping_type = '" + shipping + "'";
			ResultSet rsship = stmt.executeQuery(sqlship);
			while(rsship.next())
			{
				shipprice = rsship.getString("price");
				System.out.println("shipprice: " + shipprice);
			}
			Double total_price = Math.round((Double.parseDouble(request.getParameter("totalprice")) + Double.parseDouble(shipprice))*100.0) / 100.0;
			
			String sql = "INSERT INTO purchase (product_id, full_name, email, phone_number, street_address, city, state, zip, cardholder_name, card_number, card_exp_month, card_exp_year, cvv, quantity, shipping, shipping_name, shipping_street, shipping_city, shipping_state, shipping_zip, total_price) VALUES ('" + product_id + "', '" + fullname + "', '"  +  email + "', '" + phone + "', '" + streetaddress + "', '" + cityname + "', '" + statename + "', '" + zipcode + "', '" + cardname + "', '" + cardnumber + "', '" + expmonth + "', '" + expyear + "', '" + cvv + "', " + quantity + ", '" + shipping + "', '" + firstname + "', '" + address + "', '" + city + "', '" + state + "', '" + zip + "', '" + total_price + "')";


			//out.println("vars set");
			stmt.executeUpdate(sql);

			RequestDispatcher rd = request.getRequestDispatcher("Reciept?product_id=" + product_id);
			rd.forward(request, response);
			
			//out.println("executed");
			conn.close();
			
//			out.println("Thank you for your purchase!<br>");
//			out.println("Name:" + fullname + "<br>");
//			out.println("Email: " + email + "<br>");
//			out.println("Phone: " + phone + "<br>");
//			out.println("Street: " + streetaddress + "<br>");
//			out.println("City: " + cityname + "<br>");
//			out.println("State: " + statename + "<br>");
//			out.println("Zipcode: " + zipcode + "<br>");
//			out.println("Cardholder Name: " + cardname + "<br>");
//			out.println("Card Number: " + cardnumber + "<br>");
//			out.println("Expiration Month: " + expmonth + "<br>");
//			out.println("Expiration Year: " + expyear + "<br>");
//			out.println("CVV: " + cvv + "<br>");
//			out.println("Shipping: " + shipping + "<br>");
//			out.println("Name: " + firstname + "<br>");
//			out.println("Shipping Address: " + address + "<br>");
//			out.println("Shipping City: " + city + "<br>");
//			out.println("Shipping State: " + state + "<br>");
//			out.println("Shipping Zip: " + zip + "<br>");
//			out.println("Quantity: " + quantity + "<br>");
//			out.println("Total Price: " + total_price + "<br>");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		out.close();
	}

}
