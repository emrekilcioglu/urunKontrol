<?php
// array for JSON response
$response = array();

//DB_SERVER,DB_USER,DB_PASSWORD,DB_DATABASE değişkenleri alınır.
require_once __DIR__ . '/db_config.php';

// Bağlantı oluşturuluyor.
$baglanti = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);
    
// Bağlanti kontrolü yapılır.
if (!$baglanti) {
    die("Hatalı bağlantı : " . mysqli_connect_error());
}
    
$sqlsorgu = "SELECT * FROM category";
$result = mysqli_query($baglanti, $sqlsorgu);

// result kontrolü yap
if (mysqli_num_rows($result) > 0) {
    
    $response["category"] = array();
    
    while ($row = mysqli_fetch_assoc($result)) {
        // temp user array
        $category = array();
        
        $category["category_id"] = $row["category_id"];
        $category["category_name"] = $row["category_name"];
        
        
        // push single product into final response array
        array_push($response["category"], $category);
    }
    // success
    $response["success"] = 1;

    // echoing JSON response
    echo json_encode($response,JSON_UNESCAPED_UNICODE);
    
} else {
    // no products found
    $response["success"] = 0;
    $response["message"] = "No data found";

    // echo no users JSON
    echo json_encode($response);
}
//bağlantı koparılır.
mysqli_close($baglanti);
?>
