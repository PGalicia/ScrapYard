

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Reciept
 */
@WebServlet("/Reciept")
public class Reciept extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Reciept() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			PrintWriter out = response.getWriter();
			Connection conn = null;
			Class.forName("org.gjt.mm.mysql.Driver");
			conn = DriverManager.getConnection ("jdbc:mysql://matt-smith-v4.ics.uci.edu/inf124db023", "inf124db023", "pristine");
	
			Statement stmt = conn.createStatement();
			String sql = "select * from purchase ORDER BY purchase_id desc LIMIT 1";
			ResultSet purchase = stmt.executeQuery(sql);
			while(purchase.next())
			{
				out.println("Thank you for your purchase!<br>");
				
				out.println("Name: " + purchase.getString("full_name") + "<br>");
				out.println("Email: " + purchase.getString("email") + "<br>");
				out.println("Phone: " + purchase.getString("phone_number") + "<br>");
				out.println("Street: " + purchase.getString("street_address") + "<br>");
				out.println("City: " + purchase.getString("city") + "<br>");
				out.println("State: " + purchase.getString("state") + "<br>");
				out.println("Zipcode: " + purchase.getString("zip") + "<br>");
				out.println("Cardholder Name: " + purchase.getString("cardholder_name") + "<br>");
				out.println("Card Number: " + purchase.getString("card_number") + "<br>");
				out.println("Expiration Month: " + purchase.getString("card_exp_month") + "<br>");
				out.println("Expiration Year: " + purchase.getString("card_exp_year") + "<br>");
				out.println("CVV: " + purchase.getString("cvv") + "<br>");
				out.println("Shipping: " + purchase.getString("shipping") + "<br>");
				out.println("Name: " + purchase.getString("shipping_name") + "<br>");
				out.println("Shipping Address: " + purchase.getString("shipping_street") + "<br>");
				out.println("Shipping City: " + purchase.getString("shipping_city") + "<br>");
				out.println("Shipping State: " + purchase.getString("shipping_state") + "<br>");
				out.println("Shipping Zip: " + purchase.getString("shipping_zip") + "<br>");
				out.println("Quantity: " + purchase.getString("quantity") + "<br>");
				out.println("Total Price: " + purchase.getString("total_price") + "<br>");	
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
