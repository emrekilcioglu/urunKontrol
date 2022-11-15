package com.example.urunkontrol.classes;

public class ApiUtils {
    public static final String BASE_URL ="https://emrekilcioglu.com.tr/";//Apileri çektiğimiz sunucumuzun ana adresini giriyoru<
    public static BrandDaoInterface getBrandDaoInterface(){
        return RetrofitClient.getClient(BASE_URL).create(BrandDaoInterface.class);
        //Ana url yi cliente girdik
    }
    public static CategoryDaoInterface getCategoryInterface(){
        return RetrofitClient.getClient(BASE_URL).create(CategoryDaoInterface.class);
        //Ana url yi cliente girdik
    }
}
