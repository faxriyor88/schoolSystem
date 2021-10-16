package com.example.school_db.controller;

import com.example.school_db.entity.Subject;
import com.example.school_db.entity.Teacher;
import com.example.school_db.repository.SubjectRepository;
import com.example.school_db.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    SubjectRepository subjectRepository;

    @PostMapping("/post")
    public String insert(@RequestBody Teacher teacher) {
        List<Subject> list=teacher.getSubject();
        subjectRepository.save(teacher.getSubject().get(0));
        teacherRepository.save(teacher);
        return "Ma'lumot qo'shildi";
    }

    //
    @GetMapping("/get")
    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public Teacher getId(@PathVariable Integer id) {
        Optional<Teacher> optional = teacherRepository.findById(id);
        return optional.orElseGet(Teacher::new);
    }

    @PutMapping("/update/{id}")
    public String update(@PathVariable Integer id, @RequestBody Teacher teacher) {
        Optional<Teacher> teacher1 = teacherRepository.findById(id);
        if (teacher1.isPresent()) {
            Teacher teacher2 = teacher1.get();
            Subject subject=teacher2.getSubject().get(0);
            subject.setSub_name(teacher.getSubject().get(0).getSub_name());
            List<Subject> list=teacher2.getSubject();
            subjectRepository.save(subject);
            teacher2.setSurname(teacher.getSurname());
            teacher2.setName(teacher.getName());
            teacher2.setSubject(list);
            teacherRepository.save(teacher2);
            return "Yangilandi";
        } else {
            return "Bunday Teacher topilmadi";
        }

    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Optional<Teacher> teacher1 = teacherRepository.findById(id);
        if (teacher1.isPresent()) {
            subjectRepository.delete(teacher1.get().getSubject().get(0));
            teacherRepository.deleteById(id);
            return "O'chirildi";
        } else {
            return "Bunday Teacher yo'q !!!";
        }
    }
}