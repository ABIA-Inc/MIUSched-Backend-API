package com.edu.miusched.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long Id;
    private String telephone;
    private String city;
    private String state;
    private long zipcode;
    private String country;

}
