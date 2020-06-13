package com.ynz.jpa.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@MappedSuperclass
@JsonIgnoreProperties("application")

public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;
    private String description;

    @ManyToOne
    @JoinColumn
    @EqualsAndHashCode.Exclude
    private Application application;

    @ManyToOne
    @JoinColumn
    @EqualsAndHashCode.Exclude
    private Release release;

}
