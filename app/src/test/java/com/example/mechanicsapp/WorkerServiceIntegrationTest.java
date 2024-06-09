package com.example.mechanicsapp;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import androidx.test.runner.AndroidJUnit4;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(AndroidJUnit4.class)
public class WorkerServiceIntegrationTest {
    private AppDatabase appDatabase;
    private WorkerDao workerDao;
    private VehicleDao vehicleDao;
    private ServiceDao serviceDao;

    @Before
    public void initDb() {
        appDatabase = Room.inMemoryDatabaseBuilder(
                        ApplicationProvider.getApplicationContext(),
                        AppDatabase.class
                )
                .allowMainThreadQueries()
                .build();
        workerDao = appDatabase.workerDao();
        vehicleDao = appDatabase.vehicleDao();
        serviceDao = appDatabase.serviceDao();
    }

    @After
    public void closeDb() {
        appDatabase.close();
    }

    @Test
    public void testInsertServiceWithWorkerAndVehicle() {
        Worker worker = new Worker();
        worker.oib = 12345645901L;
        worker.workerName = "Antonio";
        worker.workerSurname = "Ereiz";
        workerDao.insert(worker);

        Vehicle vehicle = new Vehicle();
        vehicle.idVehicle = 1;
        vehicle.brand = "Toyota";
        vehicle.year = 2020;
        vehicle.numberOfWheels = 4;
        vehicle.engineType = "Hibrid";
        vehicle.numberOfDoors = 4;
        vehicle.bodyStyle = "Sedan";
        vehicle.driveType = "FWD";
        vehicleDao.insert(vehicle);

        Service service = new Service();
        service.serviceType = "Mijenjanje diskova";
        service.dateOfService = new Date();
        service.kmService = 15000;
        service.nextKmService = 20000;
        service.oib = worker.oib;
        service.vehicleId = vehicle.idVehicle;
        long serviceId = serviceDao.insert(service);

        Service foundService = serviceDao.findServiceById((int) serviceId);
        assertNotNull(foundService);
        assertEquals(service.serviceType, foundService.serviceType);
        assertEquals(service.oib, worker.oib);
        assertEquals(service.vehicleId, vehicle.idVehicle);
    }

    @Test
    public void testDeleteServiceWithWorkerAndVehicle() {

        Worker worker = new Worker();
        worker.oib = 12335678901L;
        worker.workerName = "Antonio";
        worker.workerSurname = "Ereiz";
        workerDao.insert(worker);

        Vehicle vehicle = new Vehicle();
        vehicle.idVehicle = 1;
        vehicle.brand = "Toyota";
        vehicle.year = 2020;
        vehicle.numberOfWheels = 4;
        vehicle.engineType = "Hibrid";
        vehicle.numberOfDoors = 4;
        vehicle.bodyStyle = "Sedan";
        vehicle.driveType = "FWD";
        vehicleDao.insert(vehicle);

        Service service = new Service();
        service.serviceType = "Mijenjanje diskova";
        service.dateOfService = new Date();
        service.kmService = 15000;
        service.nextKmService = 20000;
        service.oib = worker.oib;
        service.vehicleId = vehicle.idVehicle;
        long serviceId = serviceDao.insert(service);


        Service foundService = serviceDao.findServiceById((int) serviceId);
        serviceDao.delete(foundService);

        Service deletedService = serviceDao.findServiceById((int) serviceId);
        assertNull(deletedService);
    }
}
