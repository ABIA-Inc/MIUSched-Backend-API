package com.edu.miusched.service.impl;

import com.edu.miusched.dao.BlockDao;
import com.edu.miusched.domain.Block;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlockServiceImpl {

    @Autowired
    BlockDao blockDao;

    public List<Block> findAll() {
        return blockDao.findAll();
    }
}
