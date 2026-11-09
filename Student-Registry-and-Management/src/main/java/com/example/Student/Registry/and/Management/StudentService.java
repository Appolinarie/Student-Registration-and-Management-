package com.example.Student.Registry.and.Management;

import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public String addStudent(Student student) throws SQLException {
        if (student.getName() == null || student.getName().isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (student.getAge() <= 18) {
            throw new IllegalArgumentException("Age must be greater than 18");
        }
        if (student.getEmail() == null || !student.getEmail().contains("@")) {
            throw new IllegalArgumentException("A valid email is required");
        }

        studentRepository.addStudent(student);
        return "Student added successfully";
    }

    public List<Student> getAllStudents() throws SQLException {
        return studentRepository.getStudents();
    }

    public Student getStudentByName(String name) throws SQLException {
        Student student = studentRepository.getStudentByName(name);
        if (student == null) {
            throw new IllegalArgumentException("Student not found: " + name);
        }
        return student;
    }

    public String removeStudent(int id) throws SQLException {
        boolean removed = studentRepository.removeStudent(id);
        if (!removed) {
            throw new IllegalArgumentException("No student with id " + id);
        }
        return "Student removed successfully";
    }
}


