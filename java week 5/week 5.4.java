package com.yourpackage.model;
import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    private int id;

    private String name;
    private String department;
    private int age;

    public Student() {}

    public Student(int id, String name, String department, int age) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.age = age;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}

package com.yourpackage.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.yourpackage.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findByDepartment(String department);

    List<Student> findByAge(int age);

    List<Student> findByAgeGreaterThan(int age);
}

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

    public List<Student> getByDepartment(String dept) {
        return repo.findByDepartment(dept);
    }

    public List<Student> getByAge(int age) {
        return repo.findByAge(age);
    }

    public List<Student> getByAgeGreater(int age) {
        return repo.findByAgeGreaterThan(age);
    }
}

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

    @GetMapping("/department/{dept}")
    public List<Student> getByDept(@PathVariable String dept) {
        return service.getByDepartment(dept);
    }

    @GetMapping("/age/{age}")
    public List<Student> getByAge(@PathVariable int age) {
        return service.getByAge(age);
    }

    @GetMapping("/ageGreater/{age}")
    public List<Student> getByAgeGreater(@PathVariable int age) {
        return service.getByAgeGreater(age);
    }
}
