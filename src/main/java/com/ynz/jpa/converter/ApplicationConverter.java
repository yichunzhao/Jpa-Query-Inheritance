package com.ynz.jpa.converter;

import com.ynz.jpa.dto.ApplicationDto;
import com.ynz.jpa.dto.BugDto;
import com.ynz.jpa.dto.EnhancementDto;
import com.ynz.jpa.dto.ReleaseDto;
import com.ynz.jpa.entities.Application;
import lombok.NoArgsConstructor;

import static java.util.stream.Collectors.toSet;

@NoArgsConstructor
public class ApplicationConverter implements converter<Application, ApplicationDto> {

    public static  ApplicationConverter create() {
        return new ApplicationConverter();
    }

    @Override
    public ApplicationDto toDto(Application application) {
        ApplicationDto applicationDto = new ApplicationDto();
        applicationDto.setId(application.getId());
        applicationDto.setDescription(application.getDescription());
        applicationDto.setName(application.getName());
        applicationDto.setOwner(application.getOwner());
        applicationDto.setReleaseDTOs(application.getReleases().stream().map(r -> ReleaseDto.toDto(r)).collect(toSet()));

        applicationDto.setBugDTOs(application.getBugs().stream().map(b -> BugDto.toDto(b)).collect(toSet()));
        applicationDto.setEnhancementDTOs(application.getEnhancements().stream().map(e -> EnhancementDto.toDto(e)).collect(toSet()));

        return applicationDto;
    }

}
