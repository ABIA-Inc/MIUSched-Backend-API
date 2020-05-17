package com.edu.miusched.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Section {
    @Id
    @Column(name = "sectionid")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotEmpty
    private String sectionName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private String classRoom;
    private Integer capacity;
//    private Integer
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "section")
    private List<Grade> grades = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name="course_id", nullable=true)
    private Course course;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "blockID")
    private Block block;
}
