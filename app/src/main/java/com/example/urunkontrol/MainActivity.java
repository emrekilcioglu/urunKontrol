package com.example.urunkontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.urunkontrol.employee.EmployeeMainPageActivity;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    private Button buttonLogin;
    private TextInputEditText editTextName,editTextPassword;
    private Intent intent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonLogin = findViewById(R.id.buttonLogin);
        editTextName = findViewById(R.id.edtitTextName);
        editTextPassword = findViewById(R.id.editTextPassword);
        intent = new Intent(MainActivity.this, EmployeeMainPageActivity.class);
        buttonLogin.setOnClickListener(view -> {
            startActivity(intent);

        });
    }
}