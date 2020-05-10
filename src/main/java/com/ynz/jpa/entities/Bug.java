package com.ynz.jpa.entities;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
//ToDo investigate hashcode generation due to inheritance by the lombok.
public class Bug extends Ticket {

    private int severity;

    private String rootCause;
}
