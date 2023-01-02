package com.example.urunkontrol;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.urunkontrol.classes.ApiUtils;
import com.example.urunkontrol.classes.CRUDResponse;
import com.example.urunkontrol.classes.CaptureAct;
import com.example.urunkontrol.classes.Product;
import com.example.urunkontrol.classes.ProductDaoInterface;
import com.example.urunkontrol.classes.ProductMovementDaoInterfaca;
import com.example.urunkontrol.classes.ProductResponse;
import com.example.urunkontrol.employee.EmployeeMainPageActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductInputActivity extends AppCompatActivity {
    private TextInputEditText editTextProductNum;
    private TextInputLayout textInputLayoutPro;
    private TextView textViewProductNam;
    private Button buttonOkInput,buttonContiuneInput;
    private Intent intentChoice;
    private Product product;
    private String userId,movementState,qrBarcode;
    private ProductDaoInterface productDif;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_input);
        buttonOkInput = findViewById(R.id.buttonOkInputOne);
        buttonContiuneInput = findViewById(R.id.buttonContiuneInput);
        editTextProductNum = findViewById(R.id.editTextAddOne);
        textInputLayoutPro = findViewById(R.id.textInputLayoutOne);
        textViewProductNam = findViewById(R.id.textViewProductNam);
        productDif = ApiUtils.getProductInterface();

        //intentden aldıklarımız
        intentChoice = getIntent();
        userId = intentChoice.getStringExtra("user_id");
        movementState = intentChoice.getStringExtra("movement_state");
        qrBarcode = intentChoice.getStringExtra("qr_barcode");// hatta var
        getProductInf(productDif,qrBarcode);




        //textInputLayoutPro.setHint("Hahahaha");
        buttonOkInput.setOnClickListener(view -> {
            String productId = product.getProductId();
            String piece = editTextProductNum.getText().toString();
            insertMove(productId,userId,piece);
            Intent mainIntet = new Intent(ProductInputActivity.this, EmployeeMainPageActivity.class);
            mainIntet.putExtra("user_id",userId);
            startActivity(mainIntet);
            finish();

        });

        buttonContiuneInput.setOnClickListener(view -> {
            String productId = product.getProductId();//id aldık
            String piece = editTextProductNum.getText().toString();//sayıyı aldık
            scanCode();
            insertMove(productId,userId,piece);//Bunu qr tarama içine de alacağız


        });
    }

    private void insertMove(String productId,String userId,String piece){
        //ProdcutMovementİnsert metodu
        //Tarih anlık alınsın diye hhtp atan metod içinde
        Date currentTime = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//tarih tamam
        String date = df.format(currentTime);

        ProductMovementDaoInterfaca productMovementDif = ApiUtils.getProductMovementInterface();
        productMovementDif.insertProductMovement(productId,userId,date,movementState,piece).enqueue(new Callback<CRUDResponse>() {
            @Override
            public void onResponse(Call<CRUDResponse> call, Response<CRUDResponse> response) {

            }

            @Override
            public void onFailure(Call<CRUDResponse> call, Throwable t) {

            }
        });

    }

    private void getProductInf(ProductDaoInterface productDif,String qrBarcode){
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


    }

    private void scanCode() {//Burada qr okuyucuyla alakalı ayarları yapıyoruz
        ScanOptions options =new ScanOptions();
        options.setPrompt(getString(R.string.flash_info));//prompt bilgi demek
        options.setBeepEnabled(true);//Beep ses manasında
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);

        barLauncher.launch(options);

    }
    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(),result -> {
        qrBarcode = result.getContents();
        getProductInf(productDif,qrBarcode);

    });
}