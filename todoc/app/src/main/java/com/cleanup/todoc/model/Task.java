package com.cleanup.todoc.model;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/**
 * <p>Model for the tasks of the application.</p>
 *
 * @author Gaëtan HERFRAY
 */
@Entity(foreignKeys = @ForeignKey(entity = Project.class,
        parentColumns = "id_project",
        childColumns = "project_id"))
public class Task {
    /**
     * The unique identifier of the task
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_task")
    private long id;

    /**
     * The unique identifier of the project associated to the task
     */
    @ColumnInfo(name = "project_id")
    private long projectId;

    /**
     * The name of the task
     */
    @ColumnInfo(name = "name_task")
    private String name;

    /**
     * The timestamp when the task has been created
     */
    @ColumnInfo(name = "timestamp")
    private long creationTimestamp;


    /**
     * Instantiates a new Task.
     *
     * @param projectId         the unique identifier of the project associated to the task to set
     * @param name              the name of the task to set
     * @param creationTimestamp the timestamp when the task has been created to set
     */

    public Task(long projectId, @NonNull String name, long creationTimestamp) {
        this.projectId = projectId;
        this.name = name;
        this.creationTimestamp = creationTimestamp;
    }

    /**
     * Returns the unique identifier of the task.
     *
     * @return the unique identifier of the task
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the task.
     *
     * @param id the unique idenifier of the task to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Sets the unique identifier of the project associated to the task.
     *
     * @param projectId the unique identifier of the project associated to the task to set
     */
    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    /**
     * Returns the project associated to the task.
     *
     * @return the project associated to the task
     */

    public long getProjectId() {
        return projectId;
    }

    public long getCreationTimestamp() {
        return creationTimestamp;
    }

    /**
     * Returns the name of the task.
     *
     * @return the name of the task
     */
    @NonNull
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the task.
     *
     * @param name the name of the task to set
     */
    public void setName(@NonNull String name) {
        this.name = name;
    }

    /**
     * Sets the timestamp when the task has been created.
     *
     * @param creationTimestamp the timestamp when the task has been created to set
     */
    public void setCreationTimestamp(long creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public static Task[] populateData(){
        return new Task[]{
                new Task(1, "aaa", 123),
                new Task(2, "zzz", 124),
                new Task(3, "hhh", 125)
        };
    }
}