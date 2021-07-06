package com.cleanup.todoc;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.TaskRepository;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import java.util.List;

public class TodocViewModel extends AndroidViewModel {

    TaskRepository taskRepository;

    public TodocViewModel(@NonNull Application application) {
        super(application);
        taskRepository = new TaskRepository(application);
    }

    public LiveData<List<Project>> getAllProjects(){
        return taskRepository.getProjects();
    }

    public LiveData<List<Task>> getAllTasks(){
        return taskRepository.getTasks();
    }
}