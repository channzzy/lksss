package com.example.chanzyhebat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chanzyhebat.API.APIRequestData;
import com.example.chanzyhebat.API.RetroServer;
import com.example.chanzyhebat.Model.RegisterRequest;
import com.example.chanzyhebat.Model.RegitserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText tename,teusername,teaddress,tepassword,tekfpassword;
    String name,username,address,password,kfpassword;
    Button btndaftar,btnback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        tename = findViewById(R.id.te_nama);
        teusername = findViewById(R.id.te_username);
        teaddress = findViewById(R.id.te_alamat);
        tepassword = findViewById(R.id.te_password);
        tekfpassword = findViewById(R.id.te_kfpassword);
        btndaftar = findViewById(R.id.btn_daftar);
        btnback  =  findViewById(R.id.btn_back);

        btndaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });
    }
    public void register(){
        name = tename.getText().toString().trim();
        username = teusername.getText().toString().trim();
        address = teaddress.getText().toString().trim();
        password  = tepassword.getText().toString().trim();
        kfpassword  = tekfpassword.getText().toString().trim();

        if(name.equals("") || username.equals("") || address.equals("") || password.equals("") ||
        kfpassword.equals("")){
            Toast.makeText(this, "Harap Isi Semua Form", Toast.LENGTH_SHORT).show();
            return;
        }else if(!password.equals(kfpassword)){
            Toast.makeText(this, "Password Tidak Sesuai", Toast.LENGTH_SHORT).show();
            return;
        }
        RegisterRequest rp = new RegisterRequest(name,username,address,password,kfpassword);
        rp.setName(name);
        rp.setAddress(username);
        rp.setAddress(address);
        rp.setPassword(password);
        rp.setPassword_confirmation(kfpassword);

        APIRequestData ardData = RetroServer.getCon().create(APIRequestData.class);
        Call<RegitserResponse> ur = ardData.register("http://103.67.187.184/api/register",rp);
        ur.enqueue(new Callback<RegitserResponse>() {
            @Override
            public void onResponse(Call<RegitserResponse> call, Response<RegitserResponse> response) {
                if(response.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "Berhasil Mendaftar", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                }else{
                    Toast.makeText(RegisterActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegitserResponse> call, Throwable t) {

            }
        });
    }
}