package com.example.school_db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TimeTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private LocalTime startime;
    private LocalTime endtime;
    @ManyToOne
    private Groups groups;
    @ManyToOne
    private Subject subjects;
    @ManyToOne
    private Teacher teachers;

    public TimeTable(LocalTime startime, LocalTime endtime, Groups groups, Subject subjects, Teacher teachers) {
        this.startime = startime;
        this.endtime = endtime;
        this.groups = groups;
        this.subjects = subjects;
        this.teachers = teachers;
    }
}
