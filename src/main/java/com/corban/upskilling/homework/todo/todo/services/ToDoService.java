package com.corban.upskilling.homework.todo.todo.services;

import com.corban.upskilling.homework.todo.todo.models.Task;
import com.corban.upskilling.homework.todo.todo.models.ToDoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ToDoService {

    @Autowired
    private ToDoRepo toDoRepo;

    @Autowired
    private TaskRepo taskRepo;

    public ToDoList createToDoList(ToDoList toDoList){
        toDoRepo.save(toDoList);
        return toDoRepo.findById(toDoList.getId()).get();
    }

    public ToDoList addTask(Task taskToAdd){
        taskRepo.save(taskToAdd);
        ToDoList listToUpdate = toDoRepo.findById(taskToAdd.getToDoLostId()).get();
        listToUpdate.addTaskId(taskToAdd.getId());
        toDoRepo.save(listToUpdate);
        return listToUpdate;
    }

    public Task updateTask(Task task){
        taskRepo.save(task);
        return taskRepo.findById(task.getId()).get();
    }

    public Task deleteTask(Task taskToDelete){
        taskToDelete = taskRepo.findByTaskName(taskToDelete.getTaskName());
        taskRepo.delete(taskToDelete);
        ToDoList listToUpdate = toDoRepo.findById(taskToDelete.getToDoLostId()).get();
        listToUpdate.removeTaskId(taskToDelete.getId());
        toDoRepo.save(listToUpdate);
        return taskToDelete;
    }

    public Set<Task> getAllTasks(long toDoId){
        return taskRepo.findByToDoListId(toDoId);
    }

    public Set<Task> getAllIncompleteTasks(long toDoId){
        Set<Task> allTasks = getAllTasks(toDoId);
        Set<Task> incompleteTasks = new HashSet<>();
        for(Task task: allTasks){
            if(!task.isCompleted()){
                incompleteTasks.add(task);
            }
        }
        return incompleteTasks;
    }

    public Set<Task> getAllCompleteTasks(long toDoId){
        Set<Task> allTasks = getAllTasks(toDoId);
        Set<Task> completeTasks = new HashSet<>();
        for(Task task: allTasks){
            if(task.isCompleted()){
                completeTasks.add(task);
            }
        }
        return completeTasks;
    }
}
