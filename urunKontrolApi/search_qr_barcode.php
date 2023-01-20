<?php

    $response = array();//bize gelen cevabı bu diziye aktaracağız

    if (isset($_POST['barcode_or_qr'])) {//isset değerin tanımlanıp tanımlanmadığına bakar
        $barcode_or_qr = $_POST['barcode_or_qr'];
   
        //DB_SERVER,DB_USER,DB_PASSWORD,DB_DATABASE değişkenleri alınır.
        require_once __DIR__ . '/db_config.php';
        
        // Bağlantı oluşturuluyor.
        $baglanti = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);
        mysqli_set_charset($baglanti,"utf8mb4");

        
        // Bağlanti kontrolü yapılır.
        if (!$baglanti) {
            die("Hatalı bağlantı : " . mysqli_connect_error());
        }
        
        $sqlsorgu = "CALL QrControl('$barcode_or_qr')";//Prosed
        $result = mysqli_query($baglanti, $sqlsorgu);
        
        if (mysqli_num_rows($result) > 0) {

            $response["product"] = array();
            
            while ($row = mysqli_fetch_assoc($result)) {
       
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

                array_push($response["product"], $product);
            }
      
            $response["success"] = 1;
            
            echo json_encode($response);
            
        }
        else {
            $response["success"] = 2;
            
            echo json_encode($response);
            
        }
        //bağlantı koparılır.
        mysqli_close($baglanti);
        
        
    } else {

        $response["success"] = 0;
        $response["message"] = "Required field(s) is missing";

        echo json_encode($response);
}
?>




