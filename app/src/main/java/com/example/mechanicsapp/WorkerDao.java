package com.example.mechanicsapp;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WorkerDao {
    @Insert
    void insert(Worker worker);

    @Query("SELECT * FROM worker")
    List<Worker> getAllWorkers();

    // Add other queries as needed
}