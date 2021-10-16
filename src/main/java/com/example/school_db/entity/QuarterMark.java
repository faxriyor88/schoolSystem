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
public class QuarterMark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String quarternumber;
    @OneToMany
    private List<Pupil> pupil;
    @OneToMany
    private List<Subject> subjects;
    @Column(nullable = false)
    private String mark;

    public QuarterMark(String quarternumber, List<Pupil> pupil, List<Subject> subjects,String mark) {
        this.quarternumber = quarternumber;
        this.pupil = pupil;
        this.subjects = subjects;
        this.mark=mark;
    }
}
