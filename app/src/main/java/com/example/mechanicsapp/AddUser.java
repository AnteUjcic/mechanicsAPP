package com.example.mechanicsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddUser extends AppCompatActivity {
    private EditText editTextUsername, editTextPassword, editOIB, editTextName, editTextSurname;
    private Button btnAddUser;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adduser);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editOIB = findViewById(R.id.editTextNumber);
        editTextName = findViewById(R.id.txtUnosImenaKorisnika);
        editTextSurname = findViewById(R.id.txtUnosPrezimenaKorisnika);
        btnAddUser = findViewById(R.id.btnAddUser);

        // Initialize the database
        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "mechanicsDB").build();

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String name = editTextName.getText().toString().trim();
                String surname = editTextSurname.getText().toString().trim();
                Long OIB = Long.parseLong(editOIB.getText().toString());

                if (!username.isEmpty() && !password.isEmpty() && !name.isEmpty() && !surname.isEmpty()) {
                    // Add user to the database
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            long workerOIB = OIB;
                            Worker existingWorker = appDatabase.workerDao().findWorkerByOIB(workerOIB);

                            if (existingWorker == null) {
                                // If worker doesn't exist, create a new worker
                                Worker newWorker = new Worker();
                                newWorker.oib = workerOIB;
                                newWorker.workerName = name;
                                newWorker.workerSurname = surname;
                                appDatabase.workerDao().insert(newWorker);
                            }

                            LoginInfo newUser = new LoginInfo();
                            newUser.username = username;
                            newUser.password = password;
                            newUser.oib = workerOIB;
                            appDatabase.loginDao().insert(newUser);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(AddUser.this, "User added successfully", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            });
                        }
                    }).start();
                } else {
                    Toast.makeText(AddUser.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
