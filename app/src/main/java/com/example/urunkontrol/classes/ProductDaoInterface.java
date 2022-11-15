package com.example.urunkontrol.classes;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductDaoInterface {
    @GET("urunKontrolApi/all_product")
    Call<BrandResponse> allProduct();
}
