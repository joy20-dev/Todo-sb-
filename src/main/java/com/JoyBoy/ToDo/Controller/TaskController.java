package com.JoyBoy.ToDo.Controller;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.JoyBoy.ToDo.Repository.TaskRepository;
import com.JoyBoy.ToDo.Models.Task;



@RestController 
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskRepository taskRepository;

     

    @GetMapping
    public List<Task> getTasks(){
        return taskRepository.findAll();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id){
        return taskRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "task not found"));
        
    }

    @PostMapping()
    public Task createTask(@RequestBody Task task){
        return taskRepository.save(task);

    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable long id,@RequestBody Task newTask){
        Task taskOld = taskRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "task not found"));

        taskOld.setTask(newTask.getTask());
        taskOld.setCompleted(newTask.isCompleted());
        return taskRepository.save(taskOld);
    }
    

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
        taskRepository.deleteById(id);

    } 




}
