package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormatSymbols;
import java.util.ArrayList;

public class ListOfReviews extends AppCompatActivity {

    private TextView ePlaceName;
    private ListView eListReviews;

    private Button ePrevious3;

    DatabaseHelper databaseHelper;
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_reviews2);
        setTitle("List of Reviews");
        Intent intent = getIntent();
        String theName = intent.getStringExtra("ReviewName");
        ePlaceName = findViewById(R.id.textViewPlaceName);
        eListReviews = findViewById(R.id.ListOfReviews);
        ePrevious3 = findViewById(R.id.buttonPreviousPlace3);
        ePlaceName.setText(theName);
        databaseHelper = new DatabaseHelper(ListOfReviews.this);
        String inputSearch = ePlaceName.getText().toString();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ArrayList<String> arrayList = new ArrayList<String>();
        Cursor cursor = db.rawQuery("select * from Table2 where PTable = '"+inputSearch+"'",null);
        if(cursor.getCount()==0){
            Toast.makeText(ListOfReviews.this,"Np Reviews Entered",Toast.LENGTH_SHORT).show();
        }
        else{
            cursor.moveToFirst();
            String toAdd;
            while(!cursor.isAfterLast()){
                toAdd = "Rating = " + cursor.getString(1) + "\nComment = " + cursor.getString(2);
                arrayList.add(toAdd);
                cursor.moveToNext();
            }
        }
        arrayAdapter = new ArrayAdapter(ListOfReviews.this, android.R.layout.simple_expandable_list_item_1, arrayList);
        eListReviews.setAdapter(arrayAdapter);
        ePrevious3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListOfReviews.this, SearchPage.class);
                startActivity(intent);
            }
        });

    }
}