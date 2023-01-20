<?php

    $response = array();//bize gelen cevabı bu diziye aktaracağız

    if (isset($_POST['product_name'])) {//isset değerin tanımlanıp tanımlanmadığına bakar
        $product_name = $_POST['product_name'];
   
        //DB_SERVER,DB_USER,DB_PASSWORD,DB_DATABASE değişkenleri alınır.
        require_once __DIR__ . '/db_config.php';
        
        // Bağlantı oluşturuluyor.
        $baglanti = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);
        
        // Bağlanti kontrolü yapılır.
        if (!$baglanti) {
            die("Hatalı bağlantı : " . mysqli_connect_error());
        }
        
        $sqlsorgu = "SELECT * FROM product,category,brand WHERE product.category_id = category.category_id and 
        product.brand_id = brand.brand_id and product.product_name like '%$product_name%'";
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
                $product["category"] = $category;
                $product["brand"] = $brand;

                array_push($response["product"], $product);
            }
      
            $response["success"] = 1;
            
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




