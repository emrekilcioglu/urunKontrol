package com.example.urunkontrol.manager;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.urunkontrol.R;
import com.example.urunkontrol.classes.ApiUtils;
import com.example.urunkontrol.classes.Category;
import com.example.urunkontrol.classes.CategoryAdapter;
import com.example.urunkontrol.classes.CategoryDaoInterface;
import com.example.urunkontrol.classes.CategoryResponse;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductAddActivity extends AppCompatActivity {
    private TextInputLayout productInLay,productBrandLay,productCategoryLay;
    private TextInputEditText textInEditProductNam,textInEditStockDanger,textInEditStock;
    private AutoCompleteTextView textInEditCategory,textInEditBrand;
    private CategoryAdapter categoryAdapter;
    private CategoryDaoInterface categoryDif;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_add);
        //Layoutlar
        productBrandLay = findViewById(R.id.productBrandLay);
        productInLay = findViewById(R.id.productInLay);
        productCategoryLay = findViewById(R.id.productCategoryLay);
        //EditTextler
        textInEditProductNam = findViewById(R.id.textInEditProductNam);
        textInEditStockDanger = findViewById(R.id.textInEditStockDanger);
        textInEditStock = findViewById(R.id.textInEditStock);
        //AutoTextler
        textInEditCategory = findViewById(R.id.textInEditCategory);
        textInEditBrand = findViewById(R.id.textInEditBrand);
        categoryDif = ApiUtils.getCategoryInterface();
        categoryDif.allCategory().enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                List<Category> categories = response.body().getCategory();
                categoryAdapter = new CategoryAdapter(ProductAddActivity.this,R.layout.dropdown_item,categories);
                textInEditCategory.setAdapter(categoryAdapter);

            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {

            }
        });






    }
}