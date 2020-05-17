package com.edu.miusched.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotEmpty
    // private ScheduleStatus status;
    private ScheduleStatus scheduleStatus;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "schedule")
    private List<Block> blockList = new ArrayList<>();

    @OneToOne(cascade = CascadeType.REMOVE)//(mappedBy = "schedule")
    private Entry entry;

    public void addBlock(Block block){
        blockList.add(block);
    }

    public void addBlocks(List<Block> blocks){
        this.blockList.addAll(blocks);
    }
}
