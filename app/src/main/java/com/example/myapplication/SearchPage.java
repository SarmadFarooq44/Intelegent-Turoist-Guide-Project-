package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchPage extends AppCompatActivity {

    private Button eAddPlaces;

    private Button AddFavorites;
    private Button BlockObject;

    private Button eHome;
    private Button eFind;
    private Button eReadReview;
    private Button eWriteReview;

    private EditText eSearchPlace;
    private TextView eName;
    private TextView eOpen;
    private TextView eClose;
    private TextView eDescription;

    DatabaseHelper databaseHelper;
    /*ArrayList arrayList;
    ArrayAdapter arrayAdapter;
    arrayList = databaseHelper.getAllData();
    arrayAdapter = new ArrayAdapter(SearchPage.this, android.R.layout.simple_list_item_1, arrayList);*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);
        setTitle("Searching");
        AddFavorites = findViewById(R.id.AddFavorites);
        BlockObject = findViewById(R.id.BlockObject);
        eAddPlaces = findViewById(R.id.buttonAddPlaces);
        eHome = findViewById(R.id.buttonHome);
        eFind = findViewById(R.id.buttonFind);
        eReadReview = findViewById(R.id.buttonRead);
        eWriteReview = findViewById(R.id.buttonWrite);
        eSearchPlace = findViewById(R.id.editTextSearchBar);
        eName = findViewById(R.id.textName);
        eOpen = findViewById(R.id.textOpening);
        eClose = findViewById(R.id.textClosing);
        eDescription = findViewById(R.id.textDescription);
        eReadReview.setVisibility(View.INVISIBLE);
        eWriteReview.setVisibility(View.INVISIBLE);
        AddFavorites.setVisibility(View.INVISIBLE);
        BlockObject.setVisibility(View.INVISIBLE);
        eAddPlaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchPage.this, AddPlaces.class);
                startActivity(intent);
            }
        });
        eHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchPage.this, HomePage.class);
                startActivity(intent);
            }
        });
        databaseHelper = new DatabaseHelper(SearchPage.this);
        eFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputSearch = eSearchPlace.getText().toString();
                SQLiteDatabase db = databaseHelper.getWritableDatabase();
                Cursor cursor = db.rawQuery("select * from Table1 where Name = '"+inputSearch+"'",null);
                if(cursor.getCount()==0){
                    Toast.makeText(SearchPage.this,"Place not found",Toast.LENGTH_SHORT).show();
                }
                else{
                    eReadReview.setVisibility(View.VISIBLE);
                    eWriteReview.setVisibility(View.VISIBLE);
                    AddFavorites.setVisibility(View.VISIBLE);
                    BlockObject.setVisibility(View.VISIBLE);
                    cursor.moveToFirst();
                    eName.setText(cursor.getString(0));
                    eOpen.setText("Opening = " + cursor.getString(1));
                    eClose.setText("Closing = " + cursor.getString(2));
                    eDescription.setText("Description = " + cursor.getString(3));
                }
            }
        });
        AddFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputName = eSearchPlace.getText().toString();
                databaseHelper.addFavorites(inputName);
                Toast.makeText(SearchPage.this, "Added to Favorites", Toast.LENGTH_SHORT).show();
            }
        });
        BlockObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputName = eSearchPlace.getText().toString();
                databaseHelper.addBlocks(inputName);
                Toast.makeText(SearchPage.this, "Added to Block List", Toast.LENGTH_SHORT).show();
            }
        });
        eWriteReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchPage.this, AddReview.class);
                String inputReview = eName.getText().toString();
                intent.putExtra("ReviewName",inputReview);
                startActivity(intent);
            }
        });
        eReadReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchPage.this, ListOfReviews.class);
                String inputReview = eName.getText().toString();
                intent.putExtra("ReviewName",inputReview);
                startActivity(intent);
            }
        });
    }
}
