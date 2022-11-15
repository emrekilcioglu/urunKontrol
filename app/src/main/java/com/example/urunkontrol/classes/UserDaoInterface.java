package com.example.urunkontrol.classes;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserDaoInterface {
    @GET("urunKontrolApi/all_user")
    Call<BrandResponse> allUser();
}
