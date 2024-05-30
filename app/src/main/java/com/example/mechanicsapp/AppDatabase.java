package com.example.mechanicsapp;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {LoginInfo.class, Worker.class, Service.class, Vehicle.class}, version = 2)
@TypeConverters({DateConverter.class})

public abstract class AppDatabase extends RoomDatabase {
    public abstract LoginDao loginDao();
    public abstract WorkerDao workerDao();
    public abstract ServiceDao serviceDao();
    public abstract VehicleDao vehicleDao();
}
