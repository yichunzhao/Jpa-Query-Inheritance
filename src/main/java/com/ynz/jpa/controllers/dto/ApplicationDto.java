package com.ynz.jpa.controllers.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ApplicationDto {
    private String applicationName;
    private String releaseDate;
}
