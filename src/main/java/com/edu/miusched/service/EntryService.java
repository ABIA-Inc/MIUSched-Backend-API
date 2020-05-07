package com.edu.miusched.service;


import com.edu.miusched.domain.Entry;

import java.util.List;

public interface EntryService {
    List<Entry> getAllEntries();

    void save(Entry entry);

    void deleteEntry(Long id);

    Entry findByEntryId(Long id);

}
