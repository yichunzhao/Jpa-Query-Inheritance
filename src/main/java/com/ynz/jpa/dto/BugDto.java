package com.ynz.jpa.dto;

import com.ynz.jpa.entities.Bug;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Optional;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BugDto extends TicketDto {
    private int severity;
    private String rootCause;

    public BugDto(Integer id, String title, String description, ApplicationDto application, ReleaseDto release, int severity, String rootCause) {
        super(id, title, description, application, release);
        this.severity = severity;
        this.rootCause = rootCause;
    }

    public Bug toDomain() {
        Bug bug = new Bug();
        bug.setRootCause(this.rootCause);
        bug.setSeverity(this.severity);
        bug.setApplication(applicationDto != null ? applicationDto.toDomain() : null);
        bug.setRelease(releaseDto != null ? releaseDto.toDomain() : null);
        bug.setTitle(this.title);
        return bug;
    }

    public static BugDto toDto(Bug bug) {

        BugDto bugDto = new BugDto();

        bugDto.setId(bug.getId());
        bugDto.setTitle(bug.getTitle());

        bugDto.setDescription(bug.getDescription());

        Optional.ofNullable(bug.getApplication())
                .ifPresent(application -> bugDto.setApplicationDto(ApplicationDto.toDto(application)));

        Optional.ofNullable(bug.getRelease())
                .ifPresent(release -> bugDto.setReleaseDto(ReleaseDto.toDto(release)));

        bugDto.setRootCause(bug.getRootCause());
        bugDto.setSeverity(bug.getSeverity());

        return bugDto;
    }
}
