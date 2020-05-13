package com.edu.miusched.dao;

import com.edu.miusched.domain.Block;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface BlockDao extends JpaRepository<Block, Long> {
    public Block findBlockById(Long id);
    public Block findBlockByBlockName(String blockName);
    public Block findBlockByStartDate(LocalDate startDate);
    public void deleteBlockByBlockName(String blockName);
}
