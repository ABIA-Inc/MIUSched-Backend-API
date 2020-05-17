package com.edu.miusched.domain;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Entry {
    @Id
    @Column(name = "entryid")
    //@GeneratedValue(strategy= GenerationType.AUTO ,generator = "native")
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    private Long id;
    @NonNull
    private String  entryName;
    private int FPPNum;
    private int MPPNum;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate StartDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate EndDate;
    private EntryType entryType;
    @OneToMany(cascade = CascadeType.ALL,mappedBy="entry")
    private List<Student> students = new ArrayList<>();
     @OneToMany(cascade = CascadeType.ALL,mappedBy = "entry")
    private List<Block>blocks = new ArrayList<>();

     @OneToOne()
    private Schedule schedule;
}
