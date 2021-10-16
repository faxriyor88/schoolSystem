package com.example.school_db.dtocarriers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class GroupCarrier {
    private String  groupNumber;
    private Integer school_id;
}
