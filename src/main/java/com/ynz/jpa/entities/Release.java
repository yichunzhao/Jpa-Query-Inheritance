package com.ynz.jpa.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Release {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "Release_Date")
    private LocalDate releaseDate;

    @OneToOne
    private Application application;

}
