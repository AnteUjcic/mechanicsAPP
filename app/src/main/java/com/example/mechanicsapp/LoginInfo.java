package com.example.mechanicsapp;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "login_info",
        foreignKeys = @ForeignKey(entity = Worker.class,
                parentColumns = "oib",
                childColumns = "oib"))
public class LoginInfo {
    @PrimaryKey(autoGenerate = true)
    public long idLogin;
    public String username;
    public String password;

    public long oib;
}