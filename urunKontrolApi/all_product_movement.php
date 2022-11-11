<?php
    // array for JSON response
    $response = array();
    
    //DB_SERVER,DB_USER,DB_PASSWORD,DB_DATABASE değişkenleri alınır.
    require_once __DIR__ . '/db_config.php';
    
    // Bağlantı oluşturuluyor.
    $baglanti = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);
    
    // Bağlanti kontrolü yapılır.
    if (!$baglanti) {
        die("Hatalı bağlantı : " . mysqli_connect_error());
    }
    
    $sqlsorgu = "SELECT * FROM product,category,brand,product_movement,user WHERE 
    product.category_id = category.category_id and product.brand_id = brand.brand_id and 
    product_movement.product_id = product.product_id and product_movement.user_id = user.user_id ";
    $result = mysqli_query($baglanti, $sqlsorgu);
    
    // result kontrolü yap
    if (mysqli_num_rows($result) > 0) {
        
        $response["product_movement"] = array();
        
        while ($row = mysqli_fetch_assoc($result)) {
            // temp user array
            
            $category = array();
            $category["category_id"] = $row["category_id"];
            $category["category_name"] = $row["category_name"];
            
            $brand = array();
            $brand["brand_id"] = $row["brand_id"];
            $brand["brand_name"] = $row["brand_name"];
            
            $product = array();
            $product["product_id"] = $row["product_id"];
            $product["product_name"] = $row["product_name"];
            $product["price"] = $row["price"];
            $product["unit_weight"] = $row["unit_weight"];
            $product["barcode_or_qr"] = $row["barcode_or_qr"];
            $product["category"] = $category;
            $product["brand"] = $brand;

            $user = array();
            $user["user_id"] = $row["user_id"];
            $user["name"] = $row["name"];
            $user["user_name"] = $row["user_name"];
            $user["password"] = $row["password"];
            $user["login_status"] = $row["login_status"];
            $user["tc_no"] = $row["tc_no"];           



            
            $product_movement = array();
            $product_movement["movement_id"] = $row["movement_id"];
            $product_movement["product"] = $product;
            $product_movement["user"] = $user;
            $product_movement["date"] = $row["date"];
            $product_movement["movement_state"] = $row["movement_state"];
            
            // push single product into final response array
            array_push($response["product_movement"], $product_movement);
        }
        // success
        $response["success"] = 1;
        
        // echoing JSON response
        echo json_encode($response);
        
    } else {
        // no products found
        $response["success"] = 0;
        $response["message"] = "No data found";
        
        // echo no users JSON
        echo json_encode($response,JSON_UNESCAPED_UNICODE);
    }
    //bağlantı koparılır.
    mysqli_close($baglanti);
    ?>
