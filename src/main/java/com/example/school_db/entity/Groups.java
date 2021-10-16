package com.example.school_db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String groupNumber;
    @ManyToOne(optional = false)
    private School school;

    public Groups(String groupNumber, School school) {
        this.groupNumber = groupNumber;
        this.school = school;
    }
}
