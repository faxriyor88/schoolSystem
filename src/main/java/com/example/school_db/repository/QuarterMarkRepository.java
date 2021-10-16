package com.example.school_db.repository;

import com.example.school_db.entity.QuarterMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuarterMarkRepository extends JpaRepository<QuarterMark,Integer> {
}
