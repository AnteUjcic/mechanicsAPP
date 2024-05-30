package com.example.mechanicsapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminScreen extends AppCompatActivity {
     Button btnDodajKorisnika, btnBrisanjeKorisnika, btnPregledKorisnika, btnAdminLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminscreen);

        btnDodajKorisnika = findViewById(R.id.btnDodajKorisnika);
        btnBrisanjeKorisnika = findViewById(R.id.btnBrisanjeKorisnika);
        btnPregledKorisnika = findViewById(R.id.btnPregledKorisnika);
        btnAdminLogout = findViewById(R.id.btnAdminLogout);

        btnDodajKorisnika.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminScreen.this, AddUser.class);
                startActivity(intent);
            }
        });
        btnAdminLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminScreen.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnBrisanjeKorisnika.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminScreen.this, DeleteUser.class);
                startActivity(intent);
            }
        });
        btnPregledKorisnika.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminScreen.this, PregledKorisnika.class);
                startActivity(intent);
            }
        });
    }
}