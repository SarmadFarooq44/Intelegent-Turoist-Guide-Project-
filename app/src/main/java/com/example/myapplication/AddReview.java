package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddReview extends AppCompatActivity {

    private Button ePrevious2;
    private Button eSubmitReview;

    private EditText eRatingText;
    private EditText eCommentText;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);
        setTitle("Add Review");
        Intent intent = getIntent();
        String theName = intent.getStringExtra("ReviewName");
        ePrevious2 = findViewById(R.id.buttonPreviousPlace2);
        eSubmitReview = findViewById(R.id.buttonSubmitReview);
        eRatingText = findViewById(R.id.editTextRating);
        eCommentText = findViewById(R.id.editTextComment);
        ePrevious2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddReview.this, SearchPage.class);
                startActivity(intent);
            }
        });
        databaseHelper = new DatabaseHelper(AddReview.this);
        eSubmitReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputRating = eRatingText.getText().toString();
                int intRating = Integer.parseInt(inputRating);
                String inputComment = eCommentText.getText().toString();
                if(inputRating.isEmpty() || inputComment.isEmpty()){
                    Toast.makeText(AddReview.this,"Fill in all the details",Toast.LENGTH_SHORT).show();
                }
                else if(intRating<0 || intRating>5){
                    Toast.makeText(AddReview.this,"Enter Rating from 1 and 5",Toast.LENGTH_SHORT).show();
                }
                else{
                    databaseHelper.addReview(inputRating,inputComment,theName);
                    Toast.makeText(AddReview.this,"Data Entered",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddReview.this, SearchPage.class);
                    startActivity(intent);
                }
            }
        });
    }
}