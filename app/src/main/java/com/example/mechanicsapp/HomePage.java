package com.example.mechanicsapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {
    private Button btnLogoutUser,btnAddVehicle,btnPregledVozila,btnAddService,btnPregledServisa,btnDeleteService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        btnLogoutUser = findViewById(R.id.btnLogoutUser);
        btnPregledVozila = findViewById(R.id.btnPregledVozila);
        btnAddService = findViewById(R.id.btnUpisServisa);
        btnPregledServisa=findViewById(R.id.btnPregledServisa);
        btnDeleteService=findViewById(R.id.btnDeleteService);
        btnLogoutUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnAddVehicle = findViewById(R.id.btnUpisNovogVozila);
        btnAddVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, UpisNovogVozila.class);
                startActivity(intent);
            }
        });
        btnPregledVozila.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, PregledVozila.class);
                startActivity(intent);
            }
        });
        btnAddService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, UpisServisa.class);
                startActivity(intent);
            }
        });
        btnPregledServisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, PregledServisa.class);
                startActivity(intent);
            }
        });
        btnDeleteService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, DeleteService.class);
                startActivity(intent);
            }
        });
    }
}