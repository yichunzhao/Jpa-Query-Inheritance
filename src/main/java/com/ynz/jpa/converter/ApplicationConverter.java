package com.ynz.jpa.converter;

import com.ynz.jpa.dto.ApplicationDto;
import com.ynz.jpa.entities.Application;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static java.util.stream.Collectors.toSet;

@Component
@NoArgsConstructor
@Scope("prototype")
public class ApplicationConverter implements Converter<ApplicationDto, Application> {
    private BugConverter bugConverter;
    private EnhancementConverter enhancementConverter;
    private ReleaseConverter releaseConverter;

    @Autowired
    public ApplicationConverter(BugConverter bugConverter, EnhancementConverter enhancementConverter, ReleaseConverter releaseConverter) {
        this.bugConverter = bugConverter;
        this.enhancementConverter = enhancementConverter;
        this.releaseConverter = releaseConverter;
    }

    @PostConstruct
    private void postConstruct() {
        bugConverter.setApplicationConverter(this);
        enhancementConverter.setApplicationConverter(this);
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
        applicationDto.setReleaseDTOs(application.getReleases().stream().map(releaseConverter::toDto).collect(toSet()));
        applicationDto.setBugDTOs(application.getBugs().stream().map(bugConverter::toDto).collect(toSet()));
        applicationDto.setEnhancementDTOs(application.getEnhancements().stream().map(enhancementConverter::toDto).collect(toSet()));

        return applicationDto;
    }

}
