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

    @Column(nullable = false)
    private String name;

    @Column(length = 2000)
    private String description;
    private String owner;

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Release> releases = new HashSet<>();

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Bug> bugs = new HashSet<>();

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Enhancement> enhancements = new HashSet<>();

    public void addRelease(Release release) {
        this.releases.add(release);
    }

    public void addBug(Bug bug) {
        this.bugs.add(bug);
    }

    public void addEnhancement(Enhancement enhancement) {
        this.enhancements.add(enhancement);
    }


}
