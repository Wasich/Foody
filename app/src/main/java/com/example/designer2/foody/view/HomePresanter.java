package com.example.designer2.foody.view;

import android.support.annotation.NonNull;

import com.example.designer2.foody.Utils;
import com.example.designer2.foody.model.Categories;
import com.example.designer2.foody.model.Meals;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresanter {

    private HomeView view;

    public HomePresanter(HomeView view) {
        this.view = view;
    }
    void getMeals(){
        view.showloading();
        Call<Meals> mealsCall = Utils.getApi().getMeal();
        mealsCall.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(@NonNull Call<Meals> call,@NonNull Response<Meals> response) {
                view.hideloading();
                if (response.isSuccessful()&& response.body()!=null){
                    view.setMeal(response.body().getMeals());
                }
                else {
                    view.onErrorLoading(response.message());
                }

            }

            @Override
            public void onFailure(@NonNull Call<Meals> call,@NonNull Throwable t) {




                view.hideloading();
                view.onErrorLoading(t.getLocalizedMessage());

            }
        });




    }
    void getCategories(){
        view.showloading();
        Call<Categories> categoriesCall = Utils.getApi().getCategories();
        categoriesCall.enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(@NonNull Call<Categories> call,@NonNull Response<Categories> response) {
                view.hideloading();
                if (response.isSuccessful()&&response.body()!=null){
                    view.setCategory(response.body().getCategories());
                }
                else {
                    view.onErrorLoading(response.message());
                }

            }

            @Override
            public void onFailure(@NonNull Call<Categories> call,@NonNull Throwable t) {
                view.hideloading();
                view.onErrorLoading(t.getLocalizedMessage());

            }
        });
    }
}
