package com.edu.miusched.dao;

import com.edu.miusched.domain.Block;
import com.edu.miusched.domain.Schedule;
import com.edu.miusched.domain.ScheduleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleDoa extends JpaRepository<Schedule, Long> {
    Schedule findScheduleById(Long id);
    List<Schedule> findSchedulesByScheduleStatus(ScheduleStatus scheduleStatus);
    void deleteScheduleById(Long id);
//    default void addBlock(Block block){Schedule.addBlock(block);}
}
