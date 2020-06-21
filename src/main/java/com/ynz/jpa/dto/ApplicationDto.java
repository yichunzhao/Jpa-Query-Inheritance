package com.ynz.jpa.dto;


import com.ynz.jpa.entities.Application;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationDto {
    private Integer id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    @NotEmpty
    private String owner;

    private Set<ReleaseDto> releaseDTOs;

    private Set<BugDto> bugDTOs;

    private Set<EnhancementDto> enhancementDTOs;

    public Application toDomain() {
        Application application = new Application();
        application.setId(this.id);
        application.setName(this.name);
        application.setDescription(this.description);
        application.setOwner(this.owner);

        return application;
    }

//    public static ApplicationDto toDto(Application application) {
//        ApplicationDto applicationDto = new ApplicationDto();
//        applicationDto.setId(application.getId());
//        applicationDto.setDescription(application.getDescription());
//        applicationDto.setName(application.getName());
//        applicationDto.setOwner(application.getOwner());
//        applicationDto.setReleaseDTOs(application.getReleases().stream().map(r -> ReleaseDto.toDto(r)).collect(toSet()));
//
//        applicationDto.setBugDTOs(application.getBugs().stream().map(b -> BugDto.toDto(b)).collect(toSet()));
//        applicationDto.setEnhancementDTOs(application.getEnhancements().stream().map(e -> EnhancementDto.toDto(e)).collect(toSet()));
//
//        return applicationDto;
//    }
}
