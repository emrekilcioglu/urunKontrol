<?php

    $response = array();//bize gelen cevabı bu diziye aktaracağız

    if (isset($_POST['brand_name'])) {
        $brand_name = $_POST['brand_name'];//bu değişkeni arama için kullanacağız
   
        //DB_SERVER,DB_USER,DB_PASSWORD,DB_DATABASE değişkenleri alınır.
        require_once __DIR__ . '/db_config.php';
        
        // Bağlantı oluşturuluyor.
        $baglanti = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);
        
        // Bağlanti kontrolü yapılır.
        if (!$baglanti) {
            die("Hatalı bağlantı : " . mysqli_connect_error());
        }
        
        $sqlsorgu = "SELECT * FROM brand WHERE brand.brand_name like '%$brand_name%'";
        $result = mysqli_query($baglanti, $sqlsorgu);
        
        if (mysqli_num_rows($result) > 0) {

            $response["brand"] = array();
            
            while ($row = mysqli_fetch_assoc($result)) {
       
                $brand = array();
                
                $brand["brand_id"] = $row["brand_id"];
                $brand["brand_name"] = $row["brand_name"];

                array_push($response["brand"], $brand);
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




