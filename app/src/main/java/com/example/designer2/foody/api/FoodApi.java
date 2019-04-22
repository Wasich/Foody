package com.example.designer2.foody.api;

import com.example.designer2.foody.model.Categories;
import com.example.designer2.foody.model.Meals;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FoodApi {
    @GET("latest.php")
    Call<Meals>getMeal();

    @GET("categories.php")
    Call<Categories>getCategories();


    @GET("filter.php")
    Call<Meals> getMealByCategory(@Query("c") String category);

}
