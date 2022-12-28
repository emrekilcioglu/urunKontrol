package com.example.urunkontrol.employee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.urunkontrol.ProductInputActivity;
import com.example.urunkontrol.R;

public class EmployeeChoiceActivity extends AppCompatActivity {
    private Button buttonProductOut,buttonProductArrive;
    private Intent intentProductInput;
    private Intent intentMain;
    private String qrBarcode,userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_choice);
        buttonProductArrive = findViewById(R.id.buttonProductDelete);
        buttonProductOut = findViewById(R.id.buttonProductEdit);
        intentMain = getIntent();
        //intentden aldığımız veriler
        qrBarcode = intentMain.getStringExtra("qr_barcode");
        userId = intentMain.getStringExtra("user_id");

        //Seçim butonlarımız
        buttonProductOut.setOnClickListener(view -> {
            intentRouter("0");

        });
        buttonProductArrive.setOnClickListener(view -> {
            intentRouter("1");

        });


        // TODO: 27/11/2022 Listeleri göndermekten vazgeçtim direk qr code numarası atacam ilgili fragmnetlarda apiye bağlanırım
    }
    private void intentRouter(String movementState){
        intentProductInput = new Intent(this, ProductInputActivity.class);
        intentProductInput.putExtra("movement_state",movementState);
        intentProductInput.putExtra("qr_barcode",qrBarcode);
        intentProductInput.putExtra("user_id",userId);
        startActivity(intentProductInput);

    }
}