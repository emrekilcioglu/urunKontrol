package com.example.urunkontrol.classes;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserDaoInterface {
    @GET("urunKontrol/all_user.php")
    Call<UserResponse> allUser();

    @POST("urunKontrol/user_control.php")
    @FormUrlEncoded
//Türkçe karakter sıkıntısı olmaması için
    Call<UserResponse> userControl(@Field("user_name") String userName,@Field("password") String password);//Bize kisilerCevap türünden veri döneceğini belirttik
    //Field ile hangi isimde veri göndereceğimizi,oluşturduğumuz string parametre ile de kolayca veriyi parametre olarak göndermemiz sağlandı

    @POST("urunKontrol/user_close.php")
    @FormUrlEncoded
    Call<CRUDResponse> closeUser(@Field("user_id") String userId);

    @POST("urunKontrol/search_user.php")
    @FormUrlEncoded
    Call<UserResponse> searchUser(@Field("user_id") String userId);

    @POST("urunKontrol/get_user.php")
    @FormUrlEncoded
    Call<UserResponse> getUser(@Field("user_id") String userId);

    @POST("urunKontrol/delete_user.php")
    @FormUrlEncoded
    Call<UserResponse> deleteUser(@Field("user_id") String userId);

    @POST("urunKontrol/insert_user.php")
    @FormUrlEncoded
//Türkçe karakter sıkıntısı olmaması için
    Call<UserResponse> insertUser(@Field("user_name") String userName,@Field("password") String password,@Field("name") String name,@Field("tc_no") String tcNo,@Field("job_status") String job_status);//Bize kisilerCevap türünden veri döneceğini belirttik
    //Field ile hangi isimde veri göndereceğimizi,oluşturduğumuz string parametre ile de kolayca veriyi parametre olarak göndermemiz sağlandı

    @POST("urunKontrol/update_user.php")
    @FormUrlEncoded
//Türkçe karakter sıkıntısı olmaması için
    Call<CRUDResponse> updateUser(@Field("user_name") String userName,@Field("password") String password,@Field("name") String name,@Field("tc_no") String tcNo,@Field("job_status") String job_status,@Field("user_id") String userId);//Bize kisilerCevap türünden veri döneceğini belirttik
    //Field ile hangi isimde veri göndereceğimizi,oluşturduğumuz string parametre ile de kolayca veriyi parametre olarak göndermemiz sağlandı


}
