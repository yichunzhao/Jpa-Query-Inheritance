package com.ynz.jpa.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@Entity
public class Bug extends Ticket {
    private int severity;
    private String rootCause;
}
