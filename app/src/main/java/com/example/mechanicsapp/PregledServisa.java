package com.example.mechanicsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.List;

public class PregledServisa extends AppCompatActivity {
    private ListView listViewServices;
    private AppDatabase appDatabase;
    private Button btnRefresh, btnBackToHomeFromServiceOverview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregledservisa);

        listViewServices = findViewById(R.id.listViewServices);
        btnRefresh = findViewById(R.id.btnRefresh);
        btnBackToHomeFromServiceOverview = findViewById(R.id.btnBackToAdminFromServiceOverview);

        btnBackToHomeFromServiceOverview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PregledServisa.this, HomePage.class);
                startActivity(intent);
            }
        });

        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "mechanicsDB")
                .fallbackToDestructiveMigration()
                .build();

        loadServices();

        btnRefresh.setOnClickListener(v -> loadServices());
    }

    private void loadServices() {
        new Thread(() -> {
            List<Service> serviceList = appDatabase.serviceDao().getAllServices();
            runOnUiThread(() -> {
                ServiceAdapter adapter = new ServiceAdapter(PregledServisa.this, serviceList);
                listViewServices.setAdapter(adapter);
            });
        }).start();
    }
}
