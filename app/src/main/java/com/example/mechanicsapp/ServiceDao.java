package com.example.mechanicsapp;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ServiceDao {
    @Insert
    void insert(Service service);

    @Query("SELECT * FROM service")
    List<Service> getAllServices();

    // Add other queries as needed
}