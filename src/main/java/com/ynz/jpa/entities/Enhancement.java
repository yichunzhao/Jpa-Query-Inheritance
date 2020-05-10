package com.ynz.jpa.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Entity
public class Enhancement extends Ticket {
    private Boolean duplicate;

    @Enumerated(EnumType.STRING)
    private Priority priority;
}
