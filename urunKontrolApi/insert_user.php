<?php

$response = array();

if (isset($_POST['name']) && isset($_POST['user_name'])&& isset($_POST['password'])
&& isset($_POST['login_status'])&& isset($_POST['tc_no'])) {
    $name = $_POST['name'];
    $user_name = $_POST['user_name'];
    $password = $_POST['password'];
    $login_status = $_POST['login_status'];
    $tc_no = $_POST['tc_no'];



   
    //DB_SERVER,DB_USER,DB_PASSWORD,DB_DATABASE değişkenleri alınır.
    require_once __DIR__ . '/db_config.php';
    
    // Bağlantı oluşturuluyor.
    $baglanti = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);
    
    // Bağlanti kontrolü yapılır.
    if (!$baglanti) {
        die("Hatalı bağlantı : " . mysqli_connect_error());
    }
    
    $sqlsorgu = "INSERT INTO user (user_name,name,password,login_status,tc_no) 
    VALUES ('$user_name','$name','$password','$login_status','$tc_no')";

    
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



