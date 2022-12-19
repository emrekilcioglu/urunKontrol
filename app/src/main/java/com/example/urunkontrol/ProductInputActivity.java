package com.example.urunkontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.urunkontrol.classes.ApiUtils;
import com.example.urunkontrol.classes.CRUDResponse;
import com.example.urunkontrol.classes.Product;
import com.example.urunkontrol.classes.ProductDaoInterface;
import com.example.urunkontrol.classes.ProductMovementDaoInterfaca;
import com.example.urunkontrol.classes.ProductResponse;
import com.example.urunkontrol.classes.UserDaoInterface;
import com.example.urunkontrol.employee.ProductPoolActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductInputActivity extends AppCompatActivity {
    private TextInputEditText editTextProductNum;
    private TextInputLayout textInputLayoutPro;
    private TextView textViewProductNam;
    private Button buttonOkInput;
    private Intent intentChoice;
    private Product product;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_input);
        buttonOkInput = findViewById(R.id.buttonOkInputOne);
        editTextProductNum = findViewById(R.id.editTextProductOne);
        textInputLayoutPro = findViewById(R.id.textInputLayoutOne);
        textViewProductNam = findViewById(R.id.textViewProductNam);
        ProductDaoInterface productDif = ApiUtils.getProductInterface();
        intentChoice = getIntent();

        String qrBarcode = intentChoice.getStringExtra("qr_barcode");// hatta var

        productDif.qrControl(qrBarcode).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                product = response.body().getProduct().get(0);
                textViewProductNam.setText(product.getProductName());

            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {

            }
        });


        textInputLayoutPro.setHint("Hahahaha");
        buttonOkInput.setOnClickListener(view -> {
            String productId = product.getProductId();
            String userId = intentChoice.getStringExtra("user_id");
            String movementState = intentChoice.getStringExtra("movement_state");
            Log.e("Veriler",userId +" -- " + movementState);
            Date currentTime = new Date();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//tarih tamam
            String date = df.format(currentTime);
            String piece = editTextProductNum.getText().toString();





            ProductMovementDaoInterfaca productMovementDif = ApiUtils.getProductMovementInterface();
            productMovementDif.insertProductMovement(productId,userId,date,movementState,piece).enqueue(new Callback<CRUDResponse>() {
                @Override
                public void onResponse(Call<CRUDResponse> call, Response<CRUDResponse> response) {

                }

                @Override
                public void onFailure(Call<CRUDResponse> call, Throwable t) {

                }
            });




        });
    }
}