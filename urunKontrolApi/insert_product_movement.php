<?php

$response = array();

if (isset($_POST['product_id']) && isset($_POST['user_id'])&& isset($_POST['date'])
&& isset($_POST['movement_state'])) {
    $product_id = $_POST['product_id'];
    $user_id = $_POST['user_id'];
    $date = $_POST['date'];
    $movement_state = $_POST['movement_state'];
    



   
    //DB_SERVER,DB_USER,DB_PASSWORD,DB_DATABASE değişkenleri alınır.
    require_once __DIR__ . '/db_config.php';
    
    // Bağlantı oluşturuluyor.
    $baglanti = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);
    
    // Bağlanti kontrolü yapılır.
    if (!$baglanti) {
        die("Hatalı bağlantı : " . mysqli_connect_error());
    }
    
    $sqlsorgu = "INSERT INTO product_movement (user_id,product_id,date,movement_state) 
    VALUES ('$user_id','$product_id','$date','$movement_state')";

    
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



