package com.ynz.jpa.converter;

import com.ynz.jpa.dto.ApplicationDto;
import com.ynz.jpa.dto.EnhancementDto;
import com.ynz.jpa.dto.ReleaseDto;
import com.ynz.jpa.entities.Enhancement;
import com.ynz.jpa.model.Priority;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@NoArgsConstructor
@Scope("prototype")
public class EnhancementConverter implements Converter<EnhancementDto, Enhancement> {
    @Setter
    private ApplicationConverter applicationConverter;

    @Setter
    private ReleaseConverter releaseConverter;

    @Override
    public Enhancement toDomain(EnhancementDto enhancementDto) {
        if (enhancementDto == null) return null;

        Enhancement enhancement = new Enhancement();
        enhancement.setDuplicate(enhancementDto.isDuplicate());

        String priority = enhancementDto.getPriority();
        enhancement.setPriority(priority != null ? Priority.valueOf(priority) : null);

        ApplicationDto applicationDto = enhancementDto.getApplicationDto();
        enhancement.setApplication(applicationDto != null ? applicationConverter.toDomain(applicationDto) : null);

        ReleaseDto releaseDto = enhancementDto.getReleaseDto();
        enhancement.setRelease(releaseDto != null ? releaseConverter.toDomain(releaseDto) : null);

        enhancement.setDescription(enhancementDto.getDescription());
        enhancement.setTitle(enhancementDto.getTitle());

        return enhancement;
    }

    @Override
    public EnhancementDto toDto(Enhancement enhancement) {
        if (enhancement == null) return null;

        EnhancementDto enhancementDto = new EnhancementDto();

        enhancementDto.setId(enhancement.getId());
        enhancementDto.setTitle(enhancement.getTitle());
        enhancementDto.setDescription(enhancement.getDescription());

        Optional.ofNullable(enhancement.getApplication())
                .ifPresent(a -> enhancementDto.setApplicationDto(applicationConverter.toDto(a)));

        Optional.ofNullable(enhancement.getRelease())
                .ifPresent(r -> enhancementDto.setReleaseDto(releaseConverter.toDto(r)));

        enhancementDto.setDuplicate(enhancement.isDuplicate());

        Optional.ofNullable(enhancement.getPriority())
                .ifPresent(p -> enhancementDto.setPriority(p.name()));

        return enhancementDto;
    }

}


