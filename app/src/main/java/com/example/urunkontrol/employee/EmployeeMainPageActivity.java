package com.example.urunkontrol.employee;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.urunkontrol.R;
import com.google.android.material.navigation.NavigationView;

public class EmployeeMainPageActivity extends AppCompatActivity {
    private DrawerLayout drawerLayoutEmp;
    private Toolbar toolbarEmp;
    private NavigationView navViewEmp;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_main_page);
        drawerLayoutEmp = findViewById(R.id.drawerLayoutEmp);
        toolbarEmp = findViewById(R.id.toolbarEmp);
        navViewEmp = findViewById(R.id.navViewEmp);
        toolbarEmp.setTitle(R.string.toolbar_emp_main_page);
        setSupportActionBar(toolbarEmp);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayoutEmp,toolbarEmp,0,0);
        drawerLayoutEmp.addDrawerListener(toggle);
        toggle.syncState();

    }
}