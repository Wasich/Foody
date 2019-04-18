package com.example.designer2.foody.api;

import com.example.designer2.foody.model.Meals;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FoodApi {
    @GET("latest.php")
    Call<Meals>getMeal();
}
