package com.ynz.jpa.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Enhancement extends Ticket {
    private Boolean duplicate;

    @Enumerated(EnumType.STRING)
    private Priority priority;
}
