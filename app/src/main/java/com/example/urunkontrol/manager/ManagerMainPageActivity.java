package com.example.urunkontrol.manager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.urunkontrol.R;
import com.example.urunkontrol.ReadQrFragment;
import com.google.android.material.navigation.NavigationView;

public class ManagerMainPageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayoutM;
    private Toolbar toolbarM;
    private NavigationView navigationViewM;
    private FragmentContainerView fragmentContainerM;
    private Fragment fragment;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_main_page);
        drawerLayoutM = findViewById(R.id.drawerLayoutM);
        toolbarM = findViewById(R.id.toolbarM);
        navigationViewM = findViewById(R.id.navigationViewM);
        fragmentContainerM = findViewById(R.id.fragmentContainerViewM);
        toolbarM.setTitle("Deney");
        setSupportActionBar(toolbarM);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayoutM,toolbarM,0,0);
        drawerLayoutM.addDrawerListener(toggle);
        toggle.syncState();
        navigationViewM.setNavigationItemSelectedListener(this);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_home_page){
            fragment = new ReadQrFragment();

        }
        else if (id == R.id.action_list_product){
            fragment = new ListFragment();

        }
        else if (id == R.id.action_list_brand){
            fragment = new ListFragment();

        }
        else if (id == R.id.action_list_category){
            fragment = new ListFragment();

        }
        else if (id == R.id.action_list_user){
            fragment = new ListFragment();

        }
        else if (id == R.id.action_list_stock){
            fragment = new ListFragment();

        }
        else return false;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainerViewM,fragment)
                .commit();
        drawerLayoutM.closeDrawer(GravityCompat.START);

        return true;
    }
}