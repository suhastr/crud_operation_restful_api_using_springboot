package com.example.demo.mapper;

import com.example.demo.dto.StudentDto;
import com.example.demo.entity.Student;

public class StudentMapper {

    public static StudentDto mapToStudentDto(Student student) {
        return new StudentDto(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getStreetAddress(),
                student.getCity(),
                student.getState(),
                student.getZip(),
                student.getTelephoneNumber(),
                student.getEmail(),
                student.getInterestOrigin(),
                student.getRecommendationLikelihood(),
                student.getCampusPreference(),
                student.getDateOfSurvey()
        );
    }

    public static Student mapToStudent(StudentDto studentDto) {
        return new Student(
                studentDto.getId(),
                studentDto.getFirstName(),
                studentDto.getLastName(),
                studentDto.getStreetAddress(),
                studentDto.getCity(),
                studentDto.getState(),
                studentDto.getZip(),
                studentDto.getTelephoneNumber(),
                studentDto.getEmail(),
                studentDto.getInterestOrigin(),
                studentDto.getRecommendationLikelihood(),
                studentDto.getCampusPreference(),
                studentDto.getDateOfSurvey()
        );
    }
}
