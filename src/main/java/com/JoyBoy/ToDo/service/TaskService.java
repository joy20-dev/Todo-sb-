package com.JoyBoy.ToDo.service;

import com.JoyBoy.ToDo.Repository.TaskRepository;
import com.JoyBoy.ToDo.Models.Task;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List; 


@Service
@RequiredArgsConstructor
public class TaskService{
    private final TaskRepository taskRepository;
    
    public List<Task> getTasks(){
        return taskRepository.findAll();
    }

    public Task getTaskById( Long id){
        return taskRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "task not found"));
    }

    public List<Task> completedTask(){
        return taskRepository.findByCompleted(true);
    }

    public List<Task> pendingTask(){
        return taskRepository.findByCompleted(false);
    }

    public Task createTask(Task task){
        return taskRepository.save(task);

    }

    public Task updateTask( long id, Task newTask){
        Task taskOld = getTaskById(id);

        taskOld.setTitle(newTask.getTitle());
        taskOld.setDescription(newTask.getDescription());
        taskOld.setCompleted(newTask.isCompleted());

        
        return taskRepository.save(taskOld);
    }

    public void deleteTask( Long id){
        taskRepository.deleteById(id);

    }


}