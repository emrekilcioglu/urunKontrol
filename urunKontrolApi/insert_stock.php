<?php

$response = array();

if (isset($_POST['product_id']) && isset($_POST['max_stock_level'])&& isset($_POST['stock_level'])
&& isset($_POST['price'])) {
    $product_id = $_POST['product_id'];
    $max_stock_level = $_POST['max_stock_level'];
    $stock_level = $_POST['stock_level'];
    $price = $_POST['price'];
    



   
    //DB_SERVER,DB_USER,DB_PASSWORD,DB_DATABASE değişkenleri alınır.
    require_once __DIR__ . '/db_config.php';
    
    // Bağlantı oluşturuluyor.
    $baglanti = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);
    
    // Bağlanti kontrolü yapılır.
    if (!$baglanti) {
        die("Hatalı bağlantı : " . mysqli_connect_error());
    }
    
    $sqlsorgu = "INSERT INTO product (max_stock_level,product_id,stock_level,price) 
    VALUES ('$max_stock_level','$product_id','$stock_level','$price')";

    
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



