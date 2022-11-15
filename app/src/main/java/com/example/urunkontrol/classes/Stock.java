
package com.example.urunkontrol.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stock {

    @SerializedName("stock_id")
    @Expose
    private String stockId;
    @SerializedName("product")
    @Expose
    private Product product;
    @SerializedName("max_stock_level")
    @Expose
    private String maxStockLevel;
    @SerializedName("stock_level")
    @Expose
    private String stockLevel;

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getMaxStockLevel() {
        return maxStockLevel;
    }

    public void setMaxStockLevel(String maxStockLevel) {
        this.maxStockLevel = maxStockLevel;
    }

    public String getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(String stockLevel) {
        this.stockLevel = stockLevel;
    }

}
