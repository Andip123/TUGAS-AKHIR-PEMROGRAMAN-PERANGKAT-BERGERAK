package com.example.tugasakhir.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.tugasakhir.Adapter.CategoryAdapter;
import com.example.tugasakhir.Adapter.RecommendedAdapter;
import com.example.tugasakhir.Domain.CategoryDomain;
import com.example.tugasakhir.Domain.FoodDomain;
import com.example.tugasakhir.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private RecyclerView.Adapter adapter,adapter2;
private RecyclerView recyclerViewCategotyList,recyclerViewPopularList;
ImageView imgtes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgtes = findViewById(R.id.imgtes);
        imgtes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(it);
            }
        });
        recyclerViewCategoty();
        recyclerViewPopular();
    }

    private void recyclerViewPopular() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewPopularList=findViewById(R.id.view2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        ArrayList<FoodDomain> foodlist = new ArrayList<>();
        foodlist.add(new FoodDomain("Rucas season 9", "car_1", "T-shirt season 9 dengan warna hitam limited editon", 750.000, 3, 20, 30));
        foodlist.add(new FoodDomain("Rucas flanel ", "car_2", "Flanel Rucas onyx, sapphire, dan rubby limited editon", 500.000, 3, 20, 30));
        foodlist.add(new FoodDomain("Rucas season 4", "car_3", "celana panjang rucas season 5 dengan tiga pilihan graphite, shawdon black dan ceramic gray", 850.000, 3, 20, 30));
        foodlist.add(new FoodDomain("Sepatu coompass","car_4", "Sepatu compass black white",500.000, 2, 10, 20));
        foodlist.add(new FoodDomain("Rucas season 8","car_5","Celana jeans season 8 dengan kombinasi leater pada celana", 950.00,3,3,3));

        adapter2=new RecommendedAdapter(foodlist);
        recyclerViewPopularList.setAdapter(adapter2);
    }

    private void recyclerViewCategoty(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this ,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewCategotyList=findViewById(R.id.view1);
        recyclerViewCategotyList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> categoryList = new ArrayList<>();
        categoryList.add(new CategoryDomain("T-shirt","car_1"));
        categoryList.add(new CategoryDomain("Flanel", "car_2"));
        categoryList.add(new CategoryDomain("Jeans", "car_3"));
        categoryList.add(new CategoryDomain("Shoes", "car_4"));

        adapter= new CategoryAdapter(categoryList);
        recyclerViewCategotyList.setAdapter(adapter);
    }
}