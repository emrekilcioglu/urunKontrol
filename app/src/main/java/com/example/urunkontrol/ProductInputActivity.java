package com.example.urunkontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.urunkontrol.employee.ProductPoolActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ProductInputActivity extends AppCompatActivity {
    private TextInputEditText editTextProductNum;
    private TextInputLayout textInputLayoutPro;
    private Button buttonOkInput;
    private Intent intentProductPool;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_input);
        buttonOkInput = findViewById(R.id.buttonOkInputOne);
        editTextProductNum = findViewById(R.id.editTextProductOne);
        textInputLayoutPro = findViewById(R.id.textInputLayoutOne);
        intentProductPool = new Intent(this, ProductPoolActivity.class);
        textInputLayoutPro.setHint("Hahahaha");
        buttonOkInput.setOnClickListener(view -> {
            startActivity(intentProductPool);
        });
    }
}