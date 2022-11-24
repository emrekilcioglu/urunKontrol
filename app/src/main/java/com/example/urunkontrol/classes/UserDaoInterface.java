package com.example.urunkontrol.classes;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserDaoInterface {
    @GET("urunKontrol/all_user.php")
    Call<UserResponse> allUser();
}
