
package com.example.urunkontrol.classes;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductMovementResponse {

    @SerializedName("product_movement")
    @Expose
    private List<ProductMovement> productMovement = null;
    @SerializedName("success")
    @Expose
    private Integer success;

    public List<ProductMovement> getProductMovement() {
        return productMovement;
    }

    public void setProductMovement(List<ProductMovement> productMovement) {
        this.productMovement = productMovement;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

}
