package com.example.mechanicsapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.List;

public class PregledVozila extends AppCompatActivity {
    private ListView listViewVehicles;
    private AppDatabase appDatabase;
    private Button btnRefresh,btnBackToHomePageFromVehicleOverview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregledvozila);

        listViewVehicles = findViewById(R.id.listViewVehicles);
        btnRefresh = findViewById(R.id.btnRefresh);
        btnBackToHomePageFromVehicleOverview = findViewById(R.id.btnBackToAdminFromVehicleOverview);

        btnBackToHomePageFromVehicleOverview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PregledVozila.this, HomePage.class);
                startActivity(intent);
            }
        });

        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "mechanicsDB")
                .fallbackToDestructiveMigration()
                .build();

        loadVehicles();

        btnRefresh.setOnClickListener(v -> loadVehicles());


    }

    private void loadVehicles() {
        new Thread(() -> {
            List<Vehicle> vehicleList = appDatabase.vehicleDao().getAllVehicles();
            runOnUiThread(() -> {
                VehicleAdapter adapter = new VehicleAdapter(PregledVozila.this, vehicleList);
                listViewVehicles.setAdapter(adapter);
            });
        }).start();
    }
}
