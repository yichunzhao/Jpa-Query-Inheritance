package com.ynz.jpa.entities;

import lombok.Data;

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

    @OneToOne
    private Release release;

}
