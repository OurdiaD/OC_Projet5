package com.cleanup.todoc.database.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.room.Transaction;
import androidx.sqlite.db.SupportSQLiteQuery;

import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.model.TaskProject;

import java.util.List;

@Dao
public interface TaskDao {
    @Transaction
    @Query("SELECT * FROM task")
    LiveData<List<TaskProject>> getAll();

    @RawQuery(observedEntities = TaskProject.class)
    LiveData<List<TaskProject>> getOrder(SupportSQLiteQuery query);

    @Insert
    void insertOne(Task task);

    @Insert
    void insertAll(Task... tasks);

    @Delete
    void delete(Task task);
}