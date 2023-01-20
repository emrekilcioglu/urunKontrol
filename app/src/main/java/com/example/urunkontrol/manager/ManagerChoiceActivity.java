package com.example.urunkontrol.manager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.urunkontrol.R;
import com.example.urunkontrol.classes.ApiUtils;
import com.example.urunkontrol.classes.Product;
import com.example.urunkontrol.classes.ProductDaoInterface;
import com.example.urunkontrol.classes.ProductResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManagerChoiceActivity extends AppCompatActivity {
    private Button buttonProductEdit,buttonProductDelete;
    private TextView textViewProductName;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_choice);
        buttonProductDelete = findViewById(R.id.buttonProductDelete);
        buttonProductEdit = findViewById(R.id.buttonProductEdit);
        textViewProductName = findViewById(R.id.textViewProductName);
        Intent intentQr = getIntent();
        userId = intentQr.getStringExtra("user_id");
        Log.e("userid choice",userId);

        String productName = intentQr.getStringExtra("product_name");
        textViewProductName.setText(productName);
        buttonProductEdit.setOnClickListener(view -> {
            String qrBarcode = intentQr.getStringExtra("qr_barcode");
            Intent intentEdit = new Intent(ManagerChoiceActivity.this,ProductAddActivity.class);
            intentEdit.putExtra("router",1);
            intentEdit.putExtra("qr_or_barcode",qrBarcode);
            intentEdit.putExtra("user_id",userId);
            startActivity(intentEdit);
        });

        buttonProductDelete.setOnClickListener(view -> {
            AlertDialog.Builder ad =new AlertDialog.Builder(ManagerChoiceActivity.this);
            ad.setTitle("Ürünü Sil");
            ProductDaoInterface productDif = ApiUtils.getProductInterface();
            productDif.qrControl(intentQr.getStringExtra("qr_barcode")).enqueue(new Callback<ProductResponse>() {
                @Override
                public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                    Product product = response.body().getProduct().get(0);
                    ad.setMessage(product.getProductName()+" ürünü silinsin mi ?");//Veri burada
                    ad.setPositiveButton("Evet",(dialogInterface, i) -> {
                        ProductDaoInterface productDif= ApiUtils.getProductInterface();
                        productDif.deleteProduct(product.getProductId()).enqueue(new Callback<ProductResponse>() {
                            @Override
                            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                                finish();

                            }

                            @Override
                            public void onFailure(Call<ProductResponse> call, Throwable t) {

                            }
                        });
                        dialogInterface.dismiss();
                    });
                    ad.setNegativeButton("Hayır",(dialogInterface, i) -> {
                        dialogInterface.dismiss();
                    }).show();
                }

                @Override
                public void onFailure(Call<ProductResponse> call, Throwable t) {

                }
            });


        });




    }
}