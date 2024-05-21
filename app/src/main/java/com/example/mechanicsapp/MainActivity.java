package com.example.mechanicsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText username, password;
    private Button signing;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.txtUsernameInput);
        password = findViewById(R.id.txtPasswordInput);
        signing = findViewById(R.id.btnPrijava);

        // Initialize the database
        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "mechanicsDB").build();

        signing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputUsername = username.getText().toString().trim();
                String inputPassword = password.getText().toString().trim();

                // Check if the entered username and password match the hardcoded admin credentials
                if (inputUsername.equals("admin") && inputPassword.equals("admin")) {
                    // Successful login
                    Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    // Start AdminScreen activity
                    Intent intent = new Intent(MainActivity.this, AdminScreen.class);
                    startActivity(intent);
                } else {
                    // Check if the entered username and password match the database records
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            LoginInfo user = appDatabase.loginDao().login(inputUsername, inputPassword);
                            if (user != null) {
                                // Successful login
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                                        // Start AdminScreen activity
                                        Intent intent = new Intent(MainActivity.this, HomePage.class);
                                        startActivity(intent);
                                    }
                                });
                            } else {
                                // Login failed
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    }).start();
                }
            }
        });
    }
}
