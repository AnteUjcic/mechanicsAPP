package com.example.mechanicsapp;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import java.time.LocalDate;
@Entity(tableName = "service",
        foreignKeys = {
                @ForeignKey(entity = Worker.class,
                        parentColumns = "oib",
                        childColumns = "workerOIB"),
                @ForeignKey(entity = Vehicle.class,
                        parentColumns = "idVehicle",
                        childColumns = "vehicleId")
        })

public class Service {
    @PrimaryKey(autoGenerate = true)
    public int serviceId;

    public String serviceType;
    public LocalDate dateOfService;
    public double kmService;
    public double nextKmService;
    public long oib;

    public int vehicleId;
}
