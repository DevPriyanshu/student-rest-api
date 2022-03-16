package com.imriel.student.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "STUDENT", schema = "test")
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "MOBILE")
    private String mobile;

    @Column(name = "DEPARTMENT")
    private String department;

    @Column(name = "GENDER")
    private String gender;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "student_bike", joinColumns = {
            @JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "bike_id")})
    private List<Book> books;

    /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    @JsonManagedReference
    private List<Book> books;*/

}