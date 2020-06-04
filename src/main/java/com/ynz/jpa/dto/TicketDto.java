package com.ynz.jpa.dto;

import com.ynz.jpa.entities.Application;
import com.ynz.jpa.entities.Release;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TicketDto {
    protected int id;
    protected String title;
    protected String description;
    protected Application application;
    protected Release release;
}
