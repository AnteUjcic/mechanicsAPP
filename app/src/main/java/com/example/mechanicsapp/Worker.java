package com.example.mechanicsapp;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "worker")
public class Worker {
    @PrimaryKey
    public long oib;
    public String workerName;
    public String workerSurname;
    // Assuming LoginInfo is a separate entity
}
