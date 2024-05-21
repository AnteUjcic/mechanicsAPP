package com.example.mechanicsapp;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "vehicle")
public class Vehicle {
    @PrimaryKey
    public int idVehicle;
    public String brand;
    public int year;
    public int numberOfWheels;
    public String engineType;
    public int numberOfDoors;
    public String bodyStyle;
    public String driveType;
}
