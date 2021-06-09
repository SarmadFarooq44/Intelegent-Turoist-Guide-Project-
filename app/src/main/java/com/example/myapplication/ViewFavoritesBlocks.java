package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewFavoritesBlocks extends AppCompatActivity {

    private Button eHomePre;
    private Button eViewBlocks;
    private Button eViewFavorites;
    private ListView elistView;
    private ArrayList<String> list;
    DatabaseHelper databaseHelper;
    ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_favorites_blocks);
        eHomePre = findViewById(R.id.buttonPreviousPlace4);
        eViewBlocks = findViewById(R.id.ViewBlocks);
        eViewFavorites = findViewById(R.id.ViewFavorites);
        elistView = findViewById(R.id.listViewOfBlockFav);
        databaseHelper = new DatabaseHelper(ViewFavoritesBlocks.this);
        eHomePre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewFavoritesBlocks.this, HomePage.class);
                startActivity(intent);
            }
        });
        eViewFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list=databaseHelper.getAllFavorites();
                arrayAdapter=new ArrayAdapter(ViewFavoritesBlocks.this,android.R.layout.simple_expandable_list_item_1,list);
                elistView.setAdapter(arrayAdapter);
            }
        });
        eViewBlocks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list=databaseHelper.getAllBlocks();
                arrayAdapter=new ArrayAdapter(ViewFavoritesBlocks.this,android.R.layout.simple_expandable_list_item_1,list);
                elistView.setAdapter(arrayAdapter);
            }
        });
    }
}