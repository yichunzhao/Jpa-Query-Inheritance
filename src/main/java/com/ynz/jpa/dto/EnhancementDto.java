package com.ynz.jpa.dto;

import com.ynz.jpa.entities.Enhancement;
import com.ynz.jpa.entities.Priority;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class EnhancementDto extends TicketDto {
    private Boolean duplicate;
    private String priority;

    public Enhancement toDomain() {
        Enhancement enhancement = new Enhancement();
        enhancement.setDuplicate(this.duplicate);
        enhancement.setPriority(Priority.valueOf(this.priority));
        enhancement.setApplication(this.application);
        enhancement.setRelease(this.release);
        enhancement.setDescription(this.description);
        enhancement.setTitle(this.title);
        return enhancement;
    }

    public static EnhancementDto toDto(Enhancement enhancement) {
        EnhancementDto enhancementDto = new EnhancementDto();
        enhancementDto.setDuplicate(enhancement.getDuplicate());
        enhancementDto.setPriority(enhancement.getPriority().name());
        enhancementDto.setApplication(enhancement.getApplication());
        enhancementDto.setDescription(enhancement.getDescription());
        enhancementDto.setId(enhancement.getId());
        enhancementDto.setRelease(enhancement.getRelease());
        enhancementDto.setTitle(enhancement.getTitle());
        return enhancementDto;
    }
}
