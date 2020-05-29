package com.corban.upskilling.homework.todo.todo.services;

import com.corban.upskilling.homework.todo.todo.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {

    Task findByTaskName(String taskName);

    Set<Task> findByToDoListId(long toDoListId);
}
