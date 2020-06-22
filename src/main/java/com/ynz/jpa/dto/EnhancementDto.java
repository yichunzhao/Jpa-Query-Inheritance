package com.ynz.jpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class EnhancementDto extends TicketDto {

    @NotNull
    private boolean duplicate;

    @NotEmpty(message = "must have a priority.")
    private String priority;

    public EnhancementDto(Integer id, String title, String description, ApplicationDto applicationDto,
                          ReleaseDto releaseDto, boolean duplicate, String priority) {
        super(id, title, description, applicationDto, releaseDto);
        this.duplicate = duplicate;
        this.priority = priority;
    }


}
