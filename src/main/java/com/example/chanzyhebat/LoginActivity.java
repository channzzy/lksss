package com.example.chanzyhebat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chanzyhebat.API.APIRequestData;
import com.example.chanzyhebat.API.RetroServer;
import com.example.chanzyhebat.Model.LoginRequest;
import com.example.chanzyhebat.Model.LoginResponse;
import com.example.chanzyhebat.ui.home.HomeFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText teusername,tepassword;
    String username,password;
    Button btndaftar,btnlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btndaftar = findViewById(R.id.btn_daftar);
        btnlogin = findViewById(R.id.btn_login);
        teusername = findViewById(R.id.te_username);
        tepassword = findViewById(R.id.te_password);
        btndaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }
    public void login(){
        username = teusername.getText().toString().trim();
        password = tepassword.getText().toString().trim();

        if(username.equals("") || password.equals("")){
            Toast.makeText(this, "Harap Isi Akun Anda", Toast.LENGTH_SHORT).show();
            return;
        }

        LoginRequest lr = new LoginRequest(username,password);
        lr.setUsername(username);
        lr.setPassword(password);
        APIRequestData ardData = RetroServer.getCon().create(APIRequestData.class);
        Call<LoginResponse> loginUser = ardData.login("http://103.67.187.184/api/login",lr);
        loginUser.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    String token = response.body().getData();
                    new TokenManager(LoginActivity.this).saveToken(token);
                    Intent intent = new Intent(LoginActivity.this,Dashboard.class);
                    intent.putExtra("token",token);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this, "Berhasil Login" +token, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });
    }
}