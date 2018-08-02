<!-- Connect to Database -->
<?php include 'php_include/connect.php';?>

<!DOCTYPE html>
<html>
<head>
	<title>The Yard</title>

	<link rel="icon" href="https://cdn0.iconfinder.com/data/icons/e-commerce-and-shopping-2/512/keyboard_device_computer_modern_web_typing_keys_usability_tool_equipment_computing_flat_design_icon-512.png">
	<link rel="stylesheet" href="css/home.css">
</head>
<body>

	<!-- Navigation -->
	<?php include 'php_include/navigation.php';?>
	<!-- Home Header Text-->
	<?php include 'php_include/home_header.php';?>

	<!-- Product Listing -->
	<?php  
		// Access all items in PRODUCT_INFO
	    $sql = "SELECT id, name, category, price FROM product_info";
		$stmt = $conn->query($sql);
		$row = $stmt->fetchObject();

		// Product template
		// Parameter: product_id, product_name, product_category, product_price
		function cell($id, $name, $category, $price) {
			$new_name = strtolower(str_replace(str_split("[]"), "", $name));
			echo "<a href='product.php?id=" . $id . "&name=" . $new_name . "'>";
			echo "<div class='cell'>";
			echo "<div class='img'><img src='img/keyboard_" . $id . ".jpg'></div>";
			echo "<p class='product_name'>" . $name . "</p>";
			echo "<p class='product_category'>" . $category . "</p>";
			echo "<p class='product_price'>$" . $price . "</p>";
			echo "</div></a>";
		}

		// Print all products
		echo "<div id='container_items' class='wrapper'>";

		foreach ($conn->query($sql) as $row) {
			cell($row['id'], $row['name'], $row['category'], $row['price']);
		}

		echo "</div>";

	?>

</body>
</html>

<!-- Close Connection -->
<?php include 'php_include/disconnect.php';?>