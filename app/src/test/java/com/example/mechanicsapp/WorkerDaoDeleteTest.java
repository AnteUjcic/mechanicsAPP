package com.example.mechanicsapp;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import androidx.test.runner.AndroidJUnit4;

import static org.junit.Assert.assertNull;

@RunWith(AndroidJUnit4.class)
public class WorkerDaoDeleteTest {
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
    public void testDeleteWorker() {
        Worker worker = new Worker();
        worker.oib = 12345678901L;
        worker.workerName = "John";
        worker.workerSurname = "Doe";
        workerDao.insert(worker);

        workerDao.deleteByOib(12345678901L);
        Worker deletedWorker = workerDao.findWorkerByOIB(12345678901L);
        assertNull(deletedWorker);
    }
}
