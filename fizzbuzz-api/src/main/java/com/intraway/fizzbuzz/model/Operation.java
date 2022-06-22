package com.intraway.fizzbuzz.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "fizzbuzz_operations")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Timestamp timestamp;
    private String description;
    @ElementCollection
    private List<String> list;

    public Operation() {
        final Date date = new Date();
        this.timestamp = new Timestamp(date.getTime());
    }

}
