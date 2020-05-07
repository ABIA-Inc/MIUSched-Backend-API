package com.edu.miusched.dao;


import com.edu.miusched.domain.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryDao  extends JpaRepository<Entry,Long> {
    Entry findEntryById(Long id);
    Entry findEntryByEntryName(String name);
}
