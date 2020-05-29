package com.corban.upskilling.homework.todo.todo.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class ToDoList implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String listName;
    @ElementCollection(targetClass = Long.class)
    private Set<Long> taskIds;

    protected ToDoList(){}

    public ToDoList(String listName){
        this.listName = listName;
    }

    public ToDoList(String listName, long id){
        this.listName = listName;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public Set<Long> getTaskIds() {
        return taskIds;
    }

    public void addTaskId(long id){
        taskIds.add(id);
    }

    public void removeTaskId(long id){
        taskIds.remove(id);
    }
}
