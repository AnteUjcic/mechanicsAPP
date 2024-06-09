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

@RunWith(AndroidJUnit4.class)
public class AppDatabaseE2E {
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
    public void testAddWorkerVehicleAndService() {

        Worker worker = new Worker();
        worker.oib = 12341238901L;
        worker.workerName = "Ante";
        worker.workerSurname = "Ujcic";
        workerDao.insert(worker);

        Worker foundWorker = workerDao.findWorkerByOIB(worker.oib);
        assertNotNull(foundWorker);
        assertEquals(worker.workerName, foundWorker.workerName);
        assertEquals(worker.workerSurname, foundWorker.workerSurname);


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

        Vehicle foundVehicle = vehicleDao.findVehicleById(vehicle.idVehicle);
        assertNotNull(foundVehicle);
        assertEquals(vehicle.brand, foundVehicle.brand);
        assertEquals(vehicle.year, foundVehicle.year);

        Service service = new Service();
        service.serviceType = "Mjenjanje auspuha";
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

        assertEquals(foundService.serviceType, "Mjenjanje auspuha");
        assertEquals(foundService.kmService, 15000, 0);
        assertEquals(foundService.nextKmService, 20000, 0);
        assertEquals(foundService.oib, worker.oib);
        assertEquals(foundService.vehicleId, vehicle.idVehicle);

    }
}
