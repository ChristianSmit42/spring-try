package de.neuefische.springtry.utils;

import de.neuefische.springtry.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("student")
public class StudentController {
    private ArrayList<Student> students = new ArrayList<Student>(List.of(new Student("Peter", "21",12), new Student("Paula", "23",112),new Student("Susanne", "42",22), new Student("Daniel", "1",42)));
    @GetMapping
    public Collection getStudent() {
        return Collections.unmodifiableCollection(students);
    }

    @PutMapping
    public Student addStudent(@RequestBody Student student){
        students.add(student);
        return student;
    }

    @GetMapping(path = "{id}")
    public Student getStudent(@PathVariable String id) {
        for(Student student: students){
            if(student.getId().equals(id)){
                return student;
            }
        }
        return new Student("not valid", "False input", 404);
    }

    @GetMapping("search")
    public List<Student> getStudents(@RequestParam(name="q", required=false) String query, @RequestParam(required = false) Integer minAge){
        ArrayList<Student> matchingStudents = new ArrayList<>();
        if(query == null){
            return this.students;
        }

        for(Student student: this.students){
            if(studentMatchQuery(student,query,minAge)){
                matchingStudents.add(student);
            }
        }
        return matchingStudents;
    }
    private boolean studentMatchQuery(Student student, String query, Integer minAge){

        if(studentMatchesAge(student,minAge)){
            if(student.getName().toLowerCase().startsWith(query.toLowerCase())){
                return true;
            }
            return student.getId().toLowerCase().startsWith(query.toLowerCase());
        }
        return false;
    }
    private boolean studentMatchesAge(Student student, Integer minAge){
        if(minAge== null){
            return true;
        }
        return student.getAge()>= minAge;
    }





}
