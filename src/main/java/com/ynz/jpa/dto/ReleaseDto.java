package com.ynz.jpa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReleaseDto {
    private String id;

    @FutureOrPresent
    private String releaseDate;

    private String description;

    private Integer applicationId;

    private Set<@NotNull BugDto> bugDTOs = new HashSet<>();

    private Set<@NotNull EnhancementDto> enhancementDTOs = new HashSet<>();

    public void addBug(BugDto bugDto) {
        Optional.ofNullable(bugDTOs).ifPresent(b -> b.add(bugDto));
    }

    public void addEnhancement(EnhancementDto enhancementDto) {
        Optional.ofNullable(enhancementDTOs).ifPresent(e -> e.add(enhancementDto));
    }


}
