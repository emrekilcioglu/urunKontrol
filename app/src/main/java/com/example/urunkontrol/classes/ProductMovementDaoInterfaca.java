package com.example.urunkontrol.classes;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ProductMovementDaoInterfaca {
    @GET("urunKontrolApi/all_product_movement.php")
    Call<ProductMovementResponse> allProductMovement();

    @POST("urunKontrol/insert_product_movement.php")
    @FormUrlEncoded
//Türkçe karakter sıkıntısı olmaması için
    Call<CRUDResponse> insertProductMovement(@Field("product_id") String productId, @Field("user_id") String userId,@Field("date") String date,@Field("movement_state") String movementState, @Field("piece") String piece);//Bize kisilerCevap türünden veri döneceğini belirttik
    //Field ile hangi isimde veri göndereceğimizi,oluşturduğumuz string parametre ile de kolayca veriyi parametre olarak göndermemiz sağlandı

    @POST("urunKontrol/transaction_history.php")
    @FormUrlEncoded
//Türkçe karakter sıkıntısı olmaması için
    Call<ProductMovementResponse> transHistory(@Field("user_id") String userId);//Bize kisilerCevap türünden veri döneceğini belirttik
    //Field ile hangi isimde veri göndereceğimizi,oluşturduğumuz string parametre ile de kolayca veriyi parametre olarak göndermemiz sağlandı


}
