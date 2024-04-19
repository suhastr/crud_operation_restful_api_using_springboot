package com.example.demo.service;

import com.example.demo.dto.StudentDto;

import java.util.List;

public interface StudentService {
    StudentDto createStudent(StudentDto studentDto);
    StudentDto getStudentById(Long studentId);

    List<StudentDto> getAllStudents();

    public StudentDto updateStudent(Long studentId, StudentDto updateStudent);

    public void deleteStudent(Long studentId);


}
