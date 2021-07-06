package com.cleanup.todoc.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.daos.ProjectDao;
import com.cleanup.todoc.database.daos.TaskDao;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

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

    public LiveData<List<Task>> getTasks(){
        return taskDao.getAll();
    }
}