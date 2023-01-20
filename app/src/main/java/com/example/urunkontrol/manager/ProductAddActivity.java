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
import com.example.urunkontrol.classes.Product;
import com.example.urunkontrol.classes.ProductDaoInterface;
import com.example.urunkontrol.classes.ProductResponse;
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
    private Intent intentUp;
    private String userId;



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

        productDif = ApiUtils.getProductInterface();
        intentUp = getIntent();
        int router = intentUp.getIntExtra("router",0);
        userId = intentUp.getStringExtra("user_id");



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
        if (router ==1){
            String qrBarcode = intentUp.getStringExtra("qr_or_barcode");
            productDif.qrControl(qrBarcode).enqueue(new Callback<ProductResponse>() {
                @Override
                public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                    String productName,stock,stockDangerLevel,unitWeight,price;
                    Product product = response.body().getProduct().get(0);
                    productName = product.getProductName();
                    //Brand brand = product.getBrand();
                    //Category category = product.getCategory();
                    stock = product.getMaxStockLevel();
                    stockDangerLevel = product.getStockDangerLevel();
                    unitWeight = product.getUnitWeight();
                    price = product.getPrice();

                    textInEditProductNam.setText(productName);

                    textInEditStock.setText(stock);
                    textInEditStockDanger.setText(stockDangerLevel);
                    textInEditWeight.setText(unitWeight);
                    textInEditPrice.setText(price);

                }

                @Override
                public void onFailure(Call<ProductResponse> call, Throwable t) {

                }
            });
            buttonSave.setOnClickListener(view -> {
                updateProduct();
                Intent intent = new Intent(ProductAddActivity.this,ManagerMainPageActivity.class);
                intent.putExtra("user_id",userId);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);

            });
        }
        else if (router ==2){
            String productId = intentUp.getStringExtra("product_id");
            productDif.getProduct(productId).enqueue(new Callback<ProductResponse>() {
                @Override
                public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                    String productName,stock,stockDangerLevel,unitWeight,price;
                    Product product = response.body().getProduct().get(0);
                    productName = product.getProductName();
                    //Brand brand = product.getBrand();
                    //Category category = product.getCategory();
                    stock = product.getMaxStockLevel();
                    stockDangerLevel = product.getStockDangerLevel();
                    unitWeight = product.getUnitWeight();
                    price = product.getPrice();

                    textInEditProductNam.setText(productName);

                    textInEditStock.setText(stock);
                    textInEditStockDanger.setText(stockDangerLevel);
                    textInEditWeight.setText(unitWeight);
                    textInEditPrice.setText(price);

                }

                @Override
                public void onFailure(Call<ProductResponse> call, Throwable t) {

                }
            });
            buttonSave.setOnClickListener(view -> {
                updateProduct();
                finish();



            });
        }
        else {
            buttonSave.setOnClickListener(view -> {
                addProduct();
                finish();

            });
        }

    }

    private void addProduct(){
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

    private void updateProduct(){
        String barcodeQr,productName,brandName,categoryName,price,unitWeight,maxStockLevel,stockDangerLevel;
        Intent readQrIntent = getIntent();
        barcodeQr = readQrIntent.getStringExtra("qr_or_barcode");
        productName = textInEditProductNam.getText().toString();
        brandName = textInEditBrand.getText().toString();
        categoryName = textInEditCategory.getText().toString();
        unitWeight = textInEditWeight.getText().toString();
        price = textInEditPrice.getText().toString();
        maxStockLevel = textInEditStock.getText().toString();
        stockDangerLevel = textInEditStockDanger.getText().toString();
        Log.e("deney",barcodeQr+ "  "+ " "+price+" "+productName+" "+categoryName+"  "+brandName );
        productDif.updateProduct(barcodeQr,productName,brandName,categoryName,price,unitWeight,maxStockLevel,stockDangerLevel)
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