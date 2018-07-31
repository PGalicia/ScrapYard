<!-- Connect to Database -->
<?php include 'connect.php';?>

<?php
	// Access the chosen productid
	$shipping_type = $_GET["shipping_type"];
	$product_price = $_GET["product_price"];
    $quantity = $_GET["quantity"];

	// Access price in PRODUCT_INFO
    $sql = "SELECT price FROM shipping where shipping_type = '" . $shipping_type . "'";
	$stmt = $conn->query($sql);
	$row = $stmt->fetchObject();

	// Add the shipping rate to product price
	$product_price = floatval($product_price) * floatval($quantity);
	$new_price = floatval($row->price) + $product_price;

	// Print total price
	echo $new_price;
	
?>

<!-- Close Connection -->
<?php include 'disconnect.php';?>