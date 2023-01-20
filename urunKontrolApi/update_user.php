<?php

$response = array();

if (isset($_POST['name']) && isset($_POST['user_name'])&& isset($_POST['password'])
&& isset($_POST['job_status'])&& isset($_POST['tc_no'])&& isset($_POST['user_id'])) {
    $name = $_POST['name'];
    $user_name = $_POST['user_name'];
    $password = $_POST['password'];
    $job_status = $_POST['job_status'];
    $tc_no = $_POST['tc_no'];
    $user_id = $_POST['user_id'];

    if($job_status == "İşçi"){
        $job_status = 0;
        
    }elseif($job_status == "Yönetici"){
        $job_status = 1;
    }



   
    //DB_SERVER,DB_USER,DB_PASSWORD,DB_DATABASE değişkenleri alınır.
    require_once __DIR__ . '/db_config.php';
    
    // Bağlantı oluşturuluyor.
    $baglanti = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);
	mysqli_set_charset($baglanti,"utf8mb4");

    
    // Bağlanti kontrolü yapılır.
    if (!$baglanti) {
        die("Hatalı bağlantı : " . mysqli_connect_error());
    }
    
    $sqlsorgu ="CALL sp_update_user('$name','$user_name','$password','$tc_no','$job_status','$user_id')";

    
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



