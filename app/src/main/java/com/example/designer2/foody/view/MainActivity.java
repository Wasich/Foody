package com.example.designer2.foody.view;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.designer2.foody.R;
import com.example.designer2.foody.Utils;
import com.example.designer2.foody.adapter.RecyclerViewMainAdapter;
import com.example.designer2.foody.adapter.ViewPagerHeaderAdapter;
import com.example.designer2.foody.model.Categories;
import com.example.designer2.foody.model.Meals;
import com.example.designer2.foody.view.category.CategoryActivity;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity  implements HomeView{

    public static final String EXTRA_CATEGORY = "category";
    public static final String EXTRA_POSITION = "position";
    public static final String EXTRA_DETAIL = "detail";






    @BindView(R.id.view_pager_header)
    ViewPager viewPagerMeal;
    @BindView(R.id.recyclerview_category)
    RecyclerView recyclerViewCategory;
    HomePresanter homePresanter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        homePresanter = new HomePresanter(this);
        homePresanter.getMeals();
        homePresanter.getCategories();
    }

    @Override
    public void showloading() {
        findViewById(R.id.foodymeal).setVisibility(View.VISIBLE);
        findViewById(R.id.foodycategory).setVisibility(View.VISIBLE);

    }

    @Override
    public void hideloading() {
        findViewById(R.id.foodymeal).setVisibility(View.GONE);
        findViewById(R.id.foodycategory).setVisibility(View.GONE);

    }

    @Override
    public void setMeal(List<Meals.Meal> meal) {
        ViewPagerHeaderAdapter headerAdapter = new ViewPagerHeaderAdapter(meal,this);
        viewPagerMeal.setAdapter(headerAdapter);
        viewPagerMeal.setPadding(20,0,150,0);
        headerAdapter.notifyDataSetChanged();


    }

    @Override
    public void setCategory(List<Categories.Category> category) {
        RecyclerViewMainAdapter mainAdapter = new RecyclerViewMainAdapter(category,this);
        recyclerViewCategory.setAdapter(mainAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this,3,GridLayoutManager.VERTICAL,false);
        recyclerViewCategory.setLayoutManager(layoutManager);
        recyclerViewCategory.setNestedScrollingEnabled(true);
        mainAdapter.notifyDataSetChanged();


        mainAdapter.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent(this,CategoryActivity.class);
            intent.putExtra(EXTRA_CATEGORY,(Serializable)category );
            intent.putExtra(EXTRA_POSITION,(Serializable)position );
            startActivity(intent);
        });

    }

    @Override
    public void onErrorLoading(String Message) {
        Utils.showDialogMessage(this,"title",Message);

    }
}
