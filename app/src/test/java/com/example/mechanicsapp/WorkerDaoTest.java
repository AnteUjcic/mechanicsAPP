package com.example.mechanicsapp;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import androidx.test.runner.AndroidJUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class WorkerDaoTest {
    private AppDatabase appDatabase;
    private WorkerDao workerDao;

    @Before
    public void initDb() {
        appDatabase = Room.inMemoryDatabaseBuilder(
                        ApplicationProvider.getApplicationContext(),
                        AppDatabase.class
                )
                .allowMainThreadQueries()
                .build();
        workerDao = appDatabase.workerDao();
    }

    @After
    public void closeDb() {
        appDatabase.close();
    }

    @Test
    public void testInsertAndFindWorker() {
        Worker worker = new Worker();
        worker.oib = 12345675701L;
        worker.workerName = "Ante";
        worker.workerSurname = "Ujcic";
        workerDao.insert(worker);

        Worker foundWorker = workerDao.findWorkerByOIB(12345678901L);
        assertEquals(worker.workerName, foundWorker.workerName);
        assertEquals(worker.workerSurname, foundWorker.workerSurname);
    }
}
