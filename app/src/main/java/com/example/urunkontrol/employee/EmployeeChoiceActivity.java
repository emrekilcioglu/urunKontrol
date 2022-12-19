package com.example.urunkontrol.employee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.urunkontrol.ProductInputActivity;
import com.example.urunkontrol.R;
import com.example.urunkontrol.classes.ProductResponse;

import java.util.List;

public class EmployeeChoiceActivity extends AppCompatActivity {
    private Button buttonProductOut,buttonProductArrive;
    private Intent intentProductInput;
    private Intent intentMain;
    private String qrBarcode,userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_choice);
        buttonProductArrive = findViewById(R.id.buttonProductArrive);
        buttonProductOut = findViewById(R.id.buttonProductOut);
        //List<ProductResponse> productResponseList = getIntent().getSerializableExtra("product_list");
        //Log.e("List",)
        intentMain = getIntent();
        qrBarcode = intentMain.getStringExtra("qr_barcode");
        userId = intentMain.getStringExtra("user_id");
        buttonProductOut.setOnClickListener(view -> {
            intentProductInput = new Intent(this, ProductInputActivity.class);
            intentProductInput.putExtra("movement_state","0");
            intentProductInput.putExtra("qr_barcode",qrBarcode);
            intentProductInput.putExtra("user_id",userId);
            // TODO: 18/12/2022 product movement insert başarıyla tamamlandı 

            startActivity(intentProductInput);

        });
        buttonProductArrive.setOnClickListener(view -> {
            intentProductInput = new Intent(this, ProductInputActivity.class);
            intentProductInput.putExtra("movement_state","1");
            intentProductInput.putExtra("qr_barcode",qrBarcode);
            intentProductInput.putExtra("user_id",userId);
            startActivity(intentProductInput);

        });


        // TODO: 27/11/2022 Listeleri göndermekten vazgeçtim direk qr code numarası atacam ilgili fragmnetlarda apiye bağlanırım
    }
}