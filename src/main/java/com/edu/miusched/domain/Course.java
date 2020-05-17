package com.edu.miusched.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotEmpty
    private String courseCode;
    private String courseTitle;
    private int credits;
    @Digits(integer=10, fraction=0, message = "{invalidNumber.message}")
    private int maxStudent;

    @ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    //@OneToMany(fetch = FetchType.LAZY)
    private List<Course> prerequisites = new ArrayList<>();
    @OneToMany(mappedBy="course")
    private List<Section> sections;
@Digits(integer=10, fraction=0, message = "{invalidNumber.message}")
private int level;
    public Boolean hasPreRequisite() {
        return this.prerequisites != null;
    }

}
