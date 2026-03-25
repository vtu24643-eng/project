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

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable int id) {
        return service.getTaskById(id);
  }

    @PutMapping
    public Task updateTask(@RequestBody Task t) {
        return service.updateTask(t);
  }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable int id) {
        service.deleteTask(id);
        return "Task Deleted Successfully";
    }
  
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

    public Task getTaskById(int id) {
        return repo.findById(id).orElse(null);
    }

    public Task updateTask(Task t) {
        return repo.save(t);   
    }

    public void deleteTask(int id) {
        repo.deleteById(id);
  }
}
