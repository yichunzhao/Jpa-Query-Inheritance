package com.ynz.jpa.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

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

}
