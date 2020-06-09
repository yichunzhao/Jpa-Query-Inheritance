package com.ynz.jpa.dto;


import com.ynz.jpa.entities.Application;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
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

    private String description;

    @NotEmpty
    private String owner;

    private Set<ReleaseDto> releaseDTOs = new HashSet<>();

    public Application toDomain() {
        Application application = new Application();
        application.setId(this.id);
        application.setName(this.name);
        application.setDescription(this.description);
        application.setOwner(this.owner);

        return application;
    }

    public static ApplicationDto toDto(Application application) {
        ApplicationDto applicationDto = new ApplicationDto();
        applicationDto.setId(application.getId());
        applicationDto.setDescription(application.getDescription());
        applicationDto.setName(application.getName());
        applicationDto.setOwner(application.getOwner());
        applicationDto.setReleaseDTOs(application.getReleases()
                .stream().map(r -> ReleaseDto.toDto(r)).collect(toSet()));

        return applicationDto;
    }
}
