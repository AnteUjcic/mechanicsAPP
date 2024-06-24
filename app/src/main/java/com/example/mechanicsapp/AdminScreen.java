package com.example.mechanicsapp;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
public class AdminScreen extends AppCompatActivity {
     Button btnBackupDatabase,btnDodajKorisnika, btnBrisanjeKorisnika, btnPregledKorisnika, btnAdminLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminscreen);

        btnDodajKorisnika = findViewById(R.id.btnDodajKorisnika);
        btnBrisanjeKorisnika = findViewById(R.id.btnBrisanjeKorisnika);
        btnPregledKorisnika = findViewById(R.id.btnPregledKorisnika);
        btnAdminLogout = findViewById(R.id.btnAdminLogout);
        btnBackupDatabase = findViewById(R.id.btnBackupDatabase);

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
        btnBackupDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backupDatabase();
            }
        });
    }
    private void backupDatabase() {
        File dbFile = getDatabasePath("mechanicsDB");
        File backupFile = new File(getExternalFilesDir(null), "mechanicsDB_backup.db");

        try {
            copyFile(dbFile, backupFile);
            Toast.makeText(this, "Database backup successful: " + backupFile.getAbsolutePath(), Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Database backup failed", Toast.LENGTH_LONG).show();
        }
    }
    private void copyFile(File src, File dst) throws IOException {
        try (FileInputStream in = new FileInputStream(src);
             FileOutputStream out = new FileOutputStream(dst)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
        }
    }
}