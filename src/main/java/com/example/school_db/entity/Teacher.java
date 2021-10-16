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
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @ManyToMany
    private List<Subject> subject;

    public Teacher(String name, String surname, List<Subject> subject) {
        this.name = name;
        this.surname = surname;
        this.subject = subject;
    }
}
