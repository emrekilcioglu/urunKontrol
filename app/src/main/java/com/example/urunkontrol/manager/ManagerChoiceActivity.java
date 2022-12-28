package com.example.urunkontrol.manager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.urunkontrol.R;

public class ManagerChoiceActivity extends AppCompatActivity {
    private Button buttonProductEdit,buttonProductDelete;
    private TextView textViewProductName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_choice);
        buttonProductDelete = findViewById(R.id.buttonProductDelete);
        buttonProductEdit = findViewById(R.id.buttonProductDelete);
        textViewProductName = findViewById(R.id.textViewProductName);


    }
}