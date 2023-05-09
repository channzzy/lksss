package com.example.chanzyhebat.API;

import com.example.chanzyhebat.Model.LoginRequest;
import com.example.chanzyhebat.Model.LoginResponse;
import com.example.chanzyhebat.Model.PofileRequest;
import com.example.chanzyhebat.Model.ProductRequest;
import com.example.chanzyhebat.Model.ProductResponse;
import com.example.chanzyhebat.Model.RegisterRequest;
import com.example.chanzyhebat.Model.RegitserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface APIRequestData {
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST
    Call<RegitserResponse> register(
            @Url String url,
            @Body RegisterRequest registerRequest
            );
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST
    Call<LoginResponse> login(
            @Url String url,
            @Body LoginRequest loginRequest
            );
    @Headers({"Content-Type: appliacation/json", "Accept: application/json"})
    @GET("user")
    Call<PofileRequest> getUser(
            @Header("Authorization") String token
    );
    @Headers({"Content-Type: appliacation/json", "Accept: appliacation/json"})
    @GET("products")
    Call<ProductResponse> getProduct(
            @Header("Authorization") String token
    );
}
