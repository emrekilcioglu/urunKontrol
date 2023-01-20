<?php

    $response = array();//bize gelen cevabı bu diziye aktaracağız

    if (isset($_POST['category_name'])) {
        $category_name = $_POST['category_name'];
   
        //DB_SERVER,DB_USER,DB_PASSWORD,DB_DATABASE değişkenleri alınır.
        require_once __DIR__ . '/db_config.php';
        
        // Bağlantı oluşturuluyor.
        $baglanti = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);
        
        // Bağlanti kontrolü yapılır.
        if (!$baglanti) {
            die("Hatalı bağlantı : " . mysqli_connect_error());
        }
        
        $sqlsorgu = "SELECT * FROM category WHERE category.category_name like '%$category_name%'";
        $result = mysqli_query($baglanti, $sqlsorgu);
        
        if (mysqli_num_rows($result) > 0) {

            $response["category"] = array();
            
            while ($row = mysqli_fetch_assoc($result)) {
       
                $category = array();
                
                $category["category_id"] = $row["category_id"];
                $category["category_name"] = $row["category_name"];

                array_push($response["category"], $category);
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




