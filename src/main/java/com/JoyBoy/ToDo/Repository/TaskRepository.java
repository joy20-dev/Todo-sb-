package com.JoyBoy.ToDo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JoyBoy.ToDo.Models.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{
    List<Task> findByCompleted(boolean completed);

}
