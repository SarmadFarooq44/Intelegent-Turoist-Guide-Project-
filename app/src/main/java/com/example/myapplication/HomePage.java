package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {

    private Button eSearch;
    private Button ViewFavoritesBlocks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        setTitle("Main Menu");
        ViewFavoritesBlocks=findViewById(R.id.ViewFavoritesBlocks);
        eSearch = findViewById(R.id.buttonSearch);
        eSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, SearchPage.class);
                startActivity(intent);
            }
        });
        ViewFavoritesBlocks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, ViewFavoritesBlocks.class);
                startActivity(intent);
            }
        });
    }
}