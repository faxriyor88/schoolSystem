package com.example.school_db.dtocarriers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuarterMarkCarrier {
    private String  quarterNumber;
    private Integer pupil_id;
    private Integer subject_id;
    private String  mark;

}
