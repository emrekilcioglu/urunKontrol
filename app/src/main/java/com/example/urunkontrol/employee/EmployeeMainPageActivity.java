package com.example.urunkontrol.employee;

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
import com.example.urunkontrol.classes.CRUDResponse;
import com.example.urunkontrol.classes.User;
import com.example.urunkontrol.classes.UserDaoInterface;
import com.example.urunkontrol.classes.UserResponse;
import com.google.android.material.navigation.NavigationView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeMainPageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayoutEmp;
    private Toolbar toolbarEmp;
    private NavigationView navViewEmp;
    private FragmentContainerView fragmentContainerView;
    private Fragment fragment;
    private Intent intent;
    private Intent intentMainAct;
    private String userId,jobStatus,name;
    private UserDaoInterface userDif;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intentMainAct = getIntent();
        userDif = ApiUtils.getUserInterface();
        userId = intentMainAct.getStringExtra("user_id");//user id,intentden aldığımız veri burada
        setContentView(R.layout.activity_employee_main_page);
        drawerLayoutEmp = findViewById(R.id.drawerLayoutEmp);
        toolbarEmp = findViewById(R.id.toolbarEmp);
        navViewEmp = findViewById(R.id.navViewEmp);
        fragmentContainerView = findViewById(R.id.fragmentContainerViewEmp);

        View baslik = navViewEmp.inflateHeaderView(R.layout.navigation_title);
        TextView textView = baslik.findViewById(R.id.textViewName);


        //Retrofit metodu burada
        userDif.searchUser(userId).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                User user = response.body().getUser().get(0);
                jobStatus = user.getJobStatus();
                name = user.getName();
                textView.setText(name);


                if (savedInstanceState == null) {//Burada başlangıçta gelecek fragmentı tanımladık
                    Bundle bundle = new Bundle();//Buradan bu fragmenta veri yolluyorum
                    //oq fragment için veri gönderimi
                    bundle.putString("job_status",jobStatus);
                    bundle.putString("user_id",userId);
                    Log.e("İf çalıştı","İf çalıştı");//kontrol temizlenecektir
                    // TODO: 15/12/2022 Bu bundlelarda bulunan veriyi qr fragmnetta aktar

                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .add(R.id.fragmentContainerViewEmp,ReadQrFragment.class, bundle)
                            .commit();
                }

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });







        setSupportActionBar(toolbarEmp);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayoutEmp,toolbarEmp,0,0);
        drawerLayoutEmp.addDrawerListener(toggle);
        toggle.syncState();//Toolbar üstüne togle butonu getirir

        navViewEmp.setNavigationItemSelectedListener(this);


        // TODO: 03/11/2022 drawerı bağladık

    }

    @Override
    public void onBackPressed() {//Geri tuşu ile drawerı kapatmamıza yarar
        if(drawerLayoutEmp.isDrawerOpen(GravityCompat.START)){
            drawerLayoutEmp.closeDrawer(GravityCompat.START);
        }
        else
            super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Bundle bundleMenu = new Bundle();//Buradan bu fragmenta veri yolluyorum
        //oq fragment için veri gönderimi
        bundleMenu.putString("job_status",jobStatus);
        bundleMenu.putString("user_id",userId);
        int id = item.getItemId();
        if (id == R.id.action_home){//drawerda ki anasayfaya geçiş ve veri yollama
            Log.e("HomeCalisit","Home Calisti");
            fragment = new ReadQrFragment();
            fragment.setArguments(bundleMenu);


        }
        else if (id == R.id.action_history){
            fragment = new TransactionHistoryFragment();


        }
        else if (id == R.id.action_log_out){
            SharedPreferences sharedPreferences = getSharedPreferences("AutoLoginSettings", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("user_name");
            editor.remove("password");
            editor.commit();
            startActivity(new Intent(EmployeeMainPageActivity.this, MainActivity.class));
            finish();



        }
        else return false;
        if (fragment != null){
            fragment.setArguments(bundleMenu);//Fragmenta geçerken veri ekledim
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainerViewEmp,fragment)
                    .commit();
            drawerLayoutEmp.closeDrawer(GravityCompat.START);

        }


        return true;
    }



    // TODO: 12/12/2022 Giriş ve çıkış işlemleri mainlerde dönecek 

    private void cikis(){


        userDif.closeUser(userId).enqueue(new Callback<CRUDResponse>() {
            @Override
            public void onResponse(Call<CRUDResponse> call, Response<CRUDResponse> response) {

            }

            @Override
            public void onFailure(Call<CRUDResponse> call, Throwable t) {

            }
        });
    }


}