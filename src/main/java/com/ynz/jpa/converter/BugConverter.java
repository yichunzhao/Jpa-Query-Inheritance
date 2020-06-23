package com.ynz.jpa.converter;

import com.ynz.jpa.dto.ApplicationDto;
import com.ynz.jpa.dto.BugDto;
import com.ynz.jpa.dto.ReleaseDto;
import com.ynz.jpa.entities.Bug;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component("bugConverter")
@NoArgsConstructor
@Scope("prototype")
public class BugConverter implements Converter<BugDto, Bug> {
    @Setter
    private ApplicationConverter applicationConverter;

    @Setter
    private ReleaseConverter releaseConverter;

    @Override
    public BugDto toDto(Bug bug) {
        if (bug == null) return null;

        BugDto bugDto = new BugDto();

        bugDto.setId(bug.getId());
        bugDto.setTitle(bug.getTitle());

        bugDto.setDescription(bug.getDescription());

        Optional.ofNullable(bug.getApplication())
                .ifPresent(application -> bugDto.setApplicationDto(applicationConverter.toDto(application)));

        Optional.ofNullable(bug.getRelease())
                .ifPresent(release -> bugDto.setReleaseDto(releaseConverter.toDto(release)));

        bugDto.setRootCause(bug.getRootCause());
        bugDto.setSeverity(bug.getSeverity());

        return bugDto;
    }

    @Override
    public Bug toDomain(BugDto bugDto) {
        if (bugDto == null) return null;

        Bug bug = new Bug();
        bug.setRootCause(bugDto.getRootCause());
        bug.setSeverity(bugDto.getSeverity());

        ApplicationDto applicationDto = bugDto.getApplicationDto();
        bug.setApplication(applicationDto != null ? applicationConverter.toDomain(applicationDto) : null);

        ReleaseDto releaseDto = bugDto.getReleaseDto();
        bug.setRelease(releaseDto != null ? releaseConverter.toDomain(releaseDto) : null);

        bug.setTitle(bugDto.getTitle());

        return bug;
    }
}
