package com.ynz.jpa.dto;


import com.ynz.jpa.entities.Application;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ApplicationDto {
    private String applicationName;
    private String description;
    private String owner;

    public Application toDomain() {
        Application application = new Application();
        application.setName(this.applicationName);
        application.setDescription(this.description);
        application.setOwner(this.owner);
        return application;
    }
}
