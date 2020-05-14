package com.edu.miusched.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

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
    @Value("${firstName:fer}")
    private String firstName;
    @Value("${firstName:fer}")
    private String LastName;
    @Value("${firstName:fer}")
    private String email;
    private String password;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Course> coursePreference = new ArrayList<>();
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Block> blockPreference = new ArrayList<>();


     public void deleteBlock(Long Id)
     {
         for (Block block:blockPreference
              ) {

             if( block.getId() == Id ) {

             }
         }
     }
}
