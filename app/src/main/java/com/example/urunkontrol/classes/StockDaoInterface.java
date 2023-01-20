package com.example.urunkontrol.classes;

import retrofit2.Call;
import retrofit2.http.GET;

public interface StockDaoInterface {
    @GET("urunKontrol/general_stock_control.php")
    Call<StockResponse> allStock();
}
