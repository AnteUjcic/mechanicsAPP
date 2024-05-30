package com.example.mechanicsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class UpisNovogVozila extends AppCompatActivity {
    private EditText editTextIdVehicle, editTextBrand, editTextYear, editTextNumberOfWheels, editTextEngineType, editTextNumberOfDoors, editTextBodyStyle, editTextDriveType;
    private Button btnAddVehicle,btnBackToHomeFromAddVehicle;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upisnovogvozila);

        editTextIdVehicle = findViewById(R.id.editTextIdVehicle);
        editTextBrand = findViewById(R.id.editTextBrand);
        editTextYear = findViewById(R.id.editTextYear);
        editTextNumberOfWheels = findViewById(R.id.editTextNumberOfWheels);
        editTextEngineType = findViewById(R.id.editTextEngineType);
        editTextNumberOfDoors = findViewById(R.id.editTextNumberOfDoors);
        editTextBodyStyle = findViewById(R.id.editTextBodyStyle);
        editTextDriveType = findViewById(R.id.editTextDriveType);
        btnAddVehicle = findViewById(R.id.btnAddVehicle);
        btnBackToHomeFromAddVehicle = findViewById(R.id.btnBackToHomeFromAddVehicle);
        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "mechanicsDB")
                .fallbackToDestructiveMigration()
                .build();
        btnBackToHomeFromAddVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpisNovogVozila.this, HomePage.class);
                startActivity(intent);
            }
        });
        btnAddVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idVehicle = Integer.parseInt(editTextIdVehicle.getText().toString().trim());
                String brand = editTextBrand.getText().toString().trim();
                int year = Integer.parseInt(editTextYear.getText().toString().trim());
                int numberOfWheels = Integer.parseInt(editTextNumberOfWheels.getText().toString().trim());
                String engineType = editTextEngineType.getText().toString().trim();
                int numberOfDoors = Integer.parseInt(editTextNumberOfDoors.getText().toString().trim());
                String bodyStyle = editTextBodyStyle.getText().toString().trim();
                String driveType = editTextDriveType.getText().toString().trim();

                if (!brand.isEmpty() && year > 0 && numberOfWheels > 0 && !engineType.isEmpty() && numberOfDoors > 0 && !bodyStyle.isEmpty() && !driveType.isEmpty()) {
                    Vehicle newVehicle = new Vehicle();
                    newVehicle.idVehicle = idVehicle;
                    newVehicle.brand = brand;
                    newVehicle.year = year;
                    newVehicle.numberOfWheels = numberOfWheels;
                    newVehicle.engineType = engineType;
                    newVehicle.numberOfDoors = numberOfDoors;
                    newVehicle.bodyStyle = bodyStyle;
                    newVehicle.driveType = driveType;

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            appDatabase.vehicleDao().insert(newVehicle);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(UpisNovogVozila.this, "Vehicle added successfully", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            });
                        }
                    }).start();
                } else {
                    Toast.makeText(UpisNovogVozila.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
