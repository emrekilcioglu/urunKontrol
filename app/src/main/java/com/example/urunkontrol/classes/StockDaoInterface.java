package com.example.urunkontrol.classes;

import retrofit2.Call;
import retrofit2.http.GET;

public interface StockDaoInterface {
    @GET("urunKontrolApi/all_stock")
    Call<StockResponse> allStock();
}
