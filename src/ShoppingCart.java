

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletException;
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
 * Servlet implementation class ShoppingCart
 */
@WebServlet("/ShoppingCart")
public class ShoppingCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingCart() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
    	
    	String str1 = req.getParameter("keyid");
    	String str2 = req.getParameter("quantity");
    	String str3 = req.getParameter("add");
    	String str4 = req.getParameter("list");
    	
    	res.setContentType("text/html");
    	PrintWriter out = res.getWriter();
    	
    	if(str3 != null)
    	{

    		Cookie clientCookies[] = req.getCookies();
    		
    		String cookieid = "product0";
    		
    		if(clientCookies != null)
    		{
    			cookieid = "product" + Integer.toString(clientCookies.length);
    		}
    		
    		String value = str1 + " " + str2;
    		
    		
    		Cookie c1 = new Cookie(cookieid, value);
    		
    		res.addCookie(c1);
    		//res.sendRedirect("javahome.html");
    		res.sendRedirect("ShoppingCart?list=CART");
    	}
    	
    	else if(str4 != null)
    	{
    		try {
        		Connection conn = null;
				Class.forName("org.gjt.mm.mysql.Driver");
				conn = DriverManager.getConnection ("jdbc:mysql://matt-smith-v4.ics.uci.edu/inf124db023", "inf124db023", "pristine");
				//out.print("Connection successful<br>");
				
				Statement stmt = conn.createStatement();
				
				/*-------------------------------------Navigation----------------------------------------------------*/
				out.println("<!DOCTYPE html>");
				out.println("<html>");
				out.println("<head>");
				out.println("<title>The Yard</title>");

					out.println("<link rel=\"icon\" href=\"https://cdn0.iconfinder.com/data/icons/e-commerce-and-shopping-2/512/keyboard_device_computer_modern_web_typing_keys_usability_tool_equipment_computing_flat_design_icon-512.png\">");
						out.println("<link rel=\"stylesheet\" href=\"css/home.css\">");
						out.println("<link rel=\"stylesheet\" href=\"css/product.css\">");
					out.println("</head>");
				out.println("<body>");
					
				out.println("<!-- Navigation -->");
				out.println("<div id=\"container_main\" class=\"wrapper\">");
				out.println("<div id=\"header\">");
					out.println("<ul id=\"primary_menu\">");
						out.println("<li><span class=\"nav_title\"><a href=\"home.html\">HOME</a></span></li>");
							out.println("<li><span class=\"nav_title\"><a href=\"about.html\">ABOUT</a></span></li>");
							out.println("<li><span class=\"nav_title\"><a href=\"ShoppingCart?list=CART\">CART</a></span></li>");
				            out.println("</ul>");

						out.println("<div id=\"home\">");
						out.println("<p id=\"scrap\" class=\"logo\"><a href=\"home.html\"><span>SCRAP</span></a></p>");
								out.println("<p id=\"yard\" class=\"logo\"><a href=\"home.html\"><span>YARD</span></a></p>");
								out.println("</div>");
						out.println("</div>");
				out.println("</div>");
				out.println("<br>");
				out.println("<br>");
				out.println("<br>");
				out.println("<br>");
				/*--------------------------------------------------------------------------------------------------*/
				
	    		Cookie clientCookies[] = req.getCookies();
	    		
	    		if(clientCookies == null)
	    		{
	    			out.println("<span style'text-align:center; font-family:Verdana; font-weight:bold; font-size:2.5em; color:rgb(128, 128, 128);'>Cart Empty! Add something to cart first!</span>");
	    		}
	    		else
	    		{
	    		double totalPrice = 0.0;
	    		int totalQty = 0;
	    		String productIds = "";
	    		
	    		for(int i = 0; i < clientCookies.length; i++)
	    		{

	        		//System.out.println("split0 before if: " + clientCookies[i].getValue().split(" ")[0]);
	        		//System.out.println("split1 before if: " + clientCookies[i].getValue().split(" ")[1]);
	    			if(clientCookies[i].getName().contains("product"))
	    			{
		    			String keyboard_id = clientCookies[i].getValue().split(" ")[0];
		    			String keyboard_qty = clientCookies[i].getValue().split(" ")[1];

		    			
		    			ResultSet rs = stmt.executeQuery("SELECT * FROM product_info WHERE id = " + keyboard_id);
		    			while(rs.next())
		    			{
		    				String id = rs.getString("id");
		    				String name = rs.getString("name");
		    				String category = rs.getString("category");
		    				double price = Double.parseDouble(rs.getString("price"));
		    				String quote = rs.getString("quote");
		    				String color = rs.getString("color");
		    				String connection = rs.getString("connection");
		    				String dimension = rs.getString("dimension");
		    				String weight = rs.getString("weight");
		    				String description = rs.getString("description");

		    				totalPrice += (price * Double.parseDouble(keyboard_qty));
		    				totalQty += Integer.parseInt(keyboard_qty);
		    				productIds += id;
	
		    				out.println("<hr>");
		    				out.println("<div class=\"cart\">");
		    				out.println("<table style=\"width:100%\">");
		    				out.println("<tr>");
		    				out.println("<td><div id=\"cartimg\" style=\"height:250px;width:250px;\"><img src=img/keyboard_" + keyboard_id + ".jpg></div></td>");
		    				out.println("<td><table><tr><th>" + name + "</th></tr><tr><td>" + color + "</td></tr></table></td>");
		    				out.println("<td>Qty: " + keyboard_qty + "</td>");
		    				out.println("</tr>");
		    				out.println("</table>");
		    				out.println("</div>");
		    				
		    				
		    				
	//		    			out.print(id + "<br>");
	//		    			out.print(name + "<br>");
	//		    			out.print(category + "<br>");
	//		    			out.print(price + "<br>");
	//		    			out.print(quote + "<br>");
	//		    			out.print(color + "<br>");
	//		    			out.print(connection + "<br>");
	//		    			out.print(dimension + "<br>");
	//		    			out.print(weight + "<br>");
	//		    			out.print(description + "<br>");
		    				
		    			}
	    			}
	    		}
				out.println("<hr>");
				conn.close();
				
				/*---------------------------------------Payment Form------------------------*/
				out.println("<div id='container_product_info' class='wrapper'> <button onclick=\"togglePayment()\" id=\"buybtn\">Buy</button></div>");
				
				out.println("<div class=\"wrapper\" id=\"buy\">");
				out.println("<div class=\"col-75\">");
				out.println("<div class=\"container\">");
				out.println("<!-- After completing the form, launch email client to with Subject, Body, and Email filled out -->");
				out.println("<form action=\"OrderDetails\" method=\"post\" id=\"payment_form\">");
				//out.println("<form action=<?php echo \"'php_include/purchase.php?pprice=\" . $product_price . \"&product_id=\" . $id . \"'\";?> method=\"post\" id=\"payment_form\">");
				
				
				out.println("<div class=\"row\">");
				out.println("<div class=\"col-50\">");
				out.println("<h3>Billing Address</h3>");
				out.println("<input type=\"hidden\" name=\"productids\" value=\"" + productIds + "\">");
				out.println("<input type=\"hidden\" name=\"totalprice\" value=\"" + totalPrice + "\">");
				out.println("<input type=\"hidden\" name=\"totalqty\" value=\"" + totalQty + "\">");
				out.println("<input type=\"text\" id=\"fname\" name=\"fullname\" placeholder=\"Full Name\" required pattern=\"[a-zA-Z ]*$\">");
				out.println("<input type=\"text\" id=\"email\" name=\"email\" placeholder=\"Email\" required pattern=\"[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$\" >");
				out.println("<input type=\"text\" id=\"phone\" name=\"phone\" placeholder=\"Phone Number\" required pattern=\"[0-9]{10}\" minlength=\"10\" maxlength=\"10\">");
				out.println("<input type=\"text\" id=\"adr\" name=\"streetaddress\" placeholder=\"Street Address\" required minlength=\"1\" maxlength=\"40\">");
				out.println("<input type=\"text\" id=\"city\" name=\"cityname\" placeholder=\"City\" required pattern=\"[a-zA-z ]*$\">");
				out.println("<div class=\"row\">");
				out.println("<div class=\"col-50\">");
				out.println("<input type=\"text\" id=\"state\" name=\"statename\" placeholder=\"State\" required pattern=\"[a-zA-Z]{2}\" minlength=\"2\" maxlength=\"2\">");
				out.println("</div>");
				out.println("<div class=\"col-50\">");
				out.println("<input type=\"text\" id=\"zip\" name=\"zipcode\" placeholder=\"Zip\" required pattern=\"[0-9]{5}\" minlength-\"5\" maxlength=\"5\">");
				out.println("</div>");
				out.println("</div>");
				out.println("</div>");
				out.println("<div class=\"col-50\">");
				out.println("<h3>Payment</h3>");
				out.println("<input type=\"text\" id=\"cname\" name=\"cardname\" placeholder=\"Cardholder Name\" required pattern=\"[a-zA-Z ]*$\">");
				out.println("<input type=\"text\" id=\"ccnum\" name=\"cardnumber\" placeholder=\"Card Number\" required pattern=\"[0-9]{16}\" minlength=\"16\" maxlength=\"16\">");
				out.println("<input type=\"text\" id=\"expmonth\" name=\"expmonth\" placeholder=\"EXP Month\" required pattern=\"[0-9]{1,2}\" minlength=\"1\" maxlength=\"2\">");
				out.println("<div class=\"row\">");
				out.println("<div class=\"col-50\">");
				out.println("<input type=\"text\" id=\"expyear\" name=\"expyear\" placeholder=\"EXP Year\" required pattern=\"[0-9]{4}\" minlength=\"4\" maxlength=\"4\">");
				out.println("</div>");
				out.println("<div class=\"col-50\">");
				out.println("<input type=\"text\" id=\"cvv\" name=\"cvv\" placeholder=\"CVV\" required pattern=\"[0-9]{3}\" minlength=\"3\" maxlength=\"3\">");
				out.println("</div>");
				out.println("</div>");
				//out.println("<input type=\"text\" id=\"quantity\" name=\"quantity\" placeholder=\"Quantity\" required pattern=\"[0-9]{1,2}\" minlength=\"1\" maxlength=\"2\" onchange=\"changeRate('free')\">");
				out.println("<h3 style=\"padding: 0.6em 0 0 0.3em; margin: 0;\">Total Price: $ <span id=\"total_price\">"+totalPrice+" + Shipping</span></h3>");
				out.println("</div>");
				out.println("</div>");
				out.println("<h3 style=\"margin-bottom: 0.5em;\">Shipping</h3>");
				out.println("<!-- Estimated Shipping will show after a user pick the type of shipping -->");
				//out.println("<p style=\"color: rgb(128, 128, 128); margin: 0; padding: 0 0 0.5em 0;\">Estimated Arrival Date: <span id=\"shipping_date\">Unknown</span></p>");
				out.println("<input type=\"radio\" id=\"shipping\" name=\"shipping\" required onclick=\"estimateDate('free'); changeRate('free');\" value=\"free\"><b>FREE Shipping</b>");
				out.println("<p style=\"color: rgb(128, 128, 128, 0.8); margin: 0; padding: 0 0 0.5em 1.2em;\">8 - 15 Days | Free</p>");
				out.println("<input type=\"radio\" id=\"shipping\" name=\"shipping\" required onclick=\"estimateDate('standard'); changeRate('standard');\" value=\"standard\"><b>Standard Shipping</b>");
				out.println("<p style=\"color: rgb(128, 128, 128, 0.8); margin: 0; padding: 0 0 0.5em 1.2em;\">5 - 7 Days | + $5.00</p>");
				out.println("<input type=\"radio\" id=\"shipping\" name=\"shipping\" required onclick=\"estimateDate('express'); changeRate('express');\" value=\"express\"><b>Express Shipping</b>");
				out.println("<p style=\"color: rgb(128, 128, 128, 0.8); margin: 0; padding: 0 0 0.5em 1.2em;\">2 - 3 Days | + $10.00</p>");
				out.println("<input type=\"radio\" id=\"shipping\" name=\"shipping\" required onclick=\"estimateDate('overnight'); changeRate('overnight');\" value=\"overnight\"><b>Overnight Shipping</b>");
				out.println("<p style=\"color: rgb(128, 128, 128, 0.8); margin: 0; padding: 0 0 0.5em 1.2em;\">1 Day | + $20.00</p>");
				out.println("<label>");
				out.println("<input type=\"checkbox\" id=\"address_check\" checked=\"checked\" name=\"sameadr\" onchange=\"toggleShipping(); toggleRequired();\"> Shipping address same as billing");
				out.println("</label>");
				out.println("<div class=\"row\" id=\"shipping_information\">");
				out.println("<div class=\"col-50\">");
				out.println("<h3>Shipping Address</h3>");
				out.println("<input type=\"text\" id=\"fname_ship\" name=\"firstname\" placeholder=\"Full Name\" pattern=\"[a-zA-Z ]*$\">");
				out.println("<input type=\"text\" id=\"adr_ship\" name=\"address\" placeholder=\"Street Address\" >");
				out.println("<input type=\"text\" id=\"city_ship\" name=\"city\" placeholder=\"City\" pattern=\"[a-zA-z ]*$\">");
				out.println("<div class=\"row\">");
				out.println("<div class=\"col-50\">");
				out.println("<input type=\"text\" id=\"state_ship\" name=\"state\" placeholder=\"State\" pattern=\"[a-zA-Z]{2}\" minlength=\"2\" maxlength=\"2\">");
				out.println("</div>");
				out.println("<div class=\"col-50\">");
				out.println("<input type=\"text\" id=\"zip_ship\" name=\"zip\" placeholder=\"Zip\" pattern=\"[0-9]{5}\" minlength-\"5\" maxlength=\"5\">");
				out.println("</div>");
				out.println("</div>");
				out.println("</div>");
				out.println("</div>");
				out.println("<input type=\"submit\" value=\"Continue to checkout\" class=\"btn\">");
				out.println("</form>");
				out.println("</div>");
				out.println("</div>");
				out.println("</div>");
				out.println("<script>");
				out.println("function togglePayment() {");
				out.println("var x = document.getElementById(\"buy\");");
				out.println("if (x.style.display && x.style.display !== \"none\") {");
				out.println("x.style.display = \"none\";");
				out.println("document.getElementById(\"buybtn\").innerHTML = \"Buy\"");
				out.println("} else {");
				out.println("document.getElementById(\"buybtn\").innerHTML = \"Cancel\"");
				out.println("x.style.display = \"block\";");
				out.println("window.scrollBy(0, 500);");
				out.println("}");
				out.println("}");
				out.println("function toggleRequired() {");
				out.println("if(document.getElementById(\"fname_ship\").hasAttribute('required') !== true){");
				out.println("document.getElementById(\"fname_ship\").setAttribute('required', 'required');");
				out.println("}");
				out.println("else{");
				out.println("document.getElementById(\"fname_ship\").removeAttribute('required');");
				out.println("}");
				out.println("if(document.getElementById(\"adr_ship\").hasAttribute('required') !== true){");
				out.println("document.getElementById(\"adr_ship\").setAttribute('required', 'required');");
				out.println("}");
				out.println("else{");
				out.println("document.getElementById(\"adr_ship\").removeAttribute('required');");
				out.println("}");
				out.println("if(document.getElementById(\"city_ship\").hasAttribute('required') !== true){");
				out.println("document.getElementById(\"city_ship\").setAttribute('required', 'required');");
				out.println("}");
				out.println("else{");
				out.println("document.getElementById(\"city_ship\").removeAttribute('required');");
				out.println("}");
				out.println("if(document.getElementById(\"state_ship\").hasAttribute('required') !== true){");
				out.println("document.getElementById(\"state_ship\").setAttribute('required', 'required');");
				out.println("}");
				out.println("else{");
				out.println("document.getElementById(\"state_ship\").removeAttribute('required');");
				out.println("}");
				out.println("if(document.getElementById(\"zip_ship\").hasAttribute('required') !== true){");
				out.println("document.getElementById(\"zip_ship\").setAttribute('required', 'required');");
				out.println("}");
				out.println("else{");
				out.println("document.getElementById(\"zip_ship\").removeAttribute('required');");
				out.println("}");
				out.println("}");
				out.println("function changeRate(shipping_type){");
				out.println("var xmlhttp = new XMLHttpRequest();");
				out.println("xmlhttp.onreadystatechange = function() {");
				out.println("if (this.readyState == 4 && this.status == 200) {");
				out.println("document.getElementById(\"total_price\").innerHTML = this.responseText;");
				out.println("}");
				out.println("};");
				out.println("xmlhttp.open(\"GET\", \"php_include/price.php?shipping_type=\" + shipping_type + \"&product_price=\" + " + totalPrice + " +\"&quantity=\" + document.getElementById(\"quantity\").value, true);");
				out.println("xmlhttp.send();");
				out.println("}");
				out.println("function estimateDate(shipping_type){");
				out.println("var xmlhttp = new XMLHttpRequest();");
				out.println("xmlhttp.onreadystatechange = function() {");
				out.println("if (this.readyState == 4 && this.status == 200) {");
				out.println("document.getElementById(\"shipping_date\").innerHTML = this.responseText;");
				out.println("}");
				out.println("};");
				out.println("xmlhttp.open(\"GET\", \"php_include/shipping.php?shipping_type=\" + shipping_type, true);");
				out.println("xmlhttp.send();");
				out.println("}");
				out.println("function toggleShipping(){");
				out.println("var x = document.getElementById(\"address_check\");");
				out.println("var y = document.getElementById(\"shipping_information\");");
				out.println("if(x.checked === true){");
				out.println("y.style.display = \"none\";");
				out.println("document.getElementById(\"fname_ship\").value = \"\";");
				out.println("document.getElementById(\"adr_ship\").value = \"\";");
				out.println("document.getElementById(\"city_ship\").value = \"\";");
				out.println("document.getElementById(\"state_ship\").value = \"\";");
				out.println("document.getElementById(\"zip_ship\").value = \"\";");
				out.println("} else {");
				out.println("y.style.display = \"block\";");
				out.println("}");
				out.println("}");
				out.println("</script>");
				
				
	    		}
				
				/*-------------------------------------Navigation----------------------------------------------------*/
				out.println("</body>");
				out.println("</html>");
				/*---------------------------------------------------------------------------------------------------*/
				
			} catch (Exception e) {
				e.printStackTrace();
			}

    	}
    	out.close();
    	
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
