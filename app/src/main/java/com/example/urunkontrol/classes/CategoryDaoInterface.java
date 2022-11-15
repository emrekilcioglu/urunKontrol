package com.example.urunkontrol.classes;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryDaoInterface {
    @GET("urunKontrol/all_category.php")
    Call<CategoryResponse> allCategory();
}
