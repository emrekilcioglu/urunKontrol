<?php

    $response = array();//bize gelen cevabı bu diziye aktaracağız

    if (isset($_POST['user_id'])) {
        $user_id = $_POST['user_id'];//bu değişkeni arama için kullanacağız
       


   
        //DB_SERVER,DB_USER,DB_PASSWORD,DB_DATABASE değişkenleri alınır.
        require_once __DIR__ . '/db_config.php';
        
        // Bağlantı oluşturuluyor.
        $baglanti = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);
	    mysqli_set_charset($baglanti,"utf8mb4");

        
        // Bağlanti kontrolü yapılır.
        if (!$baglanti) {
            die("Hatalı bağlantı : " . mysqli_connect_error());
        }
        
        $sqlsorgu = "SELECT * FROM user WHERE user.user_id = '$user_id'";
        
        $result = mysqli_query($baglanti, $sqlsorgu);
        
        if (mysqli_num_rows($result) > 0) {

            $response["user"] = array();
            
            while ($row = mysqli_fetch_assoc($result)) {
       
                $user = array();
                
                
                $user["job_status"] = $row["job_status"];
                $user["name"] = $row["name"];

                //$user["dene"] = true;
                

                array_push($response["user"], $user);
            }
      
            $response["success"] = 1;
            
            
            echo json_encode($response);
            
        }
        else{
            $response["success"] = 2;
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




