package com.example.school_db.dtocarriers;

import com.example.school_db.entity.Groups;
import com.example.school_db.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeTableCarrier {
   private LocalTime localTime;
     private Integer group_id;
     private Integer subject_id;
     private Integer teacher_id;


}
