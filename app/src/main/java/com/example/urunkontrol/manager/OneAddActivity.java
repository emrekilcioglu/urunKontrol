package com.example.urunkontrol.manager;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.urunkontrol.R;
import com.example.urunkontrol.classes.ApiUtils;
import com.example.urunkontrol.classes.BrandDaoInterface;
import com.example.urunkontrol.classes.CRUDResponse;
import com.example.urunkontrol.classes.CategoryDaoInterface;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OneAddActivity extends AppCompatActivity {
    private Button buttonOkInputOne;
    private TextInputLayout textInputLayoutOne;
    private TextInputEditText editTextAddOne;
    private Intent intentList;
    private boolean router;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_add);
        buttonOkInputOne = findViewById(R.id.buttonOkInputOne);
        editTextAddOne = findViewById(R.id.editTextAddOne);
        textInputLayoutOne =findViewById(R.id.textInputLayoutOne);

        intentList = getIntent();
        router = intentList.getBooleanExtra("router",true);
        if (router){
            textInputLayoutOne.setHint("Kategori İsmi Giriniz");
        }
        else textInputLayoutOne.setHint("Marka İsmi Giriniz");
        buttonOkInputOne.setOnClickListener(view -> {
            String value = editTextAddOne.getText().toString();
            if (router){
                addCategory(value);

            }
            else {
                addBrand(value);
            }

            Intent intentMain = new Intent(this,ManagerMainPageActivity.class);
            //intentMain.putExtra();
        });


    }

    private void addCategory(String categoryName){
        CategoryDaoInterface categoryDif = ApiUtils.getCategoryInterface();
        categoryDif.insertCategory(categoryName).enqueue(new Callback<CRUDResponse>() {
            @Override
            public void onResponse(Call<CRUDResponse> call, Response<CRUDResponse> response) {

            }

            @Override
            public void onFailure(Call<CRUDResponse> call, Throwable t) {

            }
        });
    }
    private void addBrand(String brandName){
        BrandDaoInterface brandDif = ApiUtils.getBrandDaoInterface();
        brandDif.insertBrand(brandName).enqueue(new Callback<CRUDResponse>() {
            @Override
            public void onResponse(Call<CRUDResponse> call, Response<CRUDResponse> response) {

            }

            @Override
            public void onFailure(Call<CRUDResponse> call, Throwable t) {

            }
        });
    }
}