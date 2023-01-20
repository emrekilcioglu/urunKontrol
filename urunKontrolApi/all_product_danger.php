<?php
    // array for JSON response
    $response = array();
    
    //DB_SERVER,DB_USER,DB_PASSWORD,DB_DATABASE değişkenleri alınır.
    require_once __DIR__ . '/db_config.php';
    
    // Bağlantı oluşturuluyor.
    $baglanti = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);
	mysqli_set_charset($baglanti,"utf8mb4");

    
    // Bağlanti kontrolü yapılır.
    if (!$baglanti) {
        die("Hatalı bağlantı : " . mysqli_connect_error());
    }
    
    $sqlsorgu = "CALL sp_all_danger_stock()";
    $result = mysqli_query($baglanti, $sqlsorgu);//Proced
    
    // result kontrolü yap
    if (mysqli_num_rows($result) > 0) {
        
        $response["product"] = array();
        
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
            $product["max_stock_level"] = $row["max_stock_level"];
            $product["stock_level"] = $row["stock_level"];
            $product["stock_danger_level"] = $row["stock_danger_level"];
            $product["category"] = $category;
            $product["brand"] = $brand;
            
            
            
            // push single product into final response array
            array_push($response["product"], $product);
        }
        // success
        $response["success"] = 1;
        
        // echoing JSON response
        echo json_encode($response,JSON_UNESCAPED_UNICODE);
        
    } else {
        // no products found
        $response["success"] = 0;
        $response["message"] = "No data found";
        
        // echo no users JSON
        echo json_encode($response);
    }
    //bağlantı koparılır.
    mysqli_close($baglanti);
    ?>
