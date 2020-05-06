package com.edu.miusched.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

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
    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="entryID", nullable = false)
    private Entry entry;
    @OneToMany
    private List<Grade> grades = new ArrayList<>();
}
