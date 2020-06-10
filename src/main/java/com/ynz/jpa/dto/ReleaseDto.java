package com.ynz.jpa.dto;

import com.ynz.jpa.entities.Bug;
import com.ynz.jpa.entities.Enhancement;
import com.ynz.jpa.entities.Release;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReleaseDto {
    private Integer id;

    @FutureOrPresent
    private String releaseDate;

    private String description;

    private Integer applicationId;

    private Set<BugDto> bugs = new HashSet<>();

    private Set<EnhancementDto> enhancements = new HashSet<>();

    public void addBug(Bug bug) {
        this.bugs.add(BugDto.toDto(bug));
    }

    public void addEnhancement(Enhancement enhancement) {
        this.enhancements.add(EnhancementDto.toDto(enhancement));
    }

    public Release toDomain() {
        Release release = new Release();
        //release.setApplication(this.application);
        release.setReleaseDate(LocalDate.parse(this.releaseDate));
        release.setDescription(this.description);
        release.setEnhancements(enhancements != null ? this.enhancements.stream().map(EnhancementDto::toDomain).collect(toSet()) : new HashSet<>());
        release.setBugs(bugs != null ? this.bugs.stream().map(BugDto::toDomain).collect(toSet()) : new HashSet<>());
        return release;
    }

    public static ReleaseDto toDto(Release release) {
        ReleaseDto releaseDto = new ReleaseDto();
        releaseDto.setId(release.getId());

        //releaseDto.setApplicationId(release.getApplication().getId());

        releaseDto.setBugs(release.getBugs().stream().map(b -> BugDto.toDto(b)).collect(toSet()));
        releaseDto.setDescription(release.getDescription());
        releaseDto.setEnhancements(release.getEnhancements().stream()
                .map(e -> EnhancementDto.toDto(e)).collect(toSet()));
        releaseDto.setReleaseDate(release.getReleaseDate() != null ? release.getReleaseDate().toString() : "");

        return releaseDto;
    }

}
