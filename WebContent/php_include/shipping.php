<!-- Connect to Database -->
<?php include 'connect.php';?>

<?php
	// Access the chosen productid
	$shipping_type = $_GET["shipping_type"];

	// Access all items in PRODUCT_INFO
    $sql = "SELECT * FROM shipping where shipping_type = '" . $shipping_type . "'";
	$stmt = $conn->query($sql);
	$row = $stmt->fetchObject();

	// Find the earliest and lastest possible arrival dates
	date_default_timezone_set('America/Los_Angeles');
	$start_date = date_modify(date_create(date("Y-m-d")), "+" . $row->start_range . " days");
	$end_date = date_modify(date_create(date("Y-m-d")), "+" . $row->end_range . " days");

	// Print Estimated Arrival Dates
	if ($shipping_type == "overnight") {
		echo date_format($end_date, "m/d/Y");
	} else {
		echo date_format($start_date, "m/d/Y") . " - " . date_format($end_date, "m/d/Y");
	}

?>

<!-- Close Connection -->
<?php include 'disconnect.php';?>