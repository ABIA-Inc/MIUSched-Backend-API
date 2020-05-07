package com.edu.miusched.service.impl;


import com.edu.miusched.dao.EntryDao;
import com.edu.miusched.domain.Entry;
import com.edu.miusched.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntryServiceImpl  implements EntryService {

     @Autowired
    EntryDao entryDao;

    @Override
    public List<Entry> getAllEntries() {
        return entryDao.findAll();
    }

    @Override
    public void save(Entry entry) {
        entryDao.save(entry);

    }

    @Override
    public void deleteEntry(Long id) {
        entryDao.deleteById(id);

    }

    @Override
    public Entry findByEntryId(Long id) {
        return entryDao.findEntryById(id);
    }
}
