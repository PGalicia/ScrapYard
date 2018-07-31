<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>The Yard</title>
	<link rel="icon" href="https://cdn0.iconfinder.com/data/icons/e-commerce-and-shopping-2/512/keyboard_device_computer_modern_web_typing_keys_usability_tool_equipment_computing_flat_design_icon-512.png">
	<link rel="stylesheet" href="css/home.css">
</head>
<body>
	<div id="container_main" class="wrapper">
		<div id="header">
			<ul id="primary_menu">
				<li><span class="nav_title"><a href="home.jsp">HOME</a></span></li>
				<li><span class="nav_title"><a href="about.html">ABOUT</a></span></li>
				<li><span class="nav_title"><a href="ShoppingCart?list=CART">CART</a></span></li>
				<li><span class="nav_title"><a href="displayproduct">HISTORY</a></span></li>
			</ul>

			<div id="home">
					<p id="scrap" class="logo"><a href="home.jsp"><span>SCRAP</span></a></p>
					<p id="yard" class="logo"><a href="home.jsp"><span>YARD</span></a></p>
			</div>
		</div>
	</div>
	<div id="container_head_text" class="wrapper">
		<div id="header_photo">
			<p class="phrase"><span>LOVE SUPERHEROES?</span></p>
			<p class="phrase"><span>LIKE KEYBOARDS?</span></p>
			<p class="phrase"><span>COME WASTE YOUR MONEY HERE</span></p>
		</div>
	</div>
	

	<%@ page import = "java.sql.Statement" %>
	<%@ page import = "java.sql.DriverManager" %>
	<%@ page import = "java.sql.Connection" %>
	<%@ page import = "java.sql.ResultSet" %>
	<%@ page import = "com.uci.rest.service.jsonToObject" %>
	<%@ page import = "java.util.List" %>
	<%@ page import = "com.uci.rest.model.keyboard" %>
	<%
		Connection conn = null;
		Statement stmt = null;
		
		try {
			List<keyboard> keyboards = jsonToObject.getAllKeyboards();
			
			out.println("<div id='container_items' class='wrapper'>");
			
			for (keyboard keyboard : keyboards)
			{
				
				out.println("<a href='historyproduct?id=" + keyboard.getId() + "'>");
				out.println("<div class='cell'>");
				out.println("<div class='img'><img src='img/keyboard_" + keyboard.getId() + ".jpg'></div>");
				out.println("<p class='product_name'>" + keyboard.getName() + "</p>");
				out.println("<p class='product_category'>" + keyboard.getCategory() + "</p>");
				out.println("<p class='product_price'>$" + keyboard.getPrice() + "</p>");
				out.println("</div></a>");
				
			}
			out.println("</div>");
				
		} catch (Exception e) {
			out.println("Error @ home.jsp: " + e );
		}
	%>
	
</body>
</html>