package com.example.school_db.controller;

import com.example.school_db.dtocarriers.TimeTableCarrier;
import com.example.school_db.entity.*;
import com.example.school_db.repository.GroupRepository;
import com.example.school_db.repository.SubjectRepository;
import com.example.school_db.repository.TeacherRepository;
import com.example.school_db.repository.TimeTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/timetable")
public class TimeTableController {
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    TimeTableRepository timeTableRepository;

    @PostMapping("/post")
    public String insert(@RequestBody TimeTableCarrier timeTableCarrier) {
        Optional<Groups> optional = groupRepository.findById(timeTableCarrier.getGroup_id());
        if (optional.isPresent()) {
            Optional<Subject> optional1 = subjectRepository.findById(timeTableCarrier.getSubject_id());
            if (optional1.isPresent()) {
                Optional<Teacher> optional2 = teacherRepository.findById(timeTableCarrier.getTeacher_id());
                if (optional2.isPresent()) {
                    LocalTime localTime = LocalTime.now();
                    LocalTime localTime1=localTime.plusMinutes(45);
                    TimeTable timeTable = new TimeTable(localTime,localTime1, optional.get(), optional1.get(), optional2.get());
                    timeTableRepository.save(timeTable);
                    return "Ma'lumot qo'shildi";
                } else {
                    return "Bunday Teacher topilmadi !!!";
                }
            } else {
                return "Bunday Subject topilmadi !!!";
            }
        } else {
            return "Bunday Group topilmadi !!!";
        }
    }

    @GetMapping("/get")
    public List<TimeTable> getAll() {
        return timeTableRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public TimeTable getId(@PathVariable Integer id) {
        Optional<TimeTable> optional = timeTableRepository.findById(id);
        return optional.orElseGet(TimeTable::new);

    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Optional<TimeTable> optional = timeTableRepository.findById(id);
        if (optional.isPresent()) {
            timeTableRepository.deleteById(id);
            return "O'chirildi";
        } else {
            return "Bunday foydalanuvchi yo'q !!!";
        }

    }
}
