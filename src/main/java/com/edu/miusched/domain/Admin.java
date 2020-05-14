package com.edu.miusched.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Getter
@Setter
public class Admin {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotEmpty
    @OneToOne
    private Address address;
    @OneToOne
    private Account account;

    private String email;
    private String password;

    private int active;


}
