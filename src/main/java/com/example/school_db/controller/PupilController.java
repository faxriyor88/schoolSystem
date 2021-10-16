package com.example.school_db.controller;

import com.example.school_db.dtocarriers.PupilCarrier;
import com.example.school_db.entity.Groups;
import com.example.school_db.entity.Pupil;

import com.example.school_db.entity.Subject;
import com.example.school_db.repository.GroupRepository;
import com.example.school_db.repository.PupilRepository;
import com.example.school_db.repository.SchoolRepository;
import com.example.school_db.repository.SubjectRepository;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping(value = "/pupil")
@RestController
public class PupilController {

    @Autowired
    PupilRepository pupilRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    SchoolRepository schoolRepository;
    @Autowired
    SubjectRepository subjectRepository;


    @PostMapping("/post")
    public String insert(@RequestBody PupilCarrier pupilCarrier) {
        Optional<Groups> optional = groupRepository.findById(pupilCarrier.getGroup_id());
        if (optional.isPresent()) {
            Optional<Subject> optional1 = subjectRepository.findById(pupilCarrier.getSubject_id());
            if (optional1.isPresent()) {
                List<Subject> subjects = new ArrayList<>();
                subjects.add(optional1.get());
                Pupil pupil = new Pupil(pupilCarrier.getName(), pupilCarrier.getSurname(), subjects, optional.get());
                pupilRepository.save(pupil);
                return "Muvaffaqiyatli qo'shildi";
            } else {
                return "Bunday subject topilmadi !!!";
            }
        }
        return "Bunday group topilmadi !!!";
    }


    @GetMapping("/get")
    public List<Pupil> select() {
        return pupilRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public Pupil getId(@PathVariable Integer id) {
        Optional<Pupil> optionalPupil = pupilRepository.findById(id);
        if (optionalPupil.isPresent()) {
            return optionalPupil.get();
        }
        return new Pupil();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Optional<Pupil> optionalPupil = pupilRepository.findById(id);
        if (optionalPupil.isPresent()) {
            pupilRepository.deleteById(id);
            return "O'chirildi";
        } else {
            return "Bunday foydalanuvchi yo'q";
        }
    }

    @PutMapping("/update/{id}")
    public String update(@PathVariable Integer id, @RequestBody PupilCarrier pupilCarrier) {
        Optional<Pupil> optionalPupil = pupilRepository.findById(id);
        if (optionalPupil.isPresent()) {
            Optional<Groups> optional = groupRepository.findById(pupilCarrier.getGroup_id());
            if (optional.isPresent()) {
                Optional<Subject> optional1 = subjectRepository.findById(pupilCarrier.getSubject_id());
                if (optional1.isPresent()) {
                    Pupil pupil = optionalPupil.get();
                    List<Subject> subjectList = pupil.getSubjects();
                    subjectList.remove(pupilCarrier.getSubject_id() - 1);
                    subjectList.add(pupilCarrier.getSubject_id() - 2, optional1.get());
                    Groups groups = pupil.getGroups();
                    groups.setGroupNumber(optional.get().getGroupNumber());
                    groupRepository.save(groups);
                    pupil.setSubjects(subjectList);
                    pupil.setSurname(pupilCarrier.getSurname());
                    pupil.setName(pupilCarrier.getName());
                    pupil.setGroups(groups);
                    pupilRepository.save(pupil);
                    return "Yangilandi";
                } else {
                    return "Bunday Subject topilmadi !!!";
                }
            } else {
                return "Bunday Group topilmadi !!!";
            }
        } else {
            return "Bunday pupil topilmadi !!!";
        }
        //{
//  "pupil_name": "gggg",
//  "pupil_surname": "ggg",
//  "subject_name": "kkkkkkkk",
//  "group_name": "325-guruh",
//  "school_name": "127-maktab"
//}

    }
}