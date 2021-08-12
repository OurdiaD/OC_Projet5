package com.cleanup.todoc.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.model.TaskProject;
import com.cleanup.todoc.ui.MainActivity;

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

    public LiveData<List<TaskProject>> getAllTasks(SortMethod sortMethod){
        String column;
        String order;
        switch (sortMethod) {
            case ALPHABETICAL:
               column = "name_task";
               order = "ASC";
               break;
            case ALPHABETICAL_INVERTED:
               column = "name_task";
               order = "DESC";
               break;
            case RECENT_FIRST:
               column = "timestamp";
               order = "DESC";
               break;
            case OLD_FIRST:
               column = "timestamp";
               order = "ASC";
               break;
            default:
                column = "id_task";
                order = "ASC";
        }
        return taskRepository.getTasks(column, order);
    }

    public void addTask(Task task){
        taskRepository.addTask(task);
    }
    public void deleteTask(Task task){
        taskRepository.deleteTask(task);
    }

    /**
     * List of all possible sort methods for task
     */
    public enum SortMethod {
        /**
         * Sort alphabetical by name
         */
        ALPHABETICAL,
        /**
         * Inverted sort alphabetical by name
         */
        ALPHABETICAL_INVERTED,
        /**
         * Lastly created first
         */
        RECENT_FIRST,
        /**
         * First created first
         */
        OLD_FIRST,
        /**
         * No sort
         */
        NONE
    }
}