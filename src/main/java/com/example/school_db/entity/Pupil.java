package com.example.school_db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pupil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String  name;
    @Column(nullable = false)
    private String  surname;
    @ManyToMany
    private List<Subject> subjects;
    @ManyToOne(optional = false)
    private Groups groups;

    public Pupil(String name, String surname, List<Subject> subjects, Groups groups) {
        this.name = name;
        this.surname = surname;
        this.subjects = subjects;
        this.groups = groups;
    }

}
