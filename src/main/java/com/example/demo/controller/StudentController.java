package com.example.demo.controller;


import com.example.demo.dto.StudentDto;
import com.example.demo.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;
    // Build Add Student REST API

    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto) {
        StudentDto savedStudent = studentService.createStudent(studentDto);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    // Build Get Student REST API

    @GetMapping("{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable("id") Long studentId) {
        StudentDto studentDto = studentService.getStudentById(studentId);
        return ResponseEntity.ok(studentDto);
    }

    // Build get all Students REST API

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        List<StudentDto> studentDtoList = studentService.getAllStudents();
        return ResponseEntity.ok(studentDtoList);
    }


    // Build Update Student By ID REST API
    @PutMapping("{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable("id") Long studentId, @RequestBody StudentDto updatedStudent) {

        StudentDto updatedStudentObj = studentService.updateStudent(studentId, updatedStudent);
        return ResponseEntity.ok(updatedStudentObj);
    }


    // Build Delete Employee by ID REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") Long studentId) {
        studentService.deleteStudent(studentId);
        return new ResponseEntity<>("Student deleted", HttpStatus.OK);

    }


}
