package com.ynz.jpa.entities;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Bug extends Ticket {

    private int severity;
    private String rootCause;

}
