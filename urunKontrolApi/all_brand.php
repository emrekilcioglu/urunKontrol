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
    
$sqlsorgu = "CALL sp_all_brand()";//Proced
$result = mysqli_query($baglanti,$sqlsorgu);

// result kontrolü yap
if (mysqli_num_rows($result) > 0) {
    
    $response["brand"] = array();
    
    while ($row = mysqli_fetch_assoc($result)) {
        // temp user array
        $brand = array();
        
        $brand["brand_id"] = $row["brand_id"];
        $brand["brand_name"] = $row["brand_name"];
        
        
        
        // push single product into final response array
        array_push($response["brand"], $brand);
    }
    // success
    $response["success"] = 1;

    // echoing JSON response
    echo json_encode($response);
    
} else {
    // no products found
    $response["success"] = 0;
    $response["message"] = "No data found";

    // echo no users JSON
    echo json_encode($response,JSON_UNESCAPED_UNICODE);
}
//bağlantı koparılır.
mysqli_close($baglanti);
?>
