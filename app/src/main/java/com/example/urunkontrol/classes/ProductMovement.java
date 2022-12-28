
package com.example.urunkontrol.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductMovement {

    @SerializedName("movement_id")
    @Expose
    private String movementId;
    @SerializedName("product")
    @Expose
    private Product product;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("movement_state")
    @Expose
    private String movementState;
    @SerializedName("piece")
    @Expose
    private String piece;

    public String getMovementId() {
        return movementId;
    }

    public void setMovementId(String movementId) {
        this.movementId = movementId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMovementState() {
        return movementState;
    }

    public void setMovementState(String movementState) {
        this.movementState = movementState;
    }

    public String getPiece() {
        return piece;
    }

    public void setPiece(String piece) {
        this.piece = piece;
    }

}
