package com.cleanup.todoc.model;

import androidx.room.Embedded;
import androidx.room.Relation;

public class TaskProject {

    @Embedded
    public Task task;
    @Relation(
            parentColumn = "project_id",
            entityColumn = "id_project"
    )

    public Project project;
}
