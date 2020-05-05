package com.edu.miusched.domain;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Section {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotEmpty
    private String sectionName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String classRoom;
    private Integer capacity;
    @OneToMany
    private List<Grade> grades = new ArrayList<>();
}
