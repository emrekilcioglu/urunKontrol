package com.example.urunkontrol.classes;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ProductDaoInterface {
    @GET("urunKontrol/all_product.php")
    Call<ProductResponse> allProduct();


    @POST("urunKontrol/search_qr_barcode.php")
    @FormUrlEncoded
//Türkçe karakter sıkıntısı olmaması için
    Call<ProductResponse> qrControl(@Field("barcode_or_qr") String barcodeQr);//Bize kisilerCevap türünden veri döneceğini belirttik
    //Field ile hangi isimde veri göndereceğimizi,oluşturduğumuz string parametre ile de kolayca veriyi parametre olarak göndermemiz sağlandı


}
