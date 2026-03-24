//TaskService.java (Interface)
package com.yourpackage.service;
public interface TaskService {
    String getTask();
}

//TaskServiceImpl.java
package com.yourpackage.service;
import org.springframework.stereotype.Service;
@Service
public class TaskServiceImpl implements TaskService {
    @Override
    public String getTask() {
        return "Task fetched successfully";
    }
}

//TaskController.java (Constructor Injection)
package com.yourpackage.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.yourpackage.service.TaskService;

@RestController
public class TaskController {
    private TaskService taskService;
    @Autowired  
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @GetMapping("/task")
    public String getTask() {
        return taskService.getTask();
    }
}
