//Task.java
package com.yourpackage.model;
import jakarta.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    private int id;
    private String title;
    private String status;
  
    public Task() {}
    public Task(int id, String title, String status) {
        this.id = id;
        this.title = title;
        this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

//TaskRepository
package com.yourpackage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.yourpackage.model.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}

//TaskService
package com.yourpackage.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yourpackage.model.Task;
import com.yourpackage.repository.TaskRepository;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repo;

    public Task addTask(Task t) {
        return repo.save(t);
    }

    public List<Task> getAllTasks() {
        return repo.findAll();
    }
}

//TaskController
package com.yourpackage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.yourpackage.model.Task;
import com.yourpackage.service.TaskService;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService service;

    @PostMapping
    public Task addTask(@RequestBody Task t) {
        return service.addTask(t);
    }


    @GetMapping
    public List<Task> getAllTasks() {
        return service.getAllTasks();
    }
}

