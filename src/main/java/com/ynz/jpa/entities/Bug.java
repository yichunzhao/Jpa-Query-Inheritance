package com.ynz.jpa.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
//ToDo investigate hashcode generation due to inheritance by the lombok.
public class Bug extends Ticket {

    private int severity;

    private String rootCause;
}
