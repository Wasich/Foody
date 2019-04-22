package com.example.designer2.foody.view;

import com.example.designer2.foody.model.Categories;
import com.example.designer2.foody.model.Meals;

import java.util.List;

public interface HomeView {
    void showloading();
    void hideloading();
    void setMeal(List<Meals.Meal>meal);
    void setCategory(List<Categories.Category>category);
    void onErrorLoading(String Message);

}
