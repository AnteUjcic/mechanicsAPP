package com.example.mechanicsapp;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Ignore;
import java.util.List;

@Entity(tableName = "worker")
public class Worker {
    @PrimaryKey
    public long oib;
    public String workerName;
    public String workerSurname;
    @Ignore
    public List<Service> services;
    @Ignore
    public LoginInfo loginInfo;
}
