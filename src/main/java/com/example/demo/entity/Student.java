package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="StudentSurvey")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "street_address")
    private String streetAddress;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zip")
    private String zip;

    @Column(name = "telephone_number")
    private String telephoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "date_of_survey")
    private String dateOfSurvey;

    @Column(name = "campus_preference")
    private String campusPreference;

    @Column(name = "interest_origin")
    private String interestOrigin;

    @Column(name = "recommendation_likelihood")
    private String recommendationLikelihood;

    public Student(Long id, String firstName, String lastName, String streetAddress, String city, String zip, String telephoneNumber, String email, String interestOrigin, String recommendationLikelihood, String campusPreference, String dateOfSurvey) {
    }
}
