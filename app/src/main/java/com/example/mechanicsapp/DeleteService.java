package com.example.mechanicsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteService extends AppCompatActivity {
    private EditText editTextServiceId;
    private Button btnDeleteService,btnBackFromServiceDeletion;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_service);

        editTextServiceId = findViewById(R.id.editTextServiceId);
        btnDeleteService = findViewById(R.id.btnDeleteService);
        btnBackFromServiceDeletion=findViewById(R.id.btnBackFromDeleteService);
        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "mechanicsDB").build();

        btnDeleteService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteService();
            }
        });
        btnBackFromServiceDeletion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeleteService.this, HomePage.class);
                startActivity(intent);
            }
        });
    }


    private void deleteService() {
        String serviceIdString = editTextServiceId.getText().toString().trim();
        if (!serviceIdString.isEmpty()) {
            int serviceId = Integer.parseInt(serviceIdString);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    Service service = appDatabase.serviceDao().findServiceById(serviceId);
                    if (service != null) {
                        appDatabase.serviceDao().delete(service);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(DeleteService.this, "Service deleted successfully", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(DeleteService.this, "Service not found", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }).start();
        } else {
            Toast.makeText(this, "Please enter a valid Service ID", Toast.LENGTH_SHORT).show();
        }
    }


}
