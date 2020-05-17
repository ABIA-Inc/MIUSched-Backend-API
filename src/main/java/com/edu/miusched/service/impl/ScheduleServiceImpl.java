package com.edu.miusched.service.impl;

import com.edu.miusched.dao.EntryDao;
import com.edu.miusched.dao.ScheduleDoa;
import com.edu.miusched.domain.Block;
import com.edu.miusched.domain.Entry;
import com.edu.miusched.domain.Schedule;
import com.edu.miusched.domain.ScheduleStatus;
import com.edu.miusched.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    ScheduleDoa scheduleDoa;

    @Autowired
    EntryDao entryDao;

    @Override
    public void save(Schedule schedule) {
        scheduleDoa.save(schedule);
    }

    @Override
    public Schedule getScheduleById(Long id) {
        return scheduleDoa.findScheduleById(id);
    }

    @Override
    public List<Schedule> getAllSchedules() {
        return scheduleDoa.findAll();
    }

    @Override
    public void deleteScheduleById(Long id) {
//        System.out.println("Setting Entry Sched to NULL");
//        Entry entry = entryDao.findEntryById(scheduleDoa.findScheduleById(id).getEntry().getId());
//        entry.setSchedule(null);
//        entryDao.save(entry);
//        System.out.println("Entry Sched value is " + entryDao.findEntryById(scheduleDoa.findScheduleById(id).getEntry().getId()).getSchedule());
//        System.out.println("Setting Sched Entry to NULL");
//        Schedule schedule =  scheduleDoa.findScheduleById(id);
//        schedule.setEntry(null);
//        scheduleDoa.save(schedule);
//        System.out.println("Sched Entry value is " + scheduleDoa.findScheduleById(id).getEntry());

        scheduleDoa.deleteScheduleById(id);
    }

    @Override
    public void addBlock(Long scheduleId, Block block) {
        scheduleDoa.findScheduleById(scheduleId).addBlock(block);
    }

    @Override
    public void addBlocks(Long scheduleId, List<Block> blocks) {
        scheduleDoa.findScheduleById(scheduleId).addBlocks(blocks);
    }

    @Override
    public List<Schedule> getAllSchedulesWthStatus(ScheduleStatus scheduleStatus) {
        return scheduleDoa.findSchedulesByScheduleStatus(scheduleStatus);
    }
}
