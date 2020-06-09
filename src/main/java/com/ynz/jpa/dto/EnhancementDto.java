package com.ynz.jpa.dto;

import com.ynz.jpa.entities.Enhancement;
import com.ynz.jpa.entities.Priority;
import lombok.*;

import java.util.Optional;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class EnhancementDto extends TicketDto {
    private Boolean duplicate;
    private String priority;

    public Enhancement toDomain() {
        Enhancement enhancement = new Enhancement();
        enhancement.setDuplicate(this.duplicate);
        enhancement.setPriority(priority!=null?Priority.valueOf(this.priority):null);
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
