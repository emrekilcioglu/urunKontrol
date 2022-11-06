package com.example.urunkontrol;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.urunkontrol.employee.EmployeeMainPageActivity;
import com.example.urunkontrol.manager.ManagerMainPageActivity;
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
        intent =  new Intent(MainActivity.this, EmployeeMainPageActivity.class);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onClick(View view) {
                if (editTextName.getText().toString().equals("admin")){
                    startActivity(new Intent(MainActivity.this, ManagerMainPageActivity.class));
                }
                else
                startActivity(intent);
            }
        });
    }
}