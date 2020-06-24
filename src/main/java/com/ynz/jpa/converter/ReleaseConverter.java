package com.ynz.jpa.converter;

import com.ynz.jpa.dto.BugDto;
import com.ynz.jpa.dto.EnhancementDto;
import com.ynz.jpa.dto.ReleaseDto;
import com.ynz.jpa.entities.Release;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Component
@NoArgsConstructor
@Scope("prototype")
public class ReleaseConverter implements Converter<ReleaseDto, Release> {

    private BugConverter bugConverter;
    private EnhancementConverter enhancementConverter;

    @Autowired
    public ReleaseConverter(BugConverter bugConverter, EnhancementConverter enhancementConverter) {
        this.bugConverter = bugConverter;
        this.enhancementConverter = enhancementConverter;
    }

    @PostConstruct
    private void postConstruct() {
        bugConverter.setReleaseConverter(this);
        enhancementConverter.setReleaseConverter(this);
    }

    @Override
    public ReleaseDto toDto(Release release) {
        if (release == null) return null;

        ReleaseDto releaseDto = new ReleaseDto();

        Integer id = release.getId();
        releaseDto.setId(id != null ? id.toString() : "");

        releaseDto.setDescription(Optional.ofNullable(release.getDescription()).orElse(""));

        releaseDto.setBugDTOs(release.getBugs().stream().map(bugConverter::toDto).collect(toSet()));

        releaseDto.setEnhancementDTOs(release.getEnhancements().stream()
                .map(enhancementConverter::toDto).collect(toSet()));
        releaseDto.setReleaseDate(release.getReleaseDate() != null ? release.getReleaseDate().toString() : "");

        return releaseDto;
    }

    @Override
    public Release toDomain(ReleaseDto releaseDto) {
        if (releaseDto == null) return null;

        Release release = new Release();
        //release.setApplication(this.application);
        String releaseDate = releaseDto.getReleaseDate();
        release.setReleaseDate(releaseDate != null ? LocalDate.parse(releaseDate) : null);

        release.setDescription(releaseDto.getDescription());

        Set<EnhancementDto> enhancementDTOs = releaseDto.getEnhancementDTOs();
        release.setEnhancements(enhancementDTOs != null ? enhancementDTOs.stream()
                .map(enhancementConverter::toDomain).collect(toSet()) : new HashSet<>());

        Set<BugDto> bugDTOs = releaseDto.getBugDTOs();
        release.setBugs(bugDTOs != null ? bugDTOs.stream().map(bugConverter::toDomain).collect(toSet())
                : new HashSet<>());

        return release;

    }
}
