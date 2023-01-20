package com.example.urunkontrol.manager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.urunkontrol.R;
import com.example.urunkontrol.classes.ApiUtils;
import com.example.urunkontrol.classes.BrandDaoInterface;
import com.example.urunkontrol.classes.BrandResponse;
import com.example.urunkontrol.classes.CRUDResponse;
import com.example.urunkontrol.classes.Category;
import com.example.urunkontrol.classes.CategoryDaoInterface;
import com.example.urunkontrol.classes.CategoryResponse;
import com.example.urunkontrol.classes.RvAdapterBrand;
import com.example.urunkontrol.classes.RvAdapterCategory;
import com.example.urunkontrol.classes.RvAdapterUser;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListFragment extends Fragment {
    private FloatingActionButton floatButtonPool;
    private List<Category> categories;
    private RecyclerView recyclerViewPool;
    private RvAdapterCategory rvAdapterCategory;
    private RecyclerView.Adapter adapter;
    private AlertDialog.Builder ad;

    public ListFragment(RecyclerView.Adapter adapter){
        this.adapter = adapter;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        floatButtonPool = rootView.findViewById(R.id.floatButtonPool);
        recyclerViewPool = rootView.findViewById(R.id.recyclerViewPool);
        recyclerViewPool.setHasFixedSize(true);
        recyclerViewPool.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewPool.setAdapter(adapter);
        floatButtonPool.setOnClickListener(view -> {

            if (adapter instanceof RvAdapterCategory){
                ad = new AlertDialog.Builder(getContext());
                View design = getLayoutInflater().inflate(R.layout.allert_design,null);
                categoryUpdate(design);

            }
            else if (adapter instanceof RvAdapterBrand){
                ad = new AlertDialog.Builder(getContext());
                View design = getLayoutInflater().inflate(R.layout.allert_design,null);
                brandUpdate(design);


            }
            else if (adapter instanceof RvAdapterUser){
                startActivity(new Intent(getContext(),AddUserActivity.class));
            }
        });






        return  rootView;

    }

    private void categoryUpdate(View design){
        CategoryDaoInterface categoryDif;
        categoryDif = ApiUtils.getCategoryInterface();
        EditText editTextInsert = design.findViewById(R.id.editTextInsert);
        ad.setTitle("Kategori Ekle");
        editTextInsert.setHint("Kategori Adı Giriniz");
        ad.setView(design);
        ad.setPositiveButton("Ekle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String categoryName = editTextInsert.getText().toString();
                categoryDif.insertCategory(categoryName).enqueue(new Callback<CRUDResponse>() {
                    @Override
                    public void onResponse(Call<CRUDResponse> call, Response<CRUDResponse> response) {
                        categoryDif.allCategory().enqueue(new Callback<CategoryResponse>() {
                            @Override
                            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                                adapter = new RvAdapterCategory(getContext(),response.body().getCategory());
                                recyclerViewPool.setAdapter(adapter);








                            }

                            @Override
                            public void onFailure(Call<CategoryResponse> call, Throwable t) {

                            }
                        });

                    }

                    @Override
                    public void onFailure(Call<CRUDResponse> call, Throwable t) {

                    }
                });


            }
        });//Listener koymalıyız ki tıklamasını yakalayalım
        ad.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        ad.create().show();



    }

    private void brandUpdate(View design){
        BrandDaoInterface brandDif;
        brandDif = ApiUtils.getBrandDaoInterface();
        EditText editTextInsert = design.findViewById(R.id.editTextInsert);
        ad.setTitle("Marka Ekle");
        editTextInsert.setHint("Marka Adı Giriniz");
        ad.setView(design);
        ad.setPositiveButton("Ekle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String brandName = editTextInsert.getText().toString();
                brandDif.insertBrand(brandName).enqueue(new Callback<CRUDResponse>() {
                    @Override
                    public void onResponse(Call<CRUDResponse> call, Response<CRUDResponse> response) {
                        brandDif.allBrand().enqueue(new Callback<BrandResponse>() {
                            @Override
                            public void onResponse(Call<BrandResponse> call, Response<BrandResponse> response) {
                                adapter = new RvAdapterBrand(getContext(),response.body().getBrand());
                                recyclerViewPool.setAdapter(adapter);








                            }

                            @Override
                            public void onFailure(Call<BrandResponse> call, Throwable t) {

                            }
                        });

                    }

                    @Override
                    public void onFailure(Call<CRUDResponse> call, Throwable t) {

                    }
                });


            }
        });//Listener koymalıyız ki tıklamasını yakalayalım
        ad.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        ad.create().show();



    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("Resume List","Resume");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("Stop List","Stop");


    }
}