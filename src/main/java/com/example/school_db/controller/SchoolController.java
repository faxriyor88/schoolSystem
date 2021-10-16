package com.example.school_db.controller;

import com.example.school_db.entity.School;
import com.example.school_db.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/school")
public class SchoolController {
    @Autowired
    SchoolRepository schoolRepository;
    @PostMapping("/post")
    public String insert(@RequestBody School school){
        schoolRepository.save(school);
        return "Ma'lumot qo'shildi";
    }
    @PutMapping("/put/{id}")
    public String update(@PathVariable Integer id,@RequestBody School school){
        Optional<School> optional=schoolRepository.findById(id);
        if(optional.isPresent()){
            School school1= optional.get();
            school1.setSchoolname(school.getSchoolname());
            schoolRepository.save(school1);
            return "Yangilandi ";
        }else {
            return "Bunday School topilmadi !!!";
        }
    }
    @GetMapping("/get")
    public List<School> getAll(){
        return schoolRepository.findAll();
    }
    @GetMapping("/get/{id}")
    public School getId(@PathVariable Integer id){
        Optional<School> optional=schoolRepository.findById(id);
        return optional.orElseGet(School::new);
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        Optional<School> optional=schoolRepository.findById(id);
        if (optional.isPresent()){
            schoolRepository.deleteById(id);
            return "O'chirildi !!!";
        }else {
            return "Bunday School topilmadi !!!";
        }
    }
}
