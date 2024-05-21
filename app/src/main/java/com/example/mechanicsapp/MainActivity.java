package com.example.mechanicsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
     AppDatabase appDatabase;
     EditText username, password;
     Button signing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.txtUsernameInput);
        password = findViewById(R.id.txtPasswordInput);
        signing = findViewById(R.id.btnPrijava);
        signing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputUsername = username.getText().toString().trim();
                String inputPassword = password.getText().toString().trim();

                // Check if the entered username and password match the hardcoded values
                if (inputUsername.equals("admin") && inputPassword.equals("admin")) {
                    // Successful login
                    Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    // You can start a new activity here if needed
                    // Intent intent = new Intent(MainActivity.this, NextActivity.class);
                    // startActivity(intent);
                } else {
                    // Login failed
                    Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}