
package com.example.urunkontrol.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("unit_weight")
    @Expose
    private String unitWeight;
    @SerializedName("barcode_or_qr")
    @Expose
    private String barcodeOrQr;
    @SerializedName("category")
    @Expose
    private Category category;
    @SerializedName("brand")
    @Expose
    private Brand brand;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUnitWeight() {
        return unitWeight;
    }

    public void setUnitWeight(String unitWeight) {
        this.unitWeight = unitWeight;
    }

    public String getBarcodeOrQr() {
        return barcodeOrQr;
    }

    public void setBarcodeOrQr(String barcodeOrQr) {
        this.barcodeOrQr = barcodeOrQr;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

}
