package com.example.urunkontrol.classes;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ProductDaoInterface {
    @GET("urunKontrol/all_product.php")
    Call<ProductResponse> allProduct();

    @GET("urunKontrol/all_product_danger.php")
    Call<ProductResponse> allProductDanger();


    @POST("urunKontrol/search_qr_barcode.php")
    @FormUrlEncoded
//Türkçe karakter sıkıntısı olmaması için
    Call<ProductResponse> qrControl(@Field("barcode_or_qr") String barcodeQr);//Bize kisilerCevap türünden veri döneceğini belirttik
    //Field ile hangi isimde veri göndereceğimizi,oluşturduğumuz string parametre ile de kolayca veriyi parametre olarak göndermemiz sağlandı

    @POST("urunKontrol/insert_product.php")
    @FormUrlEncoded
//Türkçe karakter sıkıntısı olmaması için
    Call<CRUDResponse> insertProduct(@Field("barcode_or_qr") String barcodeQr, @Field("product_name") String productName, @Field("brand_name") String brandName,@Field("category_name") String categoryName,@Field("price") String price, @Field("unit_weight") String unitWeight,@Field("max_stock_level") String maxStockLevel, @Field("stock_danger_level") String stockDangerLevel);//Bize kisilerCevap türünden veri döneceğini belirttik
    //Field ile hangi isimde veri göndereceğimizi,oluşturduğumuz string parametre ile de kolayca veriyi parametre olarak göndermemiz sağlandı

    @POST("urunKontrol/update_product.php")
    @FormUrlEncoded
//Türkçe karakter sıkıntısı olmaması için
    Call<CRUDResponse> updateProduct(@Field("barcode_or_qr") String barcodeQr, @Field("product_name") String productName, @Field("brand_name") String brandName,@Field("category_name") String categoryName,@Field("price") String price, @Field("unit_weight") String unitWeight,@Field("max_stock_level") String maxStockLevel, @Field("stock_danger_level") String stockDangerLevel);//Bize kisilerCevap türünden veri döneceğini belirttik
    //Field ile hangi isimde veri göndereceğimizi,oluşturduğumuz string parametre ile de kolayca veriyi parametre olarak göndermemiz sağlandı

    @POST("urunKontrol/get_product.php")
    @FormUrlEncoded
//Türkçe karakter sıkıntısı olmaması için
    Call<ProductResponse> getProduct(@Field("product_id") String productId);//Bize kisilerCevap türünden veri döneceğini belirttik
    //Field ile hangi isimde veri göndereceğimizi,oluşturduğumuz string parametre ile de kolayca veriyi parametre olarak göndermemiz sağlandı

    @POST("urunKontrol/delete_product.php")
    @FormUrlEncoded
//Türkçe karakter sıkıntısı olmaması için
    Call<ProductResponse> deleteProduct(@Field("product_id") String productId);//Bize kisilerCevap türünden veri döneceğini belirttik
    //Field ile hangi isimde veri göndereceğimizi,oluşturduğumuz string parametre ile de kolayca veriyi parametre olarak göndermemiz sağlandı


}
