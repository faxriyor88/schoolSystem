package com.example.school_db.controller;


import com.example.school_db.dtocarriers.QuarterMarkCarrier;
import com.example.school_db.entity.*;
import com.example.school_db.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/quartermark")
public class QuarterMarkController {
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    PupilRepository pupilRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    SchoolRepository schoolRepository;
    @Autowired
    QuarterMarkRepository quarterMarkRepository;

    @PostMapping("/post")
    public String insert(@RequestBody QuarterMarkCarrier quarterMarkCarrier) {
        Optional<Pupil> pupil = pupilRepository.findById(quarterMarkCarrier.getPupil_id());
        if (pupil.isPresent()) {
            Optional<Subject> subject = subjectRepository.findById(quarterMarkCarrier.getSubject_id());
            if (subject.isPresent()) {
                List<Pupil> pupils = new ArrayList<>();
                pupils.add(pupil.get());
                List<Subject> subjects = new ArrayList<>();
                subjects.add(subject.get());
                QuarterMark quarterMark = new QuarterMark(quarterMarkCarrier.getQuarterNumber(), pupils, subjects, quarterMarkCarrier.getMark());
                quarterMarkRepository.save(quarterMark);
                return "Ma'lumot qo'shildi !!!";
            } else {
                return "Bunday Subject yo'q !!!";
            }
        } else {
            return "Bunday Pupil yo'q !!!";
        }
    }

    @GetMapping("/get")
    public List<QuarterMark> getAll() {
        return quarterMarkRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public QuarterMark getId(@PathVariable Integer id) {
        Optional<QuarterMark> quarterMark = quarterMarkRepository.findById(id);
        if (quarterMark.isPresent()) {
            return quarterMark.get();
        } else {
            return new QuarterMark();
        }
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Optional<QuarterMark> quarterMark = quarterMarkRepository.findById(id);
        if (quarterMark.isPresent()) {
            quarterMarkRepository.deleteById(id);
            return "O'chirildi";
        } else {
            return "Bunday foydalanuvchi yo'q !!!";
        }

    }
}
