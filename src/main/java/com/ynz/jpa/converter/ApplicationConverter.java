package com.ynz.jpa.converter;

import com.ynz.jpa.dto.ApplicationDto;
import com.ynz.jpa.dto.BugDto;
import com.ynz.jpa.dto.EnhancementDto;
import com.ynz.jpa.dto.ReleaseDto;
import com.ynz.jpa.entities.Application;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static java.util.stream.Collectors.toSet;

@Component
@Scope("prototype")
public class ApplicationConverter implements converter<ApplicationDto, Application> {

    public static ApplicationConverter create() {
        return new ApplicationConverter();
    }

    @Override
    public Application toDomain(ApplicationDto applicationDto) {
        if (applicationDto == null) return null;

        Application application = new Application();
        application.setId(applicationDto.getId());
        application.setName(applicationDto.getName());
        application.setDescription(applicationDto.getDescription());
        application.setOwner(applicationDto.getOwner());

        return application;
    }

    @Override
    public ApplicationDto toDto(Application application) {
        if (application == null) return null;

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
