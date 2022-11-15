
package com.example.urunkontrol.classes;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BrandResponse {

    @SerializedName("brand")
    @Expose
    private List<Brand> brand = null;
    @SerializedName("success")
    @Expose
    private Integer success;

    public List<Brand> getBrand() {
        return brand;
    }

    public void setBrand(List<Brand> brand) {
        this.brand = brand;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

}
