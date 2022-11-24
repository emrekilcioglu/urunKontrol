package com.example.urunkontrol.classes;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductDaoInterface {
    @GET("urunKontrol/all_product.php")
    Call<ProductResponse> allProduct();
}
