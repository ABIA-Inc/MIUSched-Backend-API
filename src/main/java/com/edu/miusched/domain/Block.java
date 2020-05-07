package com.edu.miusched.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Block {

    @Id
    @Column(name = "blockid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty
    private String blockName;
    private int FPPNum;
    private int MPPNum;
    // if two way does not work change by this one //
    /*@OneToMany (cascade = CascadeType.PERSIST)
    @JoinColumn(name = "sectionid", referencedColumnName = "blockid")
    private List<Section> sections;*/

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "block")
    private List<Section> sections = new ArrayList<>();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "entryID", nullable = false)
    private Entry entry;

}
