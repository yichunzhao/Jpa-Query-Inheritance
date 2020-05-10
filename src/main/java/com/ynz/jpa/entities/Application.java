package com.ynz.jpa.entities;


import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
public class Application {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(length = 2000)
    private String description;

    @Column(nullable = false, unique = true)
    private String owner;

    @OneToMany(mappedBy = "application", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Release> releases = new HashSet<>();

    @OneToMany(mappedBy = "application", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Bug> bugs = new HashSet<>();

    @OneToMany(mappedBy = "application", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Enhancement> enhancements = new HashSet<>();

    public void addRelease(Release release) {
        this.releases.add(release);
        release.setApplication(this);
    }

    public void addBug(Bug bug) {
        this.bugs.add(bug);
        bug.setApplication(this);
    }

    public void addEnhancement(Enhancement enhancement) {
        this.enhancements.add(enhancement);
        enhancement.setApplication(this);
    }

}
