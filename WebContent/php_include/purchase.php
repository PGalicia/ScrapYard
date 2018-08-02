<html>

    <head>
    
        <title>Purchase Information</title>
    
    </head>

    <body>
    
    <?php
        include 'connect.php';
        
        $product_id = $_GET['product_id'];
        
        /*---------------------Validation--------------------*/
        if(isset($_POST['sameadr'])){
            if (isset($_POST['fullname']) && isset($_POST['email']) && isset($_POST['phone']) && isset($_POST['streetaddress']) && isset($_POST['cityname']) && isset($_POST['statename']) && isset($_POST['zipcode']) && isset($_POST['cardname']) && isset($_POST['cardnumber']) && isset($_POST['expmonth']) && isset($_POST['expyear']) && isset($_POST['cvv']) && isset($_POST['quantity']) && isset($_POST['shipping']))
            {
                $validdata = TRUE;
                
                $fullname = $_POST['fullname'];
                if(strlen($fullname) > 45)
                {
                    echo "Please enter a shorter name."."<br/>";
                    $validdata = FALSE;
                }
                
                $email = $_POST['email'];
                if(strlen($email) > 45)
                {
                    echo "Please enter a shorter email."."<br/>";
                    $validdata = FALSE;
                }
                
                $phone = $_POST['phone'];
                if(strlen($phone) > 10)
                {
                    echo "Please enter a valid number in the correct format."."<br/>";
                    $validdata = FALSE;
                }
                
                $streetaddress = $_POST['streetaddress'];
                if(strlen($streetaddress) > 45)
                {
                    echo "Please enter a shorter address."."<br/>";
                    $validdata = FALSE;
                }
                
                $cityname = $_POST['cityname'];
                if(strlen($cityname) > 45)
                {
                    echo "Please enter a shorter city."."<br/>";
                    $validdata = FALSE;
                }
                
                $statename = $_POST['statename'];
                if(strlen($statename) > 2)
                {
                    echo "Please enter a valid state."."<br/>";
                    $validdata = FALSE;
                }
                
                $zipcode = $_POST['zipcode'];
                if(strlen($zipcode) > 5)
                {
                    echo "Please enter a valid zip."."<br/>";
                    $validdata = FALSE;
                }
                
                $cardname = $_POST['cardname'];
                if(strlen($cardname) > 45)
                {
                    echo "Please enter a shorter name."."<br/>";
                    $validdata = FALSE;
                }
                
                $cardnumber = $_POST['cardnumber'];
                if(!ctype_digit($cardnumber) || strlen($fullname) > 16)
                {
                    echo "Please enter a valid cardnumber."."<br/>";
                    $validdata = FALSE;
                }
                
                $expmonth = $_POST['expmonth'];
                if(!ctype_digit($expmonth) || $expmonth > 12)
                {
                    echo "Please enter a valid month."."<br/>";
                    $validdata = FALSE;
                }
                
                $expyear = $_POST['expyear'];
                if(strlen($expyear) > 4)
                {
                    echo "Please enter a valid year."."<br/>";
                    $validdata = FALSE;
                }
                
                $cvv = $_POST['cvv'];
                if(strlen($cvv) > 3)
                {
                    echo "Please enter a valid cvv."."<br/>";
                    $validdata = FALSE;
                }
                
                
                $quantity = $_POST['quantity'];
                if(strlen($quantity) > 2)
                {
                    echo "Please enter a valid quantity."."<br/>";
                    $validdata = FALSE;
                }
                
                $shipping = $_POST['shipping'];
                
                $sql = "select price from shipping where shipping_type = '" . $shipping . "'";
                $stmt = $conn->query($sql);
                $row = $stmt->fetchObject();
                $ship_price = $row->price;
                
                $sql = "select price from product_info where id = '" . $product_id . "'";
                $stmt = $conn->query($sql);
                $row = $stmt->fetchObject();
                $product_price = $row->price;
                
                $total_price = (floatval($product_price) * floatval($quantity)) + floatval($ship_price);

                if($validdata){
                    $sql = "INSERT INTO purchase (product_id, full_name, email, phone_number, street_address, city, state, zip, cardholder_name, card_number, card_exp_month, card_exp_year, cvv, quantity, shipping, total_price) VALUES (:product_id, :fullname, :email, :phone, :streetaddress, :cityname, :statename, :zipcode, :cardname, :cardnumber, :expmonth, :expyear, :cvv, :quantity, :shipping, :total_price)";
                    //echo("<pre>\n".$sql."\n</pre>\n");
                    $stmt = $conn->prepare($sql);
                    $stmt->execute(array(':product_id' => $product_id,':fullname' => $fullname, ':email' => $email, ':phone' => $phone, ':streetaddress' => $streetaddress, ':cityname' => $cityname, ':statename' => $statename, ':zipcode' => $zipcode, ':cardname' => $cardname, ':cardnumber' => $cardnumber, ':expmonth' => $expmonth, ':expyear' => $expyear, ':cvv' => $cvv, ':quantity' => $quantity, ':shipping' => $shipping, ':total_price' => $total_price));

                   // echo "<p>Data Processed</>";
                    $sql = "select * from purchase ORDER BY purchase_id desc LIMIT 1";
                    $stmt = $conn->query($sql);
                    $row = $stmt->fetchObject();
                    
                    echo "Thank you for your purchase!"."<br/>";
                    echo "\nName: ".$row->full_name."<br/>";
                    echo "\nEmail: ".$row->email."<br/>";
                    echo "\nPhone Number: ".$row->phone_number."<br/>";
                    echo "\nStreet: ".$row->street_address."<br/>";
                    echo "\nCity: ".$row->city."<br/>";
                    echo "\nState: ".$row->state."<br/>";
                    echo "\nZip: ".$row->zip."<br/>";
                    echo "\nCardholder Name: ".$row->cardholder_name."<br/>";
                    echo "\nCard Number ".$row->card_number."<br/>";
                    echo "\nExpiration Month: ".$row->card_exp_month."<br/>";
                    echo "\nExpiration Year: ".$row->card_exp_year."<br/>";
                    echo "\nCVV: ".$row->cvv."<br/>";
                    echo "\nQuantity: ".$row->quantity."<br/>";
                    echo "\nShipping Method: ".$row->shipping."<br/>";
                    echo "\nTotal Price: ".$total_price."<br/>";
                }
                else
                {
                    echo "Please return and fix the mentioned fields.";
                }

            }
            else
            {
                echo "Please return back and fill in the required fields.";
            }
        }
        
        else if(!isset($_POST['sameadr']))
        {
            
            if(isset($_POST['fullname']) && isset($_POST['email']) && isset($_POST['phone']) && isset($_POST['streetaddress']) && isset($_POST['cityname']) && isset($_POST['statename']) && isset($_POST['zipcode']) && isset($_POST['cardname']) && isset($_POST['cardnumber']) && isset($_POST['expmonth']) && isset($_POST['expyear']) && isset($_POST['cvv']) && isset($_POST['quantity']) && isset($_POST['shipping']) && isset($_POST['firstname']) && isset($_POST['address']) && isset($_POST['city']) && isset($_POST['state']) && isset($_POST['zip']))
            {
                $validdata = TRUE;
                
                $fullname = $_POST['fullname'];
                if(strlen($fullname) > 45)
                {
                    echo "Please enter a shorter name."."<br/>";
                    $validdata = FALSE;
                }
                
                $email = $_POST['email'];
                if(strlen($email) > 45)
                {
                    echo "Please enter a shorter email."."<br/>";
                    $validdata = FALSE;
                }
                
                $phone = $_POST['phone'];
                if(strlen($phone) > 10)
                {
                    echo "Please enter a valid number in the correct format."."<br/>";
                    $validdata = FALSE;
                }
                
                $streetaddress = $_POST['streetaddress'];
                if(strlen($streetaddress) > 45)
                {
                    echo "Please enter a shorter address."."<br/>";
                    $validdata = FALSE;
                }
                
                $cityname = $_POST['cityname'];
                if(strlen($cityname) > 45)
                {
                    echo "Please enter a shorter city."."<br/>";
                    $validdata = FALSE;
                }
                
                $statename = $_POST['statename'];
                if(strlen($statename) > 2)
                {
                    echo "Please enter a valid state."."<br/>";
                    $validdata = FALSE;
                }
                
                $zipcode = $_POST['zipcode'];
                if(strlen($zipcode) > 5)
                {
                    echo "Please enter a valid zip."."<br/>";
                    $validdata = FALSE;
                }
                
                $cardname = $_POST['cardname'];
                if(strlen($cardname) > 45)
                {
                    echo "Please enter a shorter name."."<br/>";
                    $validdata = FALSE;
                }
                
                $cardnumber = $_POST['cardnumber'];
                if(!ctype_digit($cardnumber) || strlen($fullname) > 16)
                {
                    echo "Please enter a valid cardnumber."."<br/>";
                    $validdata = FALSE;
                }
                
                $expmonth = $_POST['expmonth'];
                if(!ctype_digit($expmonth) || $expmonth > 12)
                {
                    echo "Please enter a valid month."."<br/>";
                    $validdata = FALSE;
                }
                
                $expyear = $_POST['expyear'];
                if(strlen($expyear) > 4)
                {
                    echo "Please enter a valid year."."<br/>";
                    $validdata = FALSE;
                }
                
                $cvv = $_POST['cvv'];
                if(strlen($cvv) > 3)
                {
                    echo "Please enter a valid cvv."."<br/>";
                    $validdata = FALSE;
                }
                
                
                $quantity = $_POST['quantity'];
                if(strlen($quantity) > 2)
                {
                    echo "Please enter a valid quantity."."<br/>";
                    $validdata = FALSE;
                }
                
                $shipping = $_POST['shipping'];
                
                $firstname = $_POST['firstname'];
                if(strlen($fullname) > 45)
                {
                    echo "Please enter a shorter name."."<br/>";
                    $validdata = FALSE;
                }
                
                $address = $_POST['address'];
                if(strlen($streetaddress) > 45)
                {
                    echo "Please enter a shorter address."."<br/>";
                    $validdata = FALSE;
                }
                
                $city = $_POST['city'];
                if(strlen($cityname) > 45)
                {
                    echo "Please enter a shorter city."."<br/>";
                    $validdata = FALSE;
                }
                
                $state = $_POST['state'];
                if(strlen($statename) > 2)
                {
                    echo "Please enter a valid state."."<br/>";
                    $validdata = FALSE;
                }
                
                $zip = $_POST['zip'];
                if(strlen($zipcode) > 5)
                {
                    echo "Please enter a valid zip."."<br/>";
                    $validdata = FALSE;
                }
                
                $sql = "select price from shipping where shipping_type = '" . $shipping . "'";
                $stmt = $conn->query($sql);
                $row = $stmt->fetchObject();
                $ship_price = $row->price;
                
                $sql = "select price from product_info where id = '" . $product_id . "'";
                $stmt = $conn->query($sql);
                $row = $stmt->fetchObject();
                $product_price = $row->price;
                
                $total_price = (floatval($product_price) * floatval($quantity)) + floatval($ship_price);

            
            if($validdata)
            {
                $sql = "INSERT INTO purchase (product_id, full_name, email, phone_number, street_address, city, state, zip, cardholder_name, card_number, card_exp_month, card_exp_year, cvv, quantity, shipping, shipping_name, shipping_street, shipping_city, shipping_state, shipping_zip, total_price) VALUES (:product_id, :fullname, :email, :phone, :streetaddress, :cityname, :statename, :zipcode, :cardname, :cardnumber, :expmonth, :expyear, :cvv, :quantity, :shipping, :firstname, :address, :city, :state, :zip, :total_price)";
                //echo("<pre>\n".$sql."\n</pre>\n");
                $stmt = $conn->prepare($sql);
                $stmt->execute(array(':product_id' => $product_id, ':fullname' => $fullname, ':email' => $email, ':phone' => $phone, ':streetaddress' => $streetaddress, ':cityname' => $cityname, ':statename' => $statename, ':zipcode' => $zipcode, ':cardname' => $cardname, ':cardnumber' => $cardnumber, ':expmonth' => $expmonth, ':expyear' => $expyear, ':cvv' => $cvv, ':quantity' => $quantity, ':shipping' => $shipping, ':firstname' => $firstname, ':address' => $address, ':city' => $city, ':state' => $state, ':zip' => $zip, ':total_price' => $total_price));
                
                
            //echo "All Data Processed";
                $sql = "select * from purchase ORDER BY purchase_id desc LIMIT 1";
                $stmt = $conn->query($sql);
                $row = $stmt->fetchObject();
                
                echo "Thank you for your purchase!"."<br/>";
                echo "\nName: ".$row->full_name."<br/>";
                echo "\nEmail: ".$row->email."<br/>";
                echo "\nPhone Number: ".$row->phone_number."<br/>";
                echo "\nStreet: ".$row->street_address."<br/>";
                echo "\nCity: ".$row->city."<br/>";
                echo "\nState: ".$row->state."<br/>";
                echo "\nZip: ".$row->zip."<br/>";
                echo "\nCardholder Name: ".$row->cardholder_name."<br/>";
                echo "\nCard Number ".$row->card_number."<br/>";
                echo "\nExpiration Month: ".$row->card_exp_month."<br/>";
                echo "\nExpiration Year: ".$row->card_exp_year."<br/>";
                echo "\nCVV: ".$row->cvv."<br/>";
                echo "\nQuantity: ".$row->quantity."<br/>";
                
                echo "\nShipping Name: ".$row->shipping_name."<br/>";
                echo "\nShipping Street: ".$row->shipping_street."<br/>";
                echo "\nShipping City: ".$row->shipping_city."<br/>";
                echo "\nShipping State: ".$row->shipping_state."<br/>";
                echo "\nShipping Zip: ".$row->shipping_zip."<br/>";
                echo "\nShipping Method: ".$row->shipping."<br/>";
                echo "\nTotal Price: ".$total_price."<br/>";

            }
            else
            {
                echo "Please return back and fill in the required fields.";
            }
        }
        }
        
        
        include 'disconnect.php';
        
    ?>
    
    </body>
</html>