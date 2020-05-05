package com.edu.miusched.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
@Data
@NoArgsConstructor
//@AllArgsConstructor
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotEmpty
    private String firstName;
    private String LastName;
    private String email;
    @ManyToOne
    @JoinColumn(name="entry_id", nullable=false)
    private Entry entry;
}
