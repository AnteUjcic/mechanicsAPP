package com.example.mechanicsapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpisServisa extends AppCompatActivity {
    private EditText editTextServiceType, editTextDateOfService, editTextKmService, editTextNextKmService, editTextOIB, editTextVehicleId;
    private Button btnAddService, btnBackToAdminFromAddService;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upisservisavozila);

        editTextServiceType = findViewById(R.id.editTextServiceType);
        editTextDateOfService = findViewById(R.id.editTextDateOfService);
        editTextKmService = findViewById(R.id.editTextKmService);
        editTextNextKmService = findViewById(R.id.editTextNextKmService);
        editTextOIB = findViewById(R.id.editTextOIB);
        editTextVehicleId = findViewById(R.id.editTextVehicleId);
        btnAddService = findViewById(R.id.btnAddService);
        btnBackToAdminFromAddService = findViewById(R.id.btnBackToAdminFromAddService);

        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "mechanicsDB")
                .fallbackToDestructiveMigration()
                .build();

        btnAddService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String serviceType = editTextServiceType.getText().toString().trim();
                String dateOfServiceStr = editTextDateOfService.getText().toString().trim();
                double kmService = Double.parseDouble(editTextKmService.getText().toString().trim());
                double nextKmService = Double.parseDouble(editTextNextKmService.getText().toString().trim());
                long oib = Long.parseLong(editTextOIB.getText().toString().trim());
                int vehicleId = Integer.parseInt(editTextVehicleId.getText().toString().trim());

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date dateOfService = null;
                try {
                    dateOfService = sdf.parse(dateOfServiceStr);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if (dateOfService != null && !serviceType.isEmpty() && kmService > 0 && nextKmService > 0 && oib > 0 && vehicleId > 0) {
                    Service newService = new Service();
                    newService.serviceType = serviceType;
                    newService.dateOfService = dateOfService;
                    newService.kmService = kmService;
                    newService.nextKmService = nextKmService;
                    newService.oib = oib;
                    newService.vehicleId = vehicleId;

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            appDatabase.serviceDao().insert(newService);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(UpisServisa.this, "Service added successfully", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            });
                        }
                    }).start();
                } else {
                    Toast.makeText(UpisServisa.this, "Please fill all fields correctly", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnBackToAdminFromAddService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
