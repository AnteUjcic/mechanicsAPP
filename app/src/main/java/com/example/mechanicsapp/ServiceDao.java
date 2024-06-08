package com.example.mechanicsapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;

import java.util.List;

@Dao
public interface ServiceDao {
    @Insert
    long insert(Service service);

    @Update
    void update(Service service);

    @Delete
    void delete(Service service);

    @Query("SELECT * FROM service WHERE serviceId = :id LIMIT 1")
    Service findServiceById(int id);

    @Query("SELECT * FROM service")
    List<Service> getAllServices();
}
