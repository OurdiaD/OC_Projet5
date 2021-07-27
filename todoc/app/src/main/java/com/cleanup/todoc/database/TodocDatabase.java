package com.cleanup.todoc.database;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.cleanup.todoc.database.daos.ProjectDao;
import com.cleanup.todoc.database.daos.TaskDao;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import java.util.concurrent.Executors;

@Database(entities = {Task.class, Project.class}, version = 1)
public abstract class TodocDatabase extends RoomDatabase {
    public abstract ProjectDao projectDao();
    public abstract TaskDao taskDao();

    private static TodocDatabase INSTANCE;

    public static TodocDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (TodocDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TodocDatabase.class, "todoc_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            Executors.newSingleThreadExecutor().execute(() -> {
                INSTANCE.projectDao().insertAll(Project.populateData());
                INSTANCE.taskDao().insertAll(Task.populateData());
            });
        }
    };
}
