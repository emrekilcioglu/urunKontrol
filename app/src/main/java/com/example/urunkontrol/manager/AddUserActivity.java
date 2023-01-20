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
import com.example.urunkontrol.classes.CRUDResponse;
import com.example.urunkontrol.classes.Product;
import com.example.urunkontrol.classes.ProductDaoInterface;
import com.example.urunkontrol.classes.ProductResponse;
import com.example.urunkontrol.classes.User;
import com.example.urunkontrol.classes.UserDaoInterface;
import com.example.urunkontrol.classes.UserResponse;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddUserActivity extends AppCompatActivity {
    private TextInputEditText editTextName,editTextSurname,editTextTcNo,editTextUserName,editTextPassword;
    private AutoCompleteTextView editTextJob;
    private Button buttonAddUser;
    private Intent intentUp;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        editTextName = findViewById(R.id.editTextNameSign);
        editTextSurname = findViewById(R.id.editTextSurname);
        editTextTcNo = findViewById(R.id.editTextTcNo);
        editTextUserName = findViewById(R.id.editTextUserName);
        editTextPassword = findViewById(R.id.editTextPasswordSign);
        editTextJob = findViewById(R.id.editTextJob);
        buttonAddUser = findViewById(R.id.buttonAddUser);
        intentUp = getIntent();
        int router = intentUp.getIntExtra("router",0);
        String[] jobs = {"İşçi","Yönetici"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.dropdown_item,jobs);
        editTextJob.setAdapter(adapter);
        if (router ==1){
            String userId = intentUp.getStringExtra("user_id");
            UserDaoInterface userDif = ApiUtils.getUserInterface();
            userDif.getUser(userId).enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    String name,surName,tcNo,userName,password,job;
                    User user = response.body().getUser().get(0);
                    String[] fullName = user.getName().split(" ");
                    name = fullName[0];
                    surName= fullName[1];
                    Log.e("İSim",fullName[1]);
                    tcNo= user.getTcNo();
                    userName = user.getUserName();
                    password = user.getPassword();
                    job = user.getJobStatus();

                    editTextName.setText(name);
                    editTextSurname.setText(surName);
                    editTextTcNo.setText(tcNo);
                    editTextUserName.setText(userName);
                    editTextPassword.setText(password);
                    editTextJob.setText(job);

                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {

                }
            });
            buttonAddUser.setOnClickListener(view -> {
                updateUser();
                finish();

            });
        }else {
            buttonAddUser.setOnClickListener(view -> {
                addUser();
                finish();
            });
        }


    }
    private void addUser(){
        String userName,password,name,tcNo,jobStatus;
        userName = editTextUserName.getText().toString();
        password = editTextPassword.getText().toString();
        name = (editTextName.getText().toString()+""+editTextSurname.getText().toString());
        tcNo = editTextTcNo.getText().toString();
        jobStatus = editTextJob.getText().toString();
        UserDaoInterface userDif = ApiUtils.getUserInterface();
        userDif.insertUser(userName,password,name,tcNo,jobStatus).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });

    }
    private void updateUser(){
        String userName,password,name,tcNo,jobStatus;
        userName = editTextUserName.getText().toString();
        password = editTextPassword.getText().toString();
        name = (editTextName.getText().toString()+" "+editTextSurname.getText().toString());
        tcNo = editTextTcNo.getText().toString();
        jobStatus = editTextJob.getText().toString();
        Log.e("Update User Çalıştı","Update");
        String userId= intentUp.getStringExtra("user_id");

        UserDaoInterface userDif = ApiUtils.getUserInterface();
        userDif.updateUser(userName,password,name,tcNo,jobStatus,userId).enqueue(new Callback<CRUDResponse>() {
            @Override
            public void onResponse(Call<CRUDResponse> call, Response<CRUDResponse> response) {

            }

            @Override
            public void onFailure(Call<CRUDResponse> call, Throwable t) {

            }
        });

    }
}