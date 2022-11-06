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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_choice);
        buttonProductArrive = findViewById(R.id.buttonProductArrive);
        buttonProductOut = findViewById(R.id.buttonProductOut);
        intentProductInput = new Intent(this, ProductInputActivity.class);
        buttonProductOut.setOnClickListener(view -> {
            startActivity(intentProductInput);

        });
    }
}