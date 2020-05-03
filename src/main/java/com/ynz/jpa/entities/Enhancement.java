package com.ynz.jpa.entities;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Enhancement extends Ticket {
    private Boolean duplicate;
    private String priority;
}
