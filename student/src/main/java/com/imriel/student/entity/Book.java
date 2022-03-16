package com.imriel.student.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "BOOK", schema = "test")
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "AUTHOR")
    private String author;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "books")
    private List<Student> students;


    /*@ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "STUDENT_ID")
    @JsonBackReference
    private Student student;*/

}