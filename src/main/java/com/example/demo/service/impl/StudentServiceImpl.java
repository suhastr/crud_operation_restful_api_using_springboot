package com.example.demo.service.impl;

import com.example.demo.dto.StudentDto;
import com.example.demo.entity.Student;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        Student student = StudentMapper.mapToStudent(studentDto);
        Student savedStudent = studentRepository.save(student);
        return StudentMapper.mapToStudentDto(savedStudent);
    }

    @Override
    public StudentDto getStudentById(Long studentId) {
       Student queryres =  studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("student does not exist" + studentId));
        return StudentMapper.mapToStudentDto(queryres);
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map((student) -> StudentMapper.mapToStudentDto(student)).collect(Collectors.toList());
    }

    @Override
    public StudentDto updateStudent(Long studentId, StudentDto updateStudent) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("student does not exist"  + studentId));
        student.setFirstName(updateStudent.getFirstName());
        student.setLastName(updateStudent.getLastName());
        student.setStreetAddress(updateStudent.getStreetAddress());
        student.setCity(updateStudent.getCity());
        student.setState(updateStudent.getState());
        student.setZip(updateStudent.getZip());
        student.setTelephoneNumber(updateStudent.getTelephoneNumber());
        student.setEmail(updateStudent.getEmail());
        student.setDateOfSurvey(updateStudent.getDateOfSurvey());
        student.setCampusPreference(updateStudent.getCampusPreference());
        student.setInterestOrigin(updateStudent.getInterestOrigin());
        student.setRecommendationLikelihood(updateStudent.getRecommendationLikelihood());
        Student updatedStudent = studentRepository.save(student);
        return StudentMapper.mapToStudentDto(updatedStudent);

    }

    @Override
    public void deleteStudent(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("student does not exist"  + studentId));
        studentRepository.deleteById(studentId);

    }


}
