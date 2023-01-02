package com.example.urunkontrol.manager;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.example.urunkontrol.R;
import com.example.urunkontrol.classes.ApiUtils;
import com.example.urunkontrol.classes.Brand;
import com.example.urunkontrol.classes.BrandAdapter;
import com.example.urunkontrol.classes.BrandDaoInterface;
import com.example.urunkontrol.classes.BrandResponse;
import com.example.urunkontrol.classes.CRUDResponse;
import com.example.urunkontrol.classes.Category;
import com.example.urunkontrol.classes.CategoryAdapter;
import com.example.urunkontrol.classes.CategoryDaoInterface;
import com.example.urunkontrol.classes.CategoryResponse;
import com.example.urunkontrol.classes.ProductDaoInterface;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductAddActivity extends AppCompatActivity {
    private TextInputLayout productInLay,productBrandLay,productCategoryLay,unitWeightLay,priceLay;
    private TextInputEditText textInEditProductNam,textInEditStockDanger,textInEditStock,textInEditWeight,textInEditPrice;
    private AutoCompleteTextView textInEditCategory,textInEditBrand;
    private CategoryAdapter categoryAdapter;
    private BrandAdapter brandAdapter;
    private CategoryDaoInterface categoryDif;
    private BrandDaoInterface brandDif;
    private ProductDaoInterface productDif;
    private Button buttonSave;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_add);
        //Layoutlar
        productBrandLay = findViewById(R.id.productBrandLay);
        productInLay = findViewById(R.id.productInLay);
        productCategoryLay = findViewById(R.id.productCategoryLay);
        unitWeightLay =findViewById(R.id.unitWeightLay);
        priceLay = findViewById(R.id.priceLay);
        //EditTextler
        textInEditProductNam = findViewById(R.id.textInEditProductNam);
        textInEditStockDanger = findViewById(R.id.textInEditStockDanger);
        textInEditStock = findViewById(R.id.textInEditStock);
        textInEditWeight = findViewById(R.id.textInEditWeight);
        textInEditPrice = findViewById(R.id.textInEditPrice);
        //AutoTextler
        textInEditCategory = findViewById(R.id.textInEditCategory);
        textInEditBrand = findViewById(R.id.textInEditBrand);
        //Button
        buttonSave = findViewById(R.id.buttonSave);
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
        brandDif = ApiUtils.getBrandDaoInterface();
        brandDif.allBrand().enqueue(new Callback<BrandResponse>() {
            @Override
            public void onResponse(Call<BrandResponse> call, Response<BrandResponse> response) {
                List<Brand> brands = response.body().getBrand();
                brandAdapter = new BrandAdapter(ProductAddActivity.this,R.layout.dropdown_item,brands);
                textInEditBrand.setAdapter(brandAdapter);

            }

            @Override
            public void onFailure(Call<BrandResponse> call, Throwable t) {

            }
        });
        buttonSave.setOnClickListener(view -> {
            addProduct();
            finish();

        });
    }

    private void addProduct(){
        productDif = ApiUtils.getProductInterface();
        String barcodeQr,productName,brandName,categoryName,price,unitWeight,maxStockLevel,stockDangerLevel;
        Intent readQrIntent = getIntent();
        barcodeQr = readQrIntent.getStringExtra("barcode_or_qr");
        productName = textInEditProductNam.getText().toString();
        brandName = textInEditBrand.getText().toString();
        categoryName = textInEditCategory.getText().toString();
        unitWeight = textInEditWeight.getText().toString();
        price = textInEditPrice.getText().toString();
        maxStockLevel = textInEditStock.getText().toString();
        stockDangerLevel = textInEditStockDanger.getText().toString();
        Log.e("deney",barcodeQr+ "  "+ " "+price+" "+productName+" "+categoryName+"  "+brandName );
        productDif.insertProduct(barcodeQr,productName,brandName,categoryName,price,unitWeight,maxStockLevel,stockDangerLevel)
                .enqueue(new Callback<CRUDResponse>() {
                    @Override
                    public void onResponse(Call<CRUDResponse> call, Response<CRUDResponse> response) {

                    }

                    @Override
                    public void onFailure(Call<CRUDResponse> call, Throwable t) {

                    }
                });

    }
}