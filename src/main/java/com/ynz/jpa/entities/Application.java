package com.ynz.jpa.entities;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
public class Application {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "Application_Name", nullable = false)
    private String name;

    @OneToOne
    private Release release;

}
