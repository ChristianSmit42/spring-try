package de.neuefische.springtry.controller;

import de.neuefische.springtry.model.Student;
import de.neuefische.springtry.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("university")
@AllArgsConstructor
public class UniversityController {

    private final StudentService service;

    @GetMapping("{universityId}")
    public Collection<Student> getStudents(@PathVariable String universityId) {
        return Collections.unmodifiableCollection(service.findStudentsByUniverstiy(universityId));
    }
}