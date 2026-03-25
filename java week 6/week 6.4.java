//TaskController
package com.yourpackage.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.yourpackage.model.Task;
import com.yourpackage.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService service;

    // 1. @RequestBody (POST)
    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody Task t) {
        Task saved = service.addTask(t);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    //2. @PathVariable (GET by ID)
    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable int id) {
        Task task = service.getTaskById(id);

        if (task != null) {
            return new ResponseEntity<>(task, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Task not found", HttpStatus.NOT_FOUND);
        }
    }

    // 3. @RequestParam (GET with filter)
    @GetMapping("/status")
    public ResponseEntity<List<Task>> getByStatus(@RequestParam String status) {
        List<Task> tasks = service.getTasksByStatus(status);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    // 4. UPDATE with ResponseEntity
    @PutMapping
    public ResponseEntity<Task> updateTask(@RequestBody Task t) {
        Task updated = service.updateTask(t);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    // 5. DELETE with custom response
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable int id) {
        service.deleteTask(id);
        return new ResponseEntity<>("Task Deleted Successfully", HttpStatus.OK);
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
    public List<Task> getTasksByStatus(String status) {
         return repo.findByStatus(status);
    }
}
