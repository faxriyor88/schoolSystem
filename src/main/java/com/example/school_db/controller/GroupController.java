package com.example.school_db.controller;

import com.example.school_db.dtocarriers.GroupCarrier;
import com.example.school_db.entity.Groups;
import com.example.school_db.entity.School;
import com.example.school_db.repository.GroupRepository;
import com.example.school_db.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/groups")
public class GroupController {
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    SchoolRepository schoolRepository;
    @PostMapping("/post")
    public String insert(@RequestBody GroupCarrier groupCarrier){
        Optional<School> optional1=schoolRepository.findById(groupCarrier.getSchool_id());
        if(optional1.isPresent()){
            Groups groups=new Groups(groupCarrier.getGroupNumber(), optional1.get());
            groupRepository.save(groups);
            return "Ma'lumot qo'shildi";
        }else {
            return "Bunday school yo'q !!!";
        }
    }
    @PutMapping("/put/{id}")
    public String update(@PathVariable Integer id,@RequestBody GroupCarrier groupCarrier){
        Optional<Groups> optional=groupRepository.findById(id);
        if(optional.isPresent()){
            Optional<School> optional1=schoolRepository.findById(groupCarrier.getSchool_id());
            if(optional1.isPresent()){
            Groups groups1=optional.get();
            groups1.setGroupNumber(groupCarrier.getGroupNumber());
            groups1.setSchool(optional1.get());
            groupRepository.save(groups1);
            return "Yangilandi ";
        }else {
            return "Bunday School yo'q";
        }
    }else {
            return "Bunday Group yo'q";
        }
    }
    @GetMapping("/get")
    public List<Groups> getAll(){
        return groupRepository.findAll();
    }
    @GetMapping("/get/{id}")
    public Groups getId(@PathVariable Integer id){
        Optional<Groups> optional=groupRepository.findById(id);
        return optional.orElseGet(Groups::new);
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        Optional<Groups> optional=groupRepository.findById(id);
        if (optional.isPresent()){
            groupRepository.deleteById(id);
            return "O'chirildi !!!";
        }else {
            return "Bunday Group yo'q !!!";
        }
    }
}
