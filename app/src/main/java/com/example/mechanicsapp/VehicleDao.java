package com.example.mechanicsapp;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface VehicleDao {
    @Insert
    void insert(Vehicle vehicle);

    @Query("SELECT * FROM vehicle")
    List<Vehicle> getAllVehicles();

    // Add other queries as needed
}