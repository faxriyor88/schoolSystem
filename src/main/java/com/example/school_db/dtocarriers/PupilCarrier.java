package com.example.school_db.dtocarriers;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PupilCarrier {
    private String  name;
    private String  surname;
    private Integer subject_id;
    private Integer group_id;

}
