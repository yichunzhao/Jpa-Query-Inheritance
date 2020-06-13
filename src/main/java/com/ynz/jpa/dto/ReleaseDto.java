package com.ynz.jpa.dto;

import com.ynz.jpa.entities.Release;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReleaseDto {
    private String id;

    @FutureOrPresent
    private String releaseDate;

    private String description;

    private Integer applicationId;

    private Set<@NotNull BugDto> bugDTOs = new HashSet<>();

    private Set<@NotNull EnhancementDto> enhancementDTOs = new HashSet<>();

    public void addBug(BugDto bugDto) {
        Optional.ofNullable(bugDTOs).ifPresent(b -> b.add(bugDto));
    }

    public void addEnhancement(EnhancementDto enhancementDto) {
        Optional.ofNullable(enhancementDTOs).ifPresent(e -> e.add(enhancementDto));
    }

    public Release toDomain() {
        Release release = new Release();
        //release.setApplication(this.application);
        release.setReleaseDate(releaseDate != null ? LocalDate.parse(this.releaseDate) : null);
        release.setDescription(description);
        release.setEnhancements(enhancementDTOs != null ? this.enhancementDTOs.stream().map(EnhancementDto::toDomain).collect(toSet()) : new HashSet<>());
        release.setBugs(bugDTOs != null ? this.bugDTOs.stream().map(BugDto::toDomain).collect(toSet()) : new HashSet<>());
        return release;
    }

    public static ReleaseDto toDto(Release release) {
        ReleaseDto releaseDto = new ReleaseDto();

        Integer id = release.getId();
        releaseDto.setId(id != null ? id.toString() : "");

        releaseDto.setDescription(Optional.ofNullable(release.getDescription()).orElse(""));

        releaseDto.setBugDTOs(release.getBugs().stream().map(b -> BugDto.toDto(b)).collect(toSet()));
        releaseDto.setEnhancementDTOs(release.getEnhancements().stream()
                .map(e -> EnhancementDto.toDto(e)).collect(toSet()));
        releaseDto.setReleaseDate(release.getReleaseDate() != null ? release.getReleaseDate().toString() : "");

        return releaseDto;
    }

}
