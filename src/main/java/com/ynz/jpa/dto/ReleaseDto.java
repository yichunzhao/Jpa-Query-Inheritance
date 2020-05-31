package com.ynz.jpa.dto;

import com.ynz.jpa.entities.Application;
import com.ynz.jpa.entities.Bug;
import com.ynz.jpa.entities.Enhancement;
import com.ynz.jpa.entities.Release;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class ReleaseDto {
    private int id;

    private LocalDate releaseDate;

    private String description;

    private Application application;

    private Set<Bug> bugs = new HashSet<>();

    private Set<Enhancement> enhancements = new HashSet<>();

    public void addBug(Bug bug) {
        this.bugs.add(bug);
    }

    public void addEnhancement(Enhancement enhancement) {
        this.enhancements.add(enhancement);
    }

    public Release toDomain() {
        Release release = new Release();
        release.setApplication(this.application);
        release.setReleaseDate(this.releaseDate);
        release.setDescription(this.description);
        release.setEnhancements(this.enhancements);
        release.setBugs(this.bugs);
        return release;
    }

    public static ReleaseDto toDto(Release release) {
        ReleaseDto releaseDto = new ReleaseDto();
        releaseDto.setApplication(release.getApplication());
        releaseDto.setBugs(release.getBugs());
        releaseDto.setDescription(release.getDescription());
        releaseDto.setEnhancements(release.getEnhancements());
        releaseDto.setReleaseDate(release.getReleaseDate());
        return releaseDto;
    }

}
