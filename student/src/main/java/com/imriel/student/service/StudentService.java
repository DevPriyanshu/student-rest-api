package com.imriel.student.service;

import com.imriel.student.customexception.*;
import com.imriel.student.entity.Student;
import com.imriel.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student addStudent(Student student) {
        if (student.getName().isEmpty()) {
            throw new EmptyInputException();
        }
        return studentRepository.save(student);
    }

    public List<Student> addAllStudents(List<Student> students) {
        studentRepository.saveAll(students);
        return students;
    }

    public List<Student> getAllStudent() {
        List<Student> studentList = studentRepository.findAll();

        if (studentList.isEmpty()) {
            throw new StudentNotExistException();
        }
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            throw new IdInvalidException();
        }
        return student;
    }

    public List<Student> getStudentByName(String name) throws NameInvalidException {
        List<Student> studentList = studentRepository.findByName(name);
        if (studentList.isEmpty()) {
            throw new NameInvalidException();
        }
        return studentList;
    }

    public List<Student> getStudentByMatchingWithNameAlphabet(String s) {
        List<Student> allStudentList = studentRepository.findAll();
        List<Student> filterList = allStudentList
                .stream()
                .filter(student -> student
                        .getName()
                        .contains(s)).collect(Collectors.toList());
        if (filterList.isEmpty()) {
            throw new NotFindByCharacterException();
        }
        return filterList;
    }

    public Student deleteStudentById(Long id) {

        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            throw new IdInvalidException();
        }
        studentRepository.deleteById(id);
        return student;
    }

    public List<Student> deleteAll() {
        List<Student> studentList = studentRepository.findAll();
        if (!studentRepository.findAll().isEmpty()) {
            studentRepository.deleteAll();
        } else {
            throw new StudentNotExistException();
        }
        return studentList;
    }

    public Student updateStudent(Student student) {
        if (student.getId() == null) {
            throw new NullIdException();
        }

        Student existing_student = studentRepository.findById(student.getId()).orElse(null);
        if (existing_student == null) {
            throw new IdInvalidException();
        }
        existing_student.setName(student.getName());
        existing_student.setDepartment(student.getDepartment());
        existing_student.setGender(student.getGender());
        existing_student.setMobile(student.getMobile());

        studentRepository.save(existing_student);

        return existing_student;
    }

    public List<Student> sortStudent() {
        if (studentRepository.findAll().isEmpty()) {
            throw new StudentNotExistException();
        }
        List<Student> studentList = studentRepository.findAll();
        Collections.sort(studentList, Comparator.comparing(Student::getName));
        return studentList;
    }
}
