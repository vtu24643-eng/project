package com.yourpackage.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import com.yourpackage.model.Student;
import com.yourpackage.repository.StudentRepository;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repo;

    // ✅ Pagination + Sorting
    public List<Student> getStudents(int page, int size, String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        Page<Student> result = repo.findAll(pageable);

        return result.getContent();
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

    @GetMapping("/page")
    public List<Student> getStudents(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy) {

        return service.getStudents(page, size, sortBy);
    }
}
