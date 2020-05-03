package com.ynz.jpa.entities;

import lombok.Data;
import org.hibernate.mapping.ToOne;

import javax.persistence.*;

@Data
@MappedSuperclass
public class Ticket {
    @Id
    @GeneratedValue
    private int id;

    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "fk_application")
    private Application application;

    @ManyToOne
    @JoinColumn(name = "fk_release")
    private Release release;

}
