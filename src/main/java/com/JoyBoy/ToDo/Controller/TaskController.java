package com.JoyBoy.ToDo.Controller;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.JoyBoy.ToDo.Models.Task;


import com.JoyBoy.ToDo.service.TaskService;



@RestController 
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

     
    @GetMapping
    public List<Task> getTasks(){
        return taskService.getTasks();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id){
        return taskService.getTaskById(id);
        
    }

     @GetMapping("/completed")
     public List<Task> completedTask(){
        return taskService.completedTask();

    }



    
    @GetMapping("/pending")
     public List<Task> pendingTask(){
        return taskService.pendingTask();

    }

    @PostMapping()
    public Task createTask(@RequestBody Task task){
        return taskService.createTask(task);

    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable long id,@RequestBody Task newTask){
        return taskService.updateTask(id, newTask);
    }
    

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);

    } 




}
