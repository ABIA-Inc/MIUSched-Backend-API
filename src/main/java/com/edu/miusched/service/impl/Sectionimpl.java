package com.edu.miusched.service.impl;

import com.edu.miusched.dao.SectionDao;
import com.edu.miusched.domain.Section;
import com.edu.miusched.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Sectionimpl implements SectionService {

    @Autowired
    SectionDao sectionDao;
    @Override
    public Section findSectionById(Long id) {
        return sectionDao.findSectionById(id);
    }

    @Override
    public Section SaveSection(Section section) {

       return  sectionDao.save(section);
    }

    @Override
    public List<Section> getAllSection() {
        return sectionDao.findAll();
    }

    @Override
    public void deleteSectionById(Long id) {
        sectionDao.deleteById(id);
    }

    @Override
    public Section findSectionByClassRoom(String room) {
        return sectionDao.findSectionByClassRoom(room);
    }
}
