package com.corban.upskilling.homework.todo.todo;

import com.corban.upskilling.homework.todo.todo.models.Task;
import com.corban.upskilling.homework.todo.todo.models.ToDoList;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TodoApplicationTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @Test
    @Order(1)
    void contextLoads() {
    }

    @Test
    @Order(2)
    public void createToDoList(){
        try{
            mockMvc.perform(put("/ToDoList/").content(mapper.writeValueAsString(
                    new ToDoList("testToDo"))).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk()).andExpect(jsonPath("$.listName").value("testToDo"))
                    .andDo(print());
        } catch (Exception e) {
            fail("Should not have exception: " + e.getLocalizedMessage());
        }
    }

    @Test
    @Order(3)
    public void addTask(){
        try{
            mockMvc.perform(put("/task/").content(mapper.writeValueAsString(
                    new Task("test task", 1))).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk()).andExpect(jsonPath("$.taskIds").isNotEmpty())
                    .andDo(print());
        } catch (Exception e) {
            fail("Should not have exception: " + e.getLocalizedMessage());
        }
    }

    @Test
    @Order(4)
    public void updateTask(){
        Task myTask = new Task("test task updated", 1);
        myTask.setTaskDescription("execute order 66");
        myTask.setCompleted(true);
        myTask.setId(1);
        try{
            mockMvc.perform(post("/task/").content(mapper.writeValueAsString(
                    myTask)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print())
                    .andExpect(status().isOk()).andExpect(jsonPath("$.taskName").value("test task updated"))
                    .andExpect(status().isOk()).andExpect(jsonPath("$.taskDescription").value("execute order 66"))
                    .andExpect(status().isOk()).andExpect(jsonPath("$.completed").value(true));
        } catch (Exception e) {
            fail("Should not have exception: " + e.getLocalizedMessage());
        }
    }
    @Test
    @Order(5)
    public void seeAllTasksInAList(){
        try{
            mockMvc.perform(get("/task/all/1")).andDo(print()).andExpect(status().isOk())
                    .andExpect(jsonPath("$.[0].taskName").value("test task updated"));
        } catch (Exception e) {
            fail("Should not have exception: " + e.getLocalizedMessage());
        }
    }

    @Test
    @Order(6)
    public void viewIncompleteTasksInAList(){
        try{
            mockMvc.perform(put("/task/").content(mapper.writeValueAsString(
                    new Task("test task incomplete", 1))).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk()).andExpect(jsonPath("$.taskIds").isNotEmpty());
        } catch (Exception e) {
            fail("Should not have exception: " + e.getLocalizedMessage());
        }
        try{
            mockMvc.perform(get("/task/incomplete/1")).andDo(print()).andExpect(status().isOk())
                    .andExpect(jsonPath("$.[0].taskName").value("test task incomplete"));
        } catch (Exception e) {
            fail("Should not have exception: " + e.getLocalizedMessage());
        }
    }

    @Test
    @Order(7)
    public void viewCompletedTasks(){
        try{
            mockMvc.perform(get("/task/complete/1")).andDo(print()).andExpect(status().isOk())
                    .andExpect(jsonPath("$.[0].taskName").value("test task updated"));
        } catch (Exception e) {
            fail("Should not have exception: " + e.getLocalizedMessage());
        }
    }

    @Test
    @Order(8)
    public void deleteTask(){
        try{
            mockMvc.perform(post("/task/delete/").content(mapper.writeValueAsString(
                    new Task("test task updated", 1))).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk()).andExpect(jsonPath("$.taskName").value("test task updated"));
        } catch (Exception e) {
            fail("Should not have exception: " + e.getLocalizedMessage());
        }

        try{
            mockMvc.perform(get("/task/complete/1")).andDo(print()).andExpect(status().isOk())
                    .andExpect(jsonPath("$").isEmpty());
        } catch (Exception e) {
            fail("Should not have exception: " + e.getLocalizedMessage());
        }
    }
}
