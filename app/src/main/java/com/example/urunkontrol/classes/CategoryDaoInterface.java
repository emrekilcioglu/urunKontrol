package com.example.urunkontrol.classes;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CategoryDaoInterface {
    @GET("urunKontrol/all_category.php")
    Call<CategoryResponse> allCategory();

    @POST("urunKontrol/insert_category.php")
    @FormUrlEncoded
//Türkçe karakter sıkıntısı olmaması için
    Call<CRUDResponse> insertCategory(@Field("category_name") String categoryName);//Bize kisilerCevap türünden veri döneceğini belirttik
    //Field ile hangi isimde veri göndereceğimizi,oluşturduğumuz string parametre ile de kolayca veriyi parametre olarak göndermemiz sağlandı

    @POST("urunKontrol/delete_category.php")
    @FormUrlEncoded
//Türkçe karakter sıkıntısı olmaması için
    Call<CRUDResponse> deleteCategory(@Field("category_id") String categoryId);//Bize kisilerCevap türünden veri döneceğini belirttik
    //Field ile hangi isimde veri göndereceğimizi,oluşturduğumuz string parametre ile de kolayca veriyi parametre olarak göndermemiz sağlandı

    @POST("urunKontrol/update_category.php")
    @FormUrlEncoded
//Türkçe karakter sıkıntısı olmaması için
    Call<CRUDResponse> updateCategory(@Field("category_id") String categoryId,@Field("category_name") String categoryName);//Bize kisilerCevap türünden veri döneceğini belirttik
    //Field ile hangi isimde veri göndereceğimizi,oluşturduğumuz string parametre ile de kolayca veriyi parametre olarak göndermemiz sağlandı

}
