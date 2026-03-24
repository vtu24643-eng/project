//TaskService.java
package com.yourpackage.service;

import org.springframework.stereotype.Service;

@Service
public class TaskService {

    public String getTask() {
        return "Task fetched successfully";
    }

    public String createTask() {
        return "Task created successfully";
    }
}

//TaskController.java
package com.yourpackage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yourpackage.service.TaskService;

@RestController
public class TaskController {

    @Autowired   // ✅ Field Injection
    private TaskService taskService;

    @GetMapping("/getTask")
    public String getTask() {
        return taskService.getTask();
    }

    @GetMapping("/createTask")
    public String createTask() {
        return taskService.createTask();
    }
}
