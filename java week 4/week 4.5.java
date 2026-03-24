//OptionalService.java (Optional Bean)
package com.yourpackage.service;
import org.springframework.stereotype.Service;

@Service
public class OptionalService {
    public String extraFeature() {
        return "Optional Service is available!";
    }
}

//TaskService.java
package com.yourpackage.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    @Autowired(required = false)   // ✅ Optional Injection
    private OptionalService optionalService;
    public String getTask() {
        if (optionalService != null) {
            return "Task + " + optionalService.extraFeature();
        } else {
            return "Task only (OptionalService not available)";
        }
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
    @Autowired
    private TaskService taskService;
    @GetMapping("/task")
    public String getTask() {
        return taskService.getTask();
    }
}

