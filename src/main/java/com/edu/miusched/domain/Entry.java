package com.edu.miusched.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Entry {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO ,generator = "native")
    private Long id;
    private String  entryName;
    private int FPPNum;
    private int MPPNum;
    private Date StartDate;
    private Date EndDate;
    private EntryType entryType;
    @OneToMany(mappedBy="entry")
    private List<Student> students = new ArrayList<>();
    @OneToMany(cascade = CascadeType.MERGE)
    private List<Block>blocks = new ArrayList<>();
}
