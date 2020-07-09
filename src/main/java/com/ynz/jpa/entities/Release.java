package com.ynz.jpa.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@JsonIgnoreProperties("application")
public class Release {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "Release_Date")
    private LocalDate releaseDate;

    private String description;

    @ManyToOne
    @JoinColumn
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Application application;

    @OneToMany(mappedBy = "release", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Bug> bugs = new HashSet<>();

    @OneToMany(mappedBy = "release", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Enhancement> enhancements = new HashSet<>();

    public void linkToApplication(Application application) {
        application.addRelease(this);
    }

    public void addBug(Bug bug) {
        this.bugs.add(bug);
        bug.setRelease(this);
    }

    public void addEnhancement(Enhancement enhancement) {
        this.enhancements.add(enhancement);
        enhancement.setRelease(this);
    }

}
