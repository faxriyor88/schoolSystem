package com.example.school_db.controller;

import com.example.school_db.entity.Subject;
import com.example.school_db.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    SubjectRepository subjectRepository;
    @PostMapping("/post")
    public String insert(@RequestBody Subject subject){
        subjectRepository.save(subject);
        return "Ma'lumot qo'shildi";
    }
    @PutMapping("/put/{id}")
    public String update(@PathVariable Integer id,@RequestBody Subject subject){
        Optional<Subject> optional=subjectRepository.findById(id);
        if(optional.isPresent()){
            Subject subject1=optional.get();
            subject1.setSub_name(subject.getSub_name());
            subjectRepository.save(subject1);
            return "Yangilandi ";
        }else {
            return "Bunday Subject yo'q";
        }
    }
    @GetMapping("/get")
    public List<Subject> getAll(){
        return subjectRepository.findAll();
    }
    @GetMapping("/get/{id}")
    public Subject getId(@PathVariable Integer id){
        Optional<Subject> optional=subjectRepository.findById(id);
        return optional.orElseGet(Subject::new);
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        Optional<Subject> optional=subjectRepository.findById(id);
        if (optional.isPresent()){
            subjectRepository.deleteById(id);
            return "O'chirildi !!!";
        }else {
            return "Bunday Subject yo'q !!!";
        }
    }

}
