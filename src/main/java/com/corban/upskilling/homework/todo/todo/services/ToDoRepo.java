package com.corban.upskilling.homework.todo.todo.services;

import com.corban.upskilling.homework.todo.todo.models.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepo extends JpaRepository<ToDoList, Long> {

    ToDoList findByListName(String listName);

}
