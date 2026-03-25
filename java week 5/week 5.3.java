//Student.java
package com.yourpackage.model;
import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {
    @Id
    private int id;
    private String name;
    private String course;
    public Student() {}
    public Student(int id, String name, String course) {
        this.id = id;
        this.name = name;
        this.course = course;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }
}

//StudentRepository.java
package com.yourpackage.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.yourpackage.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}

//StudentService.java
package com.yourpackage.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yourpackage.model.Student;
import com.yourpackage.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repo;

    public Student addStudent(Student s) {
        return repo.save(s);
    }

    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    public Student getStudent(int id) {
        return repo.findById(id).orElse(null);
    }

    public Student updateStudent(Student s) {
        return repo.save(s);
    }

    public void deleteStudent(int id) {
        repo.deleteById(id);
    }
}

//StudentController.java
package com.yourpackage.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.yourpackage.model.Student;
import com.yourpackage.service.StudentService;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService service;

    // CREATE
    @PostMapping
    public Student addStudent(@RequestBody Student s) {
        return service.addStudent(s);
    }

    // READ ALL
    @GetMapping
    public List<Student> getAll() {
        return service.getAllStudents();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public Student getById(@PathVariable int id) {
        return service.getStudent(id);
    }

    // UPDATE
    @PutMapping
    public Student update(@RequestBody Student s) {
        return service.updateStudent(s);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        service.deleteStudent(id);
        return "Deleted Successfully";
    }
}
