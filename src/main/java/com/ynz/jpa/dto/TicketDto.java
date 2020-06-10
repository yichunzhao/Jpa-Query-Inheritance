package com.ynz.jpa.dto;

import com.ynz.jpa.entities.Application;
import com.ynz.jpa.entities.Release;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class TicketDto {
    protected Integer id;
    protected String title;
    protected String description;
    protected ApplicationDto applicationDto;
    protected ReleaseDto releaseDto;
}
