<?php

$response = array();


if (isset($_POST['product_id']) && isset($_POST['user_id'])&& isset($_POST['date'])
&& isset($_POST['movement_state']) && isset($_POST['piece'])) {
    $product_id = $_POST['product_id'];
    $user_id = $_POST['user_id'];
    $date = $_POST['date'];
    $movement_state = $_POST['movement_state'];
    $piece = $_POST['piece'];
    



   
    //DB_SERVER,DB_USER,DB_PASSWORD,DB_DATABASE değişkenleri alınır.
    require_once __DIR__ . '/db_config.php';
    
    // Bağlantı oluşturuluyor.
    $baglanti = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);
    mysqli_set_charset($baglanti,"utf8mb4");

    
    // Bağlanti kontrolü yapılır.
    if (!$baglanti) {
        die("Hatalı bağlantı : " . mysqli_connect_error());
    }
    /*switch ($movement_state) {
        case '0':
            $sqlsorgu2 = "UPDATE product SET stock_level = stock_level - $piece WHERE product_id = '$product_id'";

            
            break;
        case '1':
            $sqlsorgu2 = "UPDATE product SET stock_level = stock_level + $piece WHERE product_id = '$product_id'";
            break;
        
        default:
            # code...
            break;
    }*/
    
    $sqlsorgu = "CALL insertProductMovement('$user_id','$product_id','$date','$movement_state','$piece')";

    
    if (mysqli_query($baglanti, $sqlsorgu)) {
        //mysqli_query($baglanti, $sqlsorgu2);
        
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



