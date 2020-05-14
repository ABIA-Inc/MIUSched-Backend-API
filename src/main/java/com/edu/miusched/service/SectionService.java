package com.edu.miusched.service;

import com.edu.miusched.domain.Section;

import java.util.List;

public interface SectionService {

    Section findSectionById(Long id);
    Section SaveSection(Section section);
    List<Section> getAllSection();
    void deleteSectionById(Long id);
    Section findSectionByClassRoom(String room);
}
