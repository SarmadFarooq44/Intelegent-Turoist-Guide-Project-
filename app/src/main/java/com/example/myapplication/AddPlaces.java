package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddPlaces extends AppCompatActivity {

    private Button ePrevious;
    private Button eSubmit;
    private EditText eNameOfPlace;
    private EditText eOpenOfPlace;
    private EditText eCloseOfPlace;
    private EditText eDescriptionOfPlace;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_places);
        setTitle("Add Places");
        ePrevious = findViewById(R.id.buttonPreviousPlace);
        eSubmit = findViewById(R.id.buttonSubmitPlace);
        eNameOfPlace = findViewById(R.id.editNameOfPlace);
        eOpenOfPlace = findViewById(R.id.editOpenOfPlace);
        eCloseOfPlace = findViewById(R.id.editCloseOfPlace);
        eDescriptionOfPlace = findViewById(R.id.editDescriptionOfPlace);
        ePrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddPlaces.this, SearchPage.class);
                startActivity(intent);
            }
        });

        databaseHelper = new DatabaseHelper(AddPlaces.this);
        eSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputName = eNameOfPlace.getText().toString();
                String inputOpen = eOpenOfPlace.getText().toString();
                String inputClose = eCloseOfPlace.getText().toString();
                String inputDescription = eDescriptionOfPlace.getText().toString();
                if(inputName.isEmpty() || inputOpen.isEmpty() || inputClose.isEmpty() || inputDescription.isEmpty()){
                    Toast.makeText(AddPlaces.this,"Fill in all the details",Toast.LENGTH_SHORT).show();
                }
                else {
                    databaseHelper.addText(inputName,inputOpen,inputClose,inputDescription);
                    eNameOfPlace.setText("");
                    eCloseOfPlace.setText("");
                    eOpenOfPlace.setText("");
                    eDescriptionOfPlace.setText("");
                    Toast.makeText(AddPlaces.this,"Data Entered",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}