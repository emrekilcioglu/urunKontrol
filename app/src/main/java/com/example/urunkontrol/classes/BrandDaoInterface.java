package com.example.urunkontrol.classes;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface BrandDaoInterface {
    @GET("urunKontrol/all_brand.php")
    Call<BrandResponse> allBrand();

    @POST("urunKontrol/search_brand.php")
    @FormUrlEncoded
//Türkçe karakter sıkıntısı olmaması için
    Call<BrandResponse> searchBrand(@Field("brand_name") String brandName);//Bize kisilerCevap türünden veri döneceğini belirttik
    //Field ile hangi isimde veri göndereceğimizi,oluşturduğumuz string parametre ile de kolayca veriyi parametre olarak göndermemiz sağlandı



}
