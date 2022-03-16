package com.imriel.student.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "address", schema = "test")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "city")
    private String city;

    @Column(name = "pin")
    private String pin_code;

    @Column(name = "landmark")
    private String landmark;
}
