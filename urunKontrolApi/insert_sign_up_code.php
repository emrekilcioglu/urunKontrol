<?php

$response = array();

if (isset($_POST['code'])) {
    $code = $_POST['code'];
    
   
    //DB_SERVER,DB_USER,DB_PASSWORD,DB_DATABASE değişkenleri alınır.
    require_once __DIR__ . '/db_config.php';
    
    // Bağlantı oluşturuluyor.
    $baglanti = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);
    mysqli_set_charset($baglanti,"utf8mb4");

    
    // Bağlanti kontrolü yapılır.
    if (!$baglanti) {
        die("Hatalı bağlantı : " . mysqli_connect_error());
    }
    
    $sqlsorgu = "INSERT INTO sign_up_code (code) VALUES ('$code')";

    try {
        if (mysqli_query($baglanti, $sqlsorgu)) {
        
            $response["success"] = 1;
            $response["message"] = "successfully ";
    
            echo json_encode($response);
        } else {
        
            $response["success"] = 0;
            $response["message"] = "No product found";
    
            echo json_encode($response);
        }
        //code...
    } catch (\Throwable $th) {
        echo $th;
    }
    
    //bağlantı koparılır.
    mysqli_close($baglanti);
} else {

    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

    echo json_encode($response);
}
    ?>



