package com.example.urunkontrol.classes;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BrandDaoInterface {
    @GET("urunKontrol/all_brand.php")
    Call<BrandResponse> allBrand();


}
