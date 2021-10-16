package com.example.school_db.repository;

import com.example.school_db.entity.Pupil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PupilRepository extends JpaRepository<Pupil,Integer> {
}
