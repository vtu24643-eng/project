//User
package com.yourpackage.model;
import jakarta.validation.constraints.*;

public class User {

    @NotEmpty(message = "Name must not be empty")
    private String name;

    @Email(message = "Email must be valid")
    private String email;

    @Min(value = 18, message = "Age must be at least 18")
    private int age;

    public User() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}

//UserException
package com.yourpackage.exception;

public class UserException extends RuntimeException {
    public UserException(String message) {
        super(message);
    }
}

//UserController
package com.yourpackage.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import com.yourpackage.model.User;
import com.yourpackage.exception.UserException;

@RestController
public class UserController {

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user) {

        // Example custom exception
        if (user.getName().equalsIgnoreCase("admin")) {
            throw new UserException("Admin registration not allowed");
        }

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}

//GlobalExceptionHandler
package com.yourpackage.exception;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    //  Handle Validation Errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    //  Handle Custom Exception
    @ExceptionHandler(UserException.class)
    public ResponseEntity<Map<String, String>> handleCustom(UserException ex) {

        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}

