<!-- Connect to Database -->
<?php include 'php_include/connect.php';?>

<?php

	// Access the chosen productid
	$id = $_GET["id"];
    
	
	// Access all items in PRODUCT_INFO
    $sql = "SELECT * FROM product_info where id = '" . $id . "'";
	$stmt = $conn->query($sql);
	$row = $stmt->fetchObject();
    $product_price = $row->price;
?>

<!DOCTYPE html>
<html>
<head>
    <title>The Yard</title>

    <link rel="icon" href="https://cdn0.iconfinder.com/data/icons/e-commerce-and-shopping-2/512/keyboard_device_computer_modern_web_typing_keys_usability_tool_equipment_computing_flat_design_icon-512.png">
	<link rel="stylesheet" href="css/home.css">
    <link rel="stylesheet" href="css/product.css">
</head>
<body>

	<!-- Navigation -->
	<?php include 'php_include/navigation.php';?>

	<!-- Product Information -->
	<div id="container_product_info" class="wrapper">
	    <div class="product">
	        <div class="productimg">
	            <?php echo "<img src='img/keyboard_" . $row->id . ".jpg'>";?>
	        </div>
	        <div class="a">
	            <p class="product_name"><?php echo $row->name;?></p>
	            <p class="product_category"><?php echo $row->category;?></p>
	            <p class="product_price"><?php echo "$" . $row->price;?></p>
	            <p id="product_quote" class="product_specs"><?php echo "\"" . $row->quote . "\"";?></p>
	            <p id="product_color" class="product_specs"><span class="category_title">Color:</span> <?php echo $row->color;?></p>
	            <p id="product_connection" class="product_specs"><span class="category_title">Connection:</span> <?php echo $row->connection;?></p>
	            <p id="product_dimensions" class="product_specs"><span class="category_title">Dimension:</span> <?php echo $row->dimension;?></p>
	            <p id="product_weight" class="product_specs"><span class="category_title">Weight:</span> <?php echo $row->weight . " oz";?></p>
	            <p id="product_description" class="product_specs"><?php echo $row->description;?></p>

	            <button onclick="togglePayment()" id="buybtn">Buy</button>
	        </div>
    	</div>
    </div>


    <div class="wrapper" id="buy">
        <div class="col-75">
            <div class="container">
            <!-- After completing the form, launch email client to with Subject, Body, and Email filled out -->
                <form action=<?php echo "'php_include/purchase.php?pprice=" . $product_price . "&product_id=" . $id . "'";?> method="post" id="payment_form">
                    <div class="row">
                        <div class="col-50">
                            <h3>Billing Address</h3>
                            <input type="text" id="fname" name="fullname" placeholder="Full Name" required pattern="[a-zA-Z ]*$">
                            <input type="text" id="email" name="email" placeholder="Email" required pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" >
                            <input type="text" id="phone" name="phone" placeholder="Phone Number" required pattern="[0-9]{10}" minlength="10" maxlength="10">
                            <input type="text" id="adr" name="streetaddress" placeholder="Street Address" required>
                            <input type="text" id="city" name="cityname" placeholder="City" required pattern="[a-zA-z ]*$">

                            <div class="row">
                                <div class="col-50">
                                    <input type="text" id="state" name="statename" placeholder="State" required pattern="[a-zA-Z]{2}" minlength="2" maxlength="2">
                                </div>
                                <div class="col-50">
                                    <input type="text" id="zip" name="zipcode" placeholder="Zip" required pattern="[0-9]{5}" minlength-"5" maxlength="5">
                                </div>
                            </div>
                        </div>

                        <div class="col-50">
                            <h3>Payment</h3>
                            <input type="text" id="cname" name="cardname" placeholder="Cardholder Name" required pattern="[a-zA-Z ]*$">
                            <input type="text" id="ccnum" name="cardnumber" placeholder="Card Number" required pattern="[0-9]{16}" minlength="16" maxlength="16">
                            <input type="text" id="expmonth" name="expmonth" placeholder="EXP Month" required pattern="[0-9]{1,2}" minlength="1" maxlength="2">

                            <div class="row">
                                <div class="col-50">
                                    <input type="text" id="expyear" name="expyear" placeholder="EXP Year" required pattern="[0-9]{4}" minlength="4" maxlength="4">
                                </div>
                                <div class="col-50">
                                    <input type="text" id="cvv" name="cvv" placeholder="CVV" required pattern="[0-9]{3}" minlength="3" maxlength="3">
                                </div>
                            </div>
                            <input type="text" id="quantity" name="quantity" placeholder="Quantity" required pattern="[0-9]{1,2}" minlength="1" maxlength="2" onchange="changeRate('free')">
                            <h3 style="padding: 0.6em 0 0 0.3em; margin: 0;">Total Price: $ <span id="total_price"><?php echo $row->price;?></span></h3>
                        </div>
                    </div>
                        
                    <h3 style="margin-bottom: 0.5em;">Shipping</h3>
                    <!-- Estimated Shipping will show after a user pick the type of shipping -->
                    <p style="color: rgb(128, 128, 128); margin: 0; padding: 0 0 0.5em 0;">Estimated Arrival Date: <span id="shipping_date">Unknown</span></p>
                    <input type="radio" id="shipping" name="shipping" required onclick="estimateDate('free'); changeRate('free');" value="free"><b>FREE Shipping</b>
                    <p style="color: rgb(128, 128, 128, 0.8); margin: 0; padding: 0 0 0.5em 1.2em;">8 - 15 Days | Free</p>
                    <input type="radio" id="shipping" name="shipping" required onclick="estimateDate('standard'); changeRate('standard');" value="standard"><b>Standard Shipping</b>
                    <p style="color: rgb(128, 128, 128, 0.8); margin: 0; padding: 0 0 0.5em 1.2em;">5 - 7 Days | + $5.00</p>
                    <input type="radio" id="shipping" name="shipping" required onclick="estimateDate('express'); changeRate('express');" value="express"><b>Express Shipping</b>
                    <p style="color: rgb(128, 128, 128, 0.8); margin: 0; padding: 0 0 0.5em 1.2em;">2 - 3 Days | + $10.00</p>
                    <input type="radio" id="shipping" name="shipping" required onclick="estimateDate('overnight'); changeRate('overnight');" value="overnight"><b>Overnight Shipping</b>
                    <p style="color: rgb(128, 128, 128, 0.8); margin: 0; padding: 0 0 0.5em 1.2em;">1 Day | + $20.00</p>

                    <label>
                      <input type="checkbox" id="address_check" checked="checked" name="sameadr" onchange="toggleShipping(); toggleRequired();"> Shipping address same as billing
                    </label>

                    <div class="row" id="shipping_information">
                        <div class="col-50">
                            <h3>Shipping Address</h3>
                            <input type="text" id="fname_ship" name="firstname" placeholder="Full Name" pattern="[a-zA-Z ]*$">
                            <input type="text" id="adr_ship" name="address" placeholder="Street Address" >
                            <input type="text" id="city_ship" name="city" placeholder="City" pattern="[a-zA-z ]*$">

                            <div class="row">
                                <div class="col-50">
                                    <input type="text" id="state_ship" name="state" placeholder="State" pattern="[a-zA-Z]{2}" minlength="2" maxlength="2">
                                </div>
                                <div class="col-50">
                                    <input type="text" id="zip_ship" name="zip" placeholder="Zip" pattern="[0-9]{5}" minlength-"5" maxlength="5">
                                </div>
                            </div>
                        </div>
                    </div>

                    <input type="submit" value="Continue to checkout" class="btn">
                </form>
            </div>
        </div>
    </div>

    <script>
        function toggleRequired() {
                    if(document.getElementById("fname_ship").hasAttribute('required') !== true){
                            document.getElementById("fname_ship").setAttribute('required', 'required');
                        }
                    else{
                        document.getElementById("fname_ship").removeAttribute('required');
                    }
                    
                    if(document.getElementById("adr_ship").hasAttribute('required') !== true){
                            document.getElementById("adr_ship").setAttribute('required', 'required');
                        }
                    else{
                        document.getElementById("adr_ship").removeAttribute('required');
                    }
                    
                    if(document.getElementById("city_ship").hasAttribute('required') !== true){
                            document.getElementById("city_ship").setAttribute('required', 'required');
                        }
                    else{
                        document.getElementById("city_ship").removeAttribute('required');
                    }
                    
                    if(document.getElementById("state_ship").hasAttribute('required') !== true){
                            document.getElementById("state_ship").setAttribute('required', 'required');
                        }
                    else{
                        document.getElementById("state_ship").removeAttribute('required');
                    }
                    
                    if(document.getElementById("zip_ship").hasAttribute('required') !== true){
                            document.getElementById("zip_ship").setAttribute('required', 'required');
                        }
                    else{
                        document.getElementById("zip_ship").removeAttribute('required');
                    }
            }
        
    	// Print the total price(including shipping rate) of the item
    	function changeRate(shipping_type){
            var xmlhttp = new XMLHttpRequest();
        	xmlhttp.onreadystatechange = function() {
            	if (this.readyState == 4 && this.status == 200) {
                	document.getElementById("total_price").innerHTML = this.responseText;
            	}
        	};
        	xmlhttp.open("GET", "php_include/price.php?shipping_type=" + shipping_type + "&product_price=" + <?php echo $row->price;?>+"&quantity=" + document.getElementById("quantity").value, true);
        	xmlhttp.send();

    	}

    	// Print the arrival date based on the shipping
    	function estimateDate(shipping_type){
    		var xmlhttp = new XMLHttpRequest();
        	xmlhttp.onreadystatechange = function() {
            	if (this.readyState == 4 && this.status == 200) {
                	document.getElementById("shipping_date").innerHTML = this.responseText;
            	}
        	};
        	xmlhttp.open("GET", "php_include/shipping.php?shipping_type=" + shipping_type, true);
        	xmlhttp.send();

    	}


        // When Shipping Address is different from Billing Address Display SHIPPING_INFORMATION div
        function toggleShipping(){
            var x = document.getElementById("address_check");
            var y = document.getElementById("shipping_information");
            if(x.checked === true){
                y.style.display = "none";
                document.getElementById("fname_ship").value = "";
                document.getElementById("adr_ship").value = "";
                document.getElementById("city_ship").value = "";
                document.getElementById("state_ship").value = "";
                document.getElementById("zip_ship").value = "";
            } else {
                y.style.display = "block";
            }
        }


        // When the BUY button is pressed, display BUY div
        function togglePayment() {
            var x = document.getElementById("buy");
            if (x.style.display && x.style.display !== "none") {
                x.style.display = "none";
                document.getElementById("buybtn").innerHTML = "Buy"
            } else {
                document.getElementById("buybtn").innerHTML = "Cancel"
                x.style.display = "block";
                window.scrollBy(0, 500);
            }
        }

        // When user inputs email, update <form>
//        function updateEmail(){
//            document.getElementById("payment_form").action = "mailto:" + document.getElementById("email").value + "?Subject=Order%20Confirmation";
//        }
            
    </script>
</body>
</html>

<!-- Close Connection -->
<?php include 'php_include/disconnect.php';?>