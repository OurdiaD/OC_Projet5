package com.cleanup.todoc.databasetest;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.model.Project;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class ProjectDaoTest {
    private TodocDatabase db;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, TodocDatabase.class).build();
    }


    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void insertAndGet() throws InterruptedException {
        db.projectDao().insertAll(Project.populateData());
        List<Project> projects = LiveDataTestUtil.getValue(db.projectDao().getAll());
        assertEquals(3, projects.size());
    }
}
