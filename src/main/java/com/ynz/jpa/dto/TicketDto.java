package com.ynz.jpa.dto;

import com.ynz.jpa.entities.Application;
import com.ynz.jpa.entities.Release;
import lombok.Data;

@Data
public class TicketDto {
    protected int id;
    protected String title;
    protected String description;
    protected Application application;
    protected Release release;
}
