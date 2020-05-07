package com.edu.miusched.dao;

import com.edu.miusched.domain.Section;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionDao extends JpaRepository<Section ,Long> {

    Section findSectionById(Long id);
    Section findSectionByClassRoom(String room);
    void deleteSectionById(Long id);
}
