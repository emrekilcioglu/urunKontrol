package com.example.urunkontrol;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.urunkontrol.classes.ApiUtils;
import com.example.urunkontrol.classes.User;
import com.example.urunkontrol.classes.UserDaoInterface;
import com.example.urunkontrol.classes.UserResponse;
import com.example.urunkontrol.employee.EmployeeMainPageActivity;
import com.example.urunkontrol.manager.ManagerMainPageActivity;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Button buttonLogin;
    private TextInputEditText editTextName,editTextPassword;
    private Intent intentEmp;
    private Intent intentManage;
    private SharedPreferences sharedPreferences;
    private String userName,password;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("AutoLoginSettings", MODE_PRIVATE);

        userName = sharedPreferences.getString("user_name", null);
        password = sharedPreferences.getString("password", null);
        if (userName !=null && password != null){
            loginControl(userName,password);
        }
        else {
            setContentView(R.layout.activity_main);
            buttonLogin = findViewById(R.id.buttonLogin);
            editTextName = findViewById(R.id.edtitTextName);
            editTextPassword = findViewById(R.id.editTextPassword);



            buttonLogin.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("SuspiciousIndentation")
                @Override
                public void onClick(View view) {
                    loginControl(editTextName.getText().toString(), editTextPassword.getText().toString());


                }
            });
        }
    }

    private void loginControl(String userName,String password){
        UserDaoInterface userDif = ApiUtils.getUserInterface();
        userDif.userControl(userName,password).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.body().getSuccess() == 1){
                    User user = response.body().getUser().get(0);
                    autoLoginSettings(userName,password);
                    switch (user.getJobStatus()){
                        case "0":
                            intentEmp =  new Intent(MainActivity.this, EmployeeMainPageActivity.class);
                            intentEmp.putExtra("name",user.getName());

                            startActivity(intentEmp);
                            break;
                        case "1":
                            intentManage = new Intent(MainActivity.this, ManagerMainPageActivity.class);
                            intentManage.putExtra("name",user.getName());


                            startActivity(intentManage);

                    }

                }
                else if (response.body().getSuccess() == 2){
                    Toast.makeText(MainActivity.this, "Böyle bir kullanıcı yok", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(MainActivity.this, "Hata Oluştu", Toast.LENGTH_SHORT).show();



            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });
    }
    private void autoLoginSettings(String userName,String password){

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user_name",userName);
        editor.putString("password",password);
        editor.commit();



    }

}