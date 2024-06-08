package com.example.mechanicsapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;
import java.util.List;

@Dao
public interface VehicleDao {
    @Insert
    long insert(Vehicle vehicle);
    @Delete
    void delete(Service service);
    @Update
    void update(Vehicle vehicle);
    @Query("SELECT * FROM vehicle")
    List<Vehicle> getAllVehicles();
    @Query("SELECT * FROM vehicle WHERE idVehicle = :id LIMIT 1")
    Vehicle findVehicleById(int id);
}