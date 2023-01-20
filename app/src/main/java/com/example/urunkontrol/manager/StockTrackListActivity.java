package com.example.urunkontrol.manager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.urunkontrol.R;
import com.example.urunkontrol.classes.ApiUtils;
import com.example.urunkontrol.classes.Product;
import com.example.urunkontrol.classes.ProductDaoInterface;
import com.example.urunkontrol.classes.ProductMovement;
import com.example.urunkontrol.classes.ProductMovementDaoInterfaca;
import com.example.urunkontrol.classes.ProductMovementResponse;
import com.example.urunkontrol.classes.ProductResponse;
import com.example.urunkontrol.classes.RvAdapterProductMan;
import com.example.urunkontrol.classes.RvAdapterProductMovementMan;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StockTrackListActivity extends AppCompatActivity {
    private RecyclerView rvStockTrack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_track_list);

        Intent intentStockTrack = getIntent();
        int router = intentStockTrack.getIntExtra("router", 3);
        rvStockTrack = findViewById(R.id.rvStockTrack);
        rvStockTrack.setHasFixedSize(true);
        rvStockTrack.setLayoutManager(new LinearLayoutManager(StockTrackListActivity.this));

        if (router==1){
            ProductMovementDaoInterfaca productMovementDif;
            productMovementDif = ApiUtils.getProductMovementInterface();
            productMovementDif.allProductMovement().enqueue(new Callback<ProductMovementResponse>() {
                @Override
                public void onResponse(Call<ProductMovementResponse> call, Response<ProductMovementResponse> response) {
                    List<ProductMovement> productMovementList = response.body().getProductMovement();
                    RvAdapterProductMovementMan rvAdapter = new RvAdapterProductMovementMan(StockTrackListActivity.this, productMovementList);


                    rvStockTrack.setAdapter(rvAdapter);


                }

                @Override
                public void onFailure(Call<ProductMovementResponse> call, Throwable t) {

                }
            });
        }
        else if (router ==2){
            ProductDaoInterface productDif = ApiUtils.getProductInterface();
            productDif.allProductDanger().enqueue(new Callback<ProductResponse>() {
                @Override
                public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                    List<Product> productList = response.body().getProduct();
                    RvAdapterProductMan rvAdapter = new RvAdapterProductMan(StockTrackListActivity.this,productList);
                    rvStockTrack.setAdapter(rvAdapter);

                }

                @Override
                public void onFailure(Call<ProductResponse> call, Throwable t) {

                }
            });
        }



    }

}