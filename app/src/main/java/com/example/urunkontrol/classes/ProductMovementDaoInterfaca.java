package com.example.urunkontrol.classes;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductMovementDaoInterfaca {
    @GET("urunKontrolApi/all_product_movement.php")
    Call<ProductMovementResponse> allProductMovement();
}
