package com.example.urunkontrol.manager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.urunkontrol.MainActivity;
import com.example.urunkontrol.R;
import com.example.urunkontrol.ReadQrFragment;
import com.example.urunkontrol.classes.ApiUtils;
import com.example.urunkontrol.classes.Brand;
import com.example.urunkontrol.classes.BrandDaoInterface;
import com.example.urunkontrol.classes.BrandResponse;
import com.example.urunkontrol.classes.CategoryDaoInterface;
import com.example.urunkontrol.classes.CategoryResponse;
import com.example.urunkontrol.classes.ProductDaoInterface;
import com.example.urunkontrol.classes.ProductResponse;
import com.example.urunkontrol.classes.RvAdapterBrand;
import com.example.urunkontrol.classes.RvAdapterCategory;
import com.example.urunkontrol.classes.RvAdapterProduct;
import com.example.urunkontrol.classes.RvAdapterUser;
import com.example.urunkontrol.classes.User;
import com.example.urunkontrol.classes.UserDaoInterface;
import com.example.urunkontrol.classes.UserResponse;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManagerMainPageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayoutM;
    private Toolbar toolbarM;
    private NavigationView navigationViewM;
    private FragmentContainerView fragmentContainerM;
    private Fragment fragment;
    private RecyclerView.Adapter adapter;
    private Intent intentMainAct;
    private String userId,jobStatus,name;
    private UserDaoInterface userDif;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_manager_main_page);

        intentMainAct = getIntent();
        userDif = ApiUtils.getUserInterface();
        userId = intentMainAct.getStringExtra("user_id");//user id,intentden aldığımız veri burada

        drawerLayoutM = findViewById(R.id.drawerLayoutM);
        toolbarM = findViewById(R.id.toolbarM);
        navigationViewM = findViewById(R.id.navigationViewM);
        fragmentContainerM = findViewById(R.id.fragmentContainerViewM);

        View baslik = navigationViewM.inflateHeaderView(R.layout.navigation_title);
        TextView textView = baslik.findViewById(R.id.textViewName);

        //Retrofit metodu burada
        userDif.searchUser(userId).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                User user = response.body().getUser().get(0);
                jobStatus = user.getJobStatus();
                name = user.getName();
                textView.setText(name);

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });
        setSupportActionBar(toolbarM);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayoutM,toolbarM,0,0);
        drawerLayoutM.addDrawerListener(toggle);
        toggle.syncState();

        navigationViewM.setNavigationItemSelectedListener(this);




    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_home_page:
                fragment = new StockTrackFragment();
                replaceFragment(fragment);
                drawerLayoutM.closeDrawer(GravityCompat.START);
                return true;

            case R.id.action_qr:
                fragment = new ReadQrFragment();
                Bundle bundleMenu = new Bundle();//Buradan bu fragmenta veri yolluyorum
                //oq fragment için veri gönderimi
                bundleMenu.putString("job_status",jobStatus);
                bundleMenu.putString("user_id",userId);
                fragment.setArguments(bundleMenu);
                replaceFragment(fragment);
                drawerLayoutM.closeDrawer(GravityCompat.START);
                return true;


            case R.id.action_list_category:
                allCategory();
                drawerLayoutM.closeDrawer(GravityCompat.START);
                return true;
            case R.id.action_list_brand:
                Log.e("Brand Calıştı","Brand");
                allBrand();
                drawerLayoutM.closeDrawer(GravityCompat.START);
                return true;
            case R.id.action_list_product:
                allProduct();
                drawerLayoutM.closeDrawer(GravityCompat.START);
                return true;
            case R.id.action_list_user:
                allUser();
                drawerLayoutM.closeDrawer(GravityCompat.START);
                return true;

            case R.id.action_log_out:
                SharedPreferences sharedPreferences = getSharedPreferences("AutoLoginSettings", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("user_name");
                editor.remove("password");
                editor.commit();
                startActivity(new Intent(ManagerMainPageActivity.this, MainActivity.class));
                finish();
                return true;













        }

        return super.onOptionsItemSelected(item);




    }
    @Override
    public void onBackPressed() {
        if(drawerLayoutM.isDrawerOpen(GravityCompat.START)){
            drawerLayoutM.closeDrawer(GravityCompat.START);
        }
        else
            super.onBackPressed();
    }
    public void allCategory(){
        CategoryDaoInterface categoryDif;
        categoryDif = ApiUtils.getCategoryInterface();
        categoryDif.allCategory().enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                adapter = new RvAdapterCategory(ManagerMainPageActivity.this,response.body().getCategory());
                fragment = new ListFragment(adapter);
                Bundle bundleMenu = new Bundle();//Buradan bu fragmenta veri yolluyorum
                bundleMenu.putString("user_id",userId);
                fragment.setArguments(bundleMenu);
                replaceFragment(fragment);





            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {

            }
        });
    }
    public void allBrand(){
        Log.e("All Brand Çalıştır","Brand2");
        BrandDaoInterface brandDif;
        brandDif = ApiUtils.getBrandDaoInterface();
        brandDif.allBrand().enqueue(new Callback<BrandResponse>() {
            @Override
            public void onResponse(Call<BrandResponse> call, Response<BrandResponse> response) {
                adapter = new RvAdapterBrand(ManagerMainPageActivity.this,response.body().getBrand());
                Bundle bundleMenu = new Bundle();//Buradan bu fragmenta veri yolluyorum

                fragment = new ListFragment(adapter);
                fragment.setArguments(bundleMenu);
                bundleMenu.putString("user_id",userId);
                Log.e("All Brand Çalıştır","Brand");

                replaceFragment(fragment);





            }

            @Override
            public void onFailure(Call<BrandResponse> call, Throwable t) {

            }
        });
    }
    public void allProduct(){
        ProductDaoInterface productdDif;
        productdDif = ApiUtils.getProductInterface();

        productdDif.allProduct().enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                adapter = new RvAdapterProduct(ManagerMainPageActivity.this,response.body().getProduct());
                fragment = new ListFragment(adapter);
                replaceFragment(fragment);





            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {

            }
        });
    }
    public void allUser(){
        UserDaoInterface userDif;
        userDif = ApiUtils.getUserInterface();

        userDif.allUser().enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                adapter = new RvAdapterUser(ManagerMainPageActivity.this,response.body().getUser());
                fragment = new ListFragment(adapter);
                replaceFragment(fragment);





            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerViewM,fragment);
        fragmentTransaction.commit();
    }




}