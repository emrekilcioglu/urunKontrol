
package com.example.urunkontrol.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stock {


    @SerializedName("general_stock")
    @Expose
    private String generalStock;

    @SerializedName("danger_stock")
    @Expose
    private String dangerStock;

    @SerializedName("empty_stock")
    @Expose
    private String emptyStock;

    public String getGeneralStock() {
        return generalStock;
    }

    public void setGeneralStock(String generalStock) {
        this.generalStock = generalStock;
    }

    public String getDangerStock() {
        return dangerStock;
    }

    public void setDangerStock(String dangerStock) {
        this.dangerStock = dangerStock;
    }

    public String getEmptyStock() {
        return emptyStock;
    }

    public void setEmptyStock(String emptyStock) {
        this.emptyStock = emptyStock;
    }

}
