package com.ynz.jpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BugDto extends TicketDto {
    private int severity;
    private String rootCause;

    public BugDto(Integer id, String title, String description, ApplicationDto application,
                  ReleaseDto release, int severity, String rootCause) {
        super(id, title, description, application, release);
        this.severity = severity;
        this.rootCause = rootCause;
    }

}
