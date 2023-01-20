<?php

$response = array();

if (isset($_POST['product_name']) && isset($_POST['brand_name'])&& isset($_POST['category_name'])
&& isset($_POST['price'])&& isset($_POST['unit_weight'])&& isset($_POST['barcode_or_qr'])&& isset($_POST['max_stock_level'])
&& isset($_POST['stock_danger_level'])) {
    $product_name = $_POST['product_name'];
    $brand_name = $_POST['brand_name'];
    $category_name = $_POST['category_name'];
    $price = $_POST['price'];
    $unit_weight = $_POST['unit_weight'];
    $barcode_or_qr = $_POST['barcode_or_qr'];
    $max_stock_level = $_POST['max_stock_level'];
    $stock_danger_level = $_POST['stock_danger_level'];

    $category_id;
    $brand_id;



   
    //DB_SERVER,DB_USER,DB_PASSWORD,DB_DATABASE değişkenleri alınır.
    require_once __DIR__ . '/db_config.php';
    
    // Bağlantı oluşturuluyor.
    $baglanti = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);
    mysqli_set_charset($baglanti,"utf8mb4");

    
    // Bağlanti kontrolü yapılır.
    if (!$baglanti) {
        die("Hatalı bağlantı : " . mysqli_connect_error());
    }
    

    

    
    $sqlsorgu = "CALL sp_insert_product('$barcode_or_qr','$product_name','$category_name','$brand_name','$price','$unit_weight','$max_stock_level','$stock_danger_level')";

    
    if (mysqli_query($baglanti, $sqlsorgu)) {
        
        $response["success"] = 1;
        $response["message"] = "successfully ";

        echo json_encode($response);
    } else {
    
        $response["success"] = 0;
        $response["message"] = "No product found";

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



