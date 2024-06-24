package com.example.mechanicsapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class DeleteUser extends AppCompatActivity {
    private EditText editOIB;
    private Button btnDeleteUser,btnReturnToAdminFromDeleteUser;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brisanjekorisnika);

        editOIB = findViewById(R.id.editTextNumber);
        btnDeleteUser = findViewById(R.id.btnDeleteUser);
        btnReturnToAdminFromDeleteUser=findViewById(R.id.btnReturnToAdminFromDeleteUser);

        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "mechanicsDB")
                .fallbackToDestructiveMigration()
                .build();
        btnReturnToAdminFromDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeleteUser.this, HomePage.class);
                startActivity(intent);
            }
        });
        btnDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oibStr = editOIB.getText().toString().trim();
                try {
                    Long OIB = Long.parseLong(oibStr);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            LoginInfo user = appDatabase.loginDao().getLoginInfoByOib(OIB);
                            if (user != null) {
                                appDatabase.loginDao().deleteByOib(OIB);


                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(DeleteUser.this, "User deleted successfully", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                });
                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(DeleteUser.this, "No user found with the given OIB", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    }).start();
                } catch (NumberFormatException e) {
                    Toast.makeText(DeleteUser.this, "Please enter a valid OIB", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }
}
