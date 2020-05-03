package com.ynz.jpa.dto;

import com.ynz.jpa.entities.Bug;
import lombok.Data;

@Data
public class BugDto extends TicketDto {
    private int severity;
    private String rootCause;

    public Bug toDomain() {
        Bug bug = new Bug();

        bug.setId(this.id);
        bug.setRootCause(this.rootCause);
        bug.setSeverity(this.severity);
        bug.setApplication(this.application);
        bug.setRelease(this.release);
        bug.setTitle(this.title);
        return bug;
    }

    public static BugDto toDto(Bug bug) {
        BugDto bugDto = new BugDto();
        bugDto.setApplication(bug.getApplication());
        bugDto.setDescription(bug.getDescription());
        bugDto.setId(bug.getId());
        bugDto.setRelease(bug.getRelease());
        bugDto.setRootCause(bug.getRootCause());
        bugDto.setSeverity(bug.getSeverity());
        bugDto.setTitle(bug.getTitle());
        return bugDto;
    }
}
