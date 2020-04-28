package com.ynz.jpa.entities;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public class Ticket {
    @Id
    @GeneratedValue
    private int id;

    private String title;
    private String description;
}
