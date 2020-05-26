package de.neuefische.springtry.controller;

import de.neuefische.springtry.model.Student;
import de.neuefische.springtry.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("student")
@AllArgsConstructor
public class StudentController {

    private final StudentService service;

    @GetMapping
    public Collection getStudent() {
        return Collections.unmodifiableCollection(service.getAllStudents());
    }

    @PutMapping
    public Student addStudent(@RequestBody Student student) {
        return service.addStudent(student);
    }

    @GetMapping(path = "{id}")
    public Student getStudent(@PathVariable String id) {
        return service.getStudent(id);
    }

    @GetMapping("search")
    public Collection<Student> getStudents(@RequestParam(name = "q", required = false) String query, @RequestParam(required = false) Integer minAge) {
        return Collections.unmodifiableCollection(service.searchStudents(query, minAge));
    }


}
