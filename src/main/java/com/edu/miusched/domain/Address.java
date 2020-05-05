package com.edu.miusched.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Address {

    @Id
    private Long Id;
    private String telephone;
    private String city;
    private String state;
    private long zipcode;
    private String country;

}
