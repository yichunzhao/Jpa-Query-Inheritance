package com.ynz.jpa.dto;

import com.ynz.jpa.converter.ApplicationConverter;
import com.ynz.jpa.entities.Application;
import com.ynz.jpa.entities.Enhancement;
import com.ynz.jpa.model.Priority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Optional;

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

    public Enhancement toDomain() {
        Enhancement enhancement = new Enhancement();
        enhancement.setDuplicate(this.duplicate);
        enhancement.setPriority(priority != null ? Priority.valueOf(this.priority) : null);
        enhancement.setApplication(applicationDto != null ? applicationDto.toDomain() : null);
        enhancement.setRelease(releaseDto != null ? releaseDto.toDomain() : null);
        enhancement.setDescription(this.description);
        enhancement.setTitle(this.title);
        return enhancement;
    }

    public static EnhancementDto toDto(Enhancement enhancement) {
        EnhancementDto enhancementDto = new EnhancementDto();

        enhancementDto.setId(enhancement.getId());
        enhancementDto.setTitle(enhancement.getTitle());
        enhancementDto.setDescription(enhancement.getDescription());

        Optional.ofNullable(enhancement.getApplication())
                .ifPresent(a -> enhancementDto.setApplicationDto(ApplicationConverter.create().toDto(a)));

        Optional.ofNullable(enhancement.getRelease())
                .ifPresent(r -> enhancementDto.setReleaseDto(ReleaseDto.toDto(r)));

        enhancementDto.setDuplicate(enhancement.isDuplicate());

        Optional.ofNullable(enhancement.getPriority())
                .ifPresent(p -> enhancementDto.setPriority(p.name()));

        return enhancementDto;
    }
}
