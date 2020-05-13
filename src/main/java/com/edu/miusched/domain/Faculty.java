package com.edu.miusched.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Faculty {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotEmpty
    private String firstName;
    private String LastName;
    private String email;
    private LocalDate startDate;
    @Value("${courseLoad:0}")
    private Integer courseLoad;
    private Integer intervalBetweenBlocks;
    @OneToOne
    private Address address;
    @OneToOne
    private Schedule schedule;
    @OneToOne
    private Account account;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Course> coursePreference = new ArrayList<>();
    @OneToMany(fetch = FetchType.LAZY)
    private List<Block> blockPreference = new ArrayList<>();
}
