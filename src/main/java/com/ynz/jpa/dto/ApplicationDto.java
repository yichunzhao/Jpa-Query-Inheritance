package com.ynz.jpa.dto;


import com.ynz.jpa.entities.Application;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ApplicationDto {
    private int id;

    @NotEmpty
    private String name;

    private String description;

    @NotEmpty
    private String owner;

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
        return applicationDto;
    }
}
