package com.example.mechanicsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.List;

public class PregledKorisnika extends AppCompatActivity {
    private ListView listViewUsers;
    private AppDatabase appDatabase;
    private Button btnRefresh,btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregledkorisnika);

        listViewUsers = findViewById(R.id.listViewUsers);
        btnRefresh = findViewById(R.id.btnRefresh);
        btnBack=findViewById(R.id.btnBackToAdminScreen);
        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "mechanicsDB")
                .fallbackToDestructiveMigration()
                .build();

        loadUsers();

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadUsers();
            }

        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PregledKorisnika.this, AdminScreen.class);
                startActivity(intent);
            }
        });
    }
    private void loadUsers() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<LoginInfo> userList = appDatabase.loginDao().getAllLogins();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        LoginInfoAdapter adapter = new LoginInfoAdapter(PregledKorisnika.this, userList);
                        listViewUsers.setAdapter(adapter);
                    }
                });
            }
        }).start();
    }
}
