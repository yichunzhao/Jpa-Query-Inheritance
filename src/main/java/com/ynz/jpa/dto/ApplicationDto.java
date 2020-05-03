package com.ynz.jpa.dto;


import com.ynz.jpa.entities.Application;
import lombok.Data;

@Data
public class ApplicationDto {
    private String name;
    private String description;
    private String owner;

    public Application toDomain() {
        Application application = new Application();
        application.setName(this.name);
        application.setDescription(this.description);
        application.setOwner(this.owner);
        return application;
    }

    public static ApplicationDto toDto(Application application) {
        ApplicationDto applicationDto = new ApplicationDto();
        applicationDto.setDescription(application.getDescription());
        applicationDto.setName(application.getName());
        applicationDto.setOwner(application.getOwner());
        return applicationDto;
    }
}
