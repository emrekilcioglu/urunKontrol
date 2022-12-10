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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_choice);
        buttonProductArrive = findViewById(R.id.buttonProductArrive);
        buttonProductOut = findViewById(R.id.buttonProductOut);
        intentProductInput = new Intent(this, ProductInputActivity.class);
        //List<ProductResponse> productResponseList = getIntent().getSerializableExtra("product_list");
        //Log.e("List",)
        buttonProductOut.setOnClickListener(view -> {
            startActivity(intentProductInput);

        });
        // TODO: 27/11/2022 Listeleri göndermekten vazgeçtim direk qr code numarası atacam ilgili fragmnetlarda apiye bağlanırım 
    }
}