package com.ynz.jpa.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@Entity
public class Enhancement extends Ticket {
    private Boolean duplicate;
    private String priority;
}
