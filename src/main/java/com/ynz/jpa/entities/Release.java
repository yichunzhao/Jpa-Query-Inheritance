package com.ynz.jpa.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Release {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "Release_Date")
    private LocalDate releaseDate;

    private String description;

    @ManyToOne
    @JoinColumn(name = "fk_Application")
    private Application application;

    @OneToMany(mappedBy = "release")
    private Set<Bug> bugs = new HashSet<>();

    @OneToMany(mappedBy = "release")
    private Set<Enhancement> enhancements = new HashSet<>();

    public void addBug(Bug bug) {
        this.bugs.add(bug);
    }

    public void addEnhancement(Enhancement enhancement) {
        this.enhancements.add(enhancement);
    }

}
