package com.example.urunkontrol.classes;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface BrandDaoInterface {
    @POST("urunKontrol/all_brand.php")
    Call<BrandResponse> allBrand();

    @POST("urunKontrol/search_brand.php")
    @FormUrlEncoded
//Türkçe karakter sıkıntısı olmaması için
    Call<BrandResponse> searchBrand(@Field("brand_name") String brandName);//Bize kisilerCevap türünden veri döneceğini belirttik
    //Field ile hangi isimde veri göndereceğimizi,oluşturduğumuz string parametre ile de kolayca veriyi parametre olarak göndermemiz sağlandı

    @POST("urunKontrol/insert_brand.php")
    @FormUrlEncoded
//Türkçe karakter sıkıntısı olmaması için
    Call<CRUDResponse> insertBrand(@Field("brand_name") String brandName);//Bize kisilerCevap türünden veri döneceğini belirttik
    //Field ile hangi isimde veri göndereceğimizi,oluşturduğumuz string parametre ile de kolayca veriyi parametre olarak göndermemiz sağlandı

    @POST("urunKontrol/update_brand.php")
    @FormUrlEncoded
//Türkçe karakter sıkıntısı olmaması için
    Call<CRUDResponse> updateBrand(@Field("brand_id") String brandId,@Field("brand_name") String brandName);//Bize kisilerCevap türünden veri döneceğini belirttik
    //Field ile hangi isimde veri göndereceğimizi,oluşturduğumuz string parametre ile de kolayca veriyi parametre olarak göndermemiz sağlandı

    @POST("urunKontrol/delete_brand.php")
    @FormUrlEncoded
//Türkçe karakter sıkıntısı olmaması için
    Call<CRUDResponse> deleteBrand(@Field("brand_id") String brandId);//Bize kisilerCevap türünden veri döneceğini belirttik
    //Field ile hangi isimde veri göndereceğimizi,oluşturduğumuz string parametre ile de kolayca veriyi parametre olarak göndermemiz sağlandı

}
