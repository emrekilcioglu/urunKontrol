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
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.urunkontrol.R;
import com.example.urunkontrol.ReadQrFragment;
import com.google.android.material.navigation.NavigationView;

public class EmployeeMainPageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayoutEmp;
    private Toolbar toolbarEmp;
    private NavigationView navViewEmp;
    private FragmentContainerView fragmentContainerView;
    private Fragment fragment;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_main_page);
        drawerLayoutEmp = findViewById(R.id.drawerLayoutEmp);
        toolbarEmp = findViewById(R.id.toolbarEmp);
        navViewEmp = findViewById(R.id.navViewEmp);
        fragmentContainerView = findViewById(R.id.fragmentContainerViewEmp);
        setSupportActionBar(toolbarEmp);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayoutEmp,toolbarEmp,0,0);
        drawerLayoutEmp.addDrawerListener(toggle);
        toggle.syncState();//Toolbar üstüne togle butonu getirir
        View baslik = navViewEmp.inflateHeaderView(R.layout.navigation_title);
        TextView textView = baslik.findViewById(R.id.textViewName);
        textView.setText("Emre Kılcıoğlu");
        navViewEmp.setNavigationItemSelectedListener(this);


        // TODO: 03/11/2022 drawerı bağladık

    }

    @Override
    public void onBackPressed() {
        if(drawerLayoutEmp.isDrawerOpen(GravityCompat.START)){
            drawerLayoutEmp.closeDrawer(GravityCompat.START);
        }
        else
            super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_home){
            fragment = new ReadQrFragment();
        }
        else if (id == R.id.action_history){
            fragment = new TransactionHistoryFragment();
        }
        else return false;

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainerViewEmp,fragment)
                .commit();
        drawerLayoutEmp.closeDrawer(GravityCompat.START);
        return true;
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerViewEmp,fragment);
        fragmentTransaction.commit();
    }
}