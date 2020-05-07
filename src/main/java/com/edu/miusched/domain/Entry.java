package com.edu.miusched.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Entry {
    @Id
    @Column(name = "entryid")
    //@GeneratedValue(strategy= GenerationType.AUTO ,generator = "native")
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    private Long id;
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
//    @OneToMany(cascade = CascadeType.PERSIST)
//    @JoinColumn(name="blockid", referencedColumnName="entryid")
//    private List<Block> blocks;
    // if unidirectional works make by this using bidrectional//
     @OneToMany(cascade = CascadeType.ALL,mappedBy = "entry")
    private List<Block>blocks = new ArrayList<>();
}
