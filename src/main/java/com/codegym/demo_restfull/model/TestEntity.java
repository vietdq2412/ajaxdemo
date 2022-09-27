package com.codegym.demo_restfull.model;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Table
public class TestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    //@Column(name = "name", columnDefinition = "VARCHAR(255)")
    //private MultipartFile image;
}
