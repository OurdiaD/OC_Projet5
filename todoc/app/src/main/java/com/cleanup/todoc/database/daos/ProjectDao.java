package com.cleanup.todoc.database.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.cleanup.todoc.model.Project;

import java.util.List;

@Dao
public interface ProjectDao {
    @Query("SELECT * FROM project")
    LiveData<List<Project>> getAll();

    @Insert
    void insertAll(Project... projects);

    @Delete
    void delete(Project project);
}