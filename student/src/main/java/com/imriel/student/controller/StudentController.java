package com.imriel.student.controller;

import com.imriel.student.customexception.NameInvalidException;
import com.imriel.student.entity.Student;
import com.imriel.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/getAll")
    List<Student> fetchStudents() {
        return studentService.getAllStudent();
    }

    @GetMapping("/getById/{id}")
    Student fetchStudentById(@PathVariable int id) {
        return studentService.getStudentById((long) id);
    }

    @GetMapping("getByMatchingName/{s}")
    List<Student> fetchByChar(@PathVariable String s) {
        return studentService.getStudentByMatchingWithNameAlphabet(s);
    }

    @GetMapping("/getByName/{name}")
    List<Student> fetchStudentByName(@PathVariable String name) throws NameInvalidException {
        return studentService.getStudentByName(name);
    }

    @GetMapping("/getSorted")
    List<Student> getSortedStudent() {
        return studentService.sortStudent();
    }

    @PostMapping("/add")
    Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PostMapping("/addAll")
    List<Student> addAllStudents(@RequestBody List<Student> students) {
        return studentService.addAllStudents(students);
    }

    @PutMapping("/update")
    Student updateStudent(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }

    @DeleteMapping("/delete/{id}")
    Student deleteStudent(@PathVariable Long id) {
        return studentService.deleteStudentById(id);
    }

    @DeleteMapping("/deleteAll")
    List<Student> deleteStudent() {
        return studentService.deleteAll();
    }

}
