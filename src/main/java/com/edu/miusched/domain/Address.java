package com.edu.miusched.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long Id;
    private String telephone;
    private String city;
    private String state;
    private long zipcode;
    private String country;

    @OneToOne(mappedBy = "address")
    private Admin admin;
    @OneToOne(mappedBy = "address")
    private Student student;


}
