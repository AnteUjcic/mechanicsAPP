package com.example.mechanicsapp;
import java.util.Collection;
import java.util.ArrayList;
public class Vehicle {
    public int IDVehicle;
    public String Brand;
    public int Year;
    public int NumberOfWheels;
    public String EngineType;
    public int NumberOfDoors;
    public String BodyStyle;
    public String DriveType;
    public Collection<Service> services = new ArrayList<Service>();

}
