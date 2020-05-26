package de.neuefische.springtry.service;

import de.neuefische.springtry.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class StudentService {

    private final List<Student> students = new ArrayList<>(List.of(
            new Student("Frank", "1", 22, "uni1"),
            new Student("Marwin", "2", 25, "uni2"),
            new Student("Caro", "3", 18, "uni3"),
            new Student("Franziska", "4", 23, "uni1"),
            new Student("Sabine", "29", 5, "uni2"),
            new Student("Fabian", "30", 6, "uni1")
    ));

    public StudentService() {
    }

    public Student addStudent(Student student) {
        students.add(student);
        return student;
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public Student getStudent(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return new Student("not valid", "False input", 404, "IMGD");
    }


    public Collection<Student> findStudentsByUniverstiy(String universityId) {

        List<Student> matchingStudents = new ArrayList<>();

        for (Student student : students) {
            if (student.getUniversityId().equals(universityId)) {
                matchingStudents.add(student);
            }
        }

        return Collections.unmodifiableCollection(matchingStudents);
    }

    public Collection<Student> searchStudents(String query, Integer minAge) {
        ArrayList<Student> matchingStudents = new ArrayList<>();
        if (query == null) {
            return Collections.unmodifiableCollection(students);
        }

        for (Student student : students) {
            if (studentMatchQuery(student, query, minAge)) {
                matchingStudents.add(student);
            }
        }
        return Collections.unmodifiableCollection(matchingStudents);
    }

    private boolean studentMatchQuery(Student student, String query, Integer minAge) {

        if (studentMatchesAge(student, minAge)) {
            if (student.getName().toLowerCase().startsWith(query.toLowerCase())) {
                return true;
            }
            return student.getId().toLowerCase().startsWith(query.toLowerCase());
        }
        return false;
    }

    private boolean studentMatchesAge(Student student, Integer minAge) {
        if (minAge == null) {
            return true;
        }
        return student.getAge() >= minAge;
    }
}
