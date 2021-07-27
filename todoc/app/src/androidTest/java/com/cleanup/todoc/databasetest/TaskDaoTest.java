package com.cleanup.todoc.databasetest;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.database.daos.TaskDao;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.model.TaskProject;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class TaskDaoTest {
    private TodocDatabase db;
    private TaskDao taskDao;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, TodocDatabase.class).build();
        db.projectDao().insertAll(Project.populateData());
        taskDao = db.taskDao();
    }


    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void insertAllAndGet() throws InterruptedException {
        taskDao.insertAll(Task.populateData());
        List<TaskProject> tasks = LiveDataTestUtil.getValue(taskDao.getAll());
        assertEquals(3, tasks.size());
    }

    @Test
    public void insertOne() throws InterruptedException {
        taskDao.insertOne(Task.populateData()[1]);
        List<TaskProject> tasks = LiveDataTestUtil.getValue(taskDao.getAll());
        assertEquals(1, tasks.size());
    }

    @Test
    public void deleteTask() throws InterruptedException {
        taskDao.insertAll(Task.populateData());
        List<TaskProject> tasks = LiveDataTestUtil.getValue(taskDao.getAll());
        taskDao.delete(tasks.get(1).task);
        tasks = LiveDataTestUtil.getValue(taskDao.getAll());
        assertEquals(2, tasks.size());
    }

    @Test
    public void deleteAll() throws InterruptedException {
        taskDao.insertAll(Task.populateData());
        taskDao.deleteAll();
        List<TaskProject> tasks = LiveDataTestUtil.getValue(taskDao.getAll());
        assertTrue(tasks.isEmpty());
    }
}
