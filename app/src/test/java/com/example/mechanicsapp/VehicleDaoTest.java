package com.example.mechanicsapp;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import androidx.test.runner.AndroidJUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class VehicleDaoTest {
    private AppDatabase appDatabase;
    private VehicleDao vehicleDao;

    @Before
    public void initDb() {
        appDatabase = Room.inMemoryDatabaseBuilder(
                        ApplicationProvider.getApplicationContext(),
                        AppDatabase.class
                )
                .allowMainThreadQueries()
                .build();
        vehicleDao = appDatabase.vehicleDao();
    }

    @After
    public void closeDb() {
        appDatabase.close();
    }

    @Test
    public void testInsert() {
        Vehicle vehicle = new Vehicle();
        vehicle.idVehicle = 1;
        vehicle.brand = "Toyota";
        vehicle.year = 2020;
        vehicle.numberOfWheels = 4;
        vehicle.engineType = "Hybrid";
        vehicle.numberOfDoors = 4;
        vehicle.bodyStyle = "Sedan";
        vehicle.driveType = "FWD";

        long id = vehicleDao.insert(vehicle);
        Vehicle foundVehicle = vehicleDao.findVehicleById((int) id);

        assertNotNull(foundVehicle);
        assertEquals(vehicle.brand, foundVehicle.brand);
        assertEquals(vehicle.year, foundVehicle.year);
        assertEquals(vehicle.numberOfWheels, foundVehicle.numberOfWheels);
        assertEquals(vehicle.engineType, foundVehicle.engineType);
        assertEquals(vehicle.numberOfDoors, foundVehicle.numberOfDoors);
        assertEquals(vehicle.bodyStyle, foundVehicle.bodyStyle);
        assertEquals(vehicle.driveType, foundVehicle.driveType);
    }

    @Test
    public void testUpdateVehicle() {
        Vehicle vehicle = new Vehicle();
        vehicle.idVehicle = 1;
        vehicle.brand = "Toyota";
        vehicle.year = 2020;
        vehicle.numberOfWheels = 4;
        vehicle.engineType = "Hybrid";
        vehicle.numberOfDoors = 4;
        vehicle.bodyStyle = "Sedan";
        vehicle.driveType = "FWD";

        vehicleDao.insert(vehicle);
        Vehicle foundVehicle = vehicleDao.findVehicleById(vehicle.idVehicle);

        foundVehicle.brand = "Honda";
        vehicleDao.update(foundVehicle);

        Vehicle updatedVehicle = vehicleDao.findVehicleById(vehicle.idVehicle);
        assertEquals("Honda", updatedVehicle.brand);
    }

}
