package com.corban.upskilling.homework.todo.todo.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.sql.Timestamp;

@Entity
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String taskName;
    private String taskDescription;
    private boolean completed = false;
    private long toDoListId;
    private Timestamp createdTime;
    private Timestamp completedTime;

    protected Task(){}

    public Task(String taskName, long toDoListId){
        this.taskName = taskName;
        this.toDoListId = toDoListId;
        Date date = new Date();
        this.createdTime = new Timestamp(date.getTime());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        Date date = new Date();
        this.completedTime = new Timestamp(date.getTime());
        this.completed = completed;
    }

    public long getToDoLostId() {
        return toDoListId;
    }

    public void setToDoLostId(long toDoListId) {
        this.toDoListId = toDoListId;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public Timestamp getCompletedTime() {
        return completedTime;
    }
}
