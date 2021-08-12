package com.cleanup.todoc.database;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.sqlite.db.SimpleSQLiteQuery;

import com.cleanup.todoc.database.daos.ProjectDao;
import com.cleanup.todoc.database.daos.TaskDao;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.model.TaskProject;

import java.util.List;
import java.util.concurrent.Executors;

public class TaskRepository {

    ProjectDao projectDao;
    TaskDao taskDao;

    public TaskRepository(Application application){
        TodocDatabase db = TodocDatabase.getInstance(application);
        projectDao = db.projectDao();
        taskDao = db.taskDao();
    }

    public LiveData<List<Project>> getProjects(){
        return projectDao.getAll();
    }

    public LiveData<List<TaskProject>> getTasks(String column, String order){
        SimpleSQLiteQuery query = new SimpleSQLiteQuery(
                "SELECT * FROM Task ORDER BY " + column + " " + order);
        return taskDao.getOrder(query);
    }

    public void addTask(Task task){
        Executors.newSingleThreadExecutor().execute(() -> taskDao.insertOne(task));
    }

    public void deleteTask(Task task){
        Executors.newSingleThreadExecutor().execute(() -> taskDao.delete(task));
    }
}