package com.edu.miusched.dao;

import com.edu.miusched.domain.Block;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

public interface BlockDao extends JpaRepository<Block,Long> {

}
