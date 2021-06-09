package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText eName;
    private EditText ePassword;
    private Button eLogin;

    private final String testUserName = "Sarmad";
    private final String testPassword = "SAR123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Login Screen");
        eName = findViewById(R.id.editTextName);
        ePassword = findViewById(R.id.editTextPassword);
        eLogin = findViewById(R.id.buttonLogin);
        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputName = eName.getText().toString();
                String inputPassword = ePassword.getText().toString();
                Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, HomePage.class);
                startActivity(intent);
                /*if(inputName.equals(testUserName) && inputPassword.equals(testPassword)){
                    Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, HomePage.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this,"Login Not Successful",Toast.LENGTH_SHORT).show();
                }*/
            }
        });
    }
}