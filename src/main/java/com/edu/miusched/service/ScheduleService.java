package com.edu.miusched.service;

import com.edu.miusched.dao.ScheduleDoa;
import com.edu.miusched.domain.Block;
import com.edu.miusched.domain.Schedule;
import com.edu.miusched.domain.ScheduleStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface ScheduleService {
   void save(Schedule schedule);
   Schedule getScheduleById(Long id);
   List<Schedule> getAllSchedules();
   void deleteScheduleById(Long id);
   void addBlock(Long scheduleId, Block block);
   void addBlocks(Long scheduleId, List<Block> blocks);
   List<Schedule> getAllSchedulesWthStatus(ScheduleStatus scheduleStatus);
}
