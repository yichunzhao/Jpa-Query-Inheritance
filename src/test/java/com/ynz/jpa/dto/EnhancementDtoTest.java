package com.ynz.jpa.dto;

import com.ynz.jpa.entities.Priority;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@Slf4j
@DisplayName("EnhancementDto tests")
class EnhancementDtoTest {

    @BeforeEach
    void init() {
        log.info(".......................................................................");
    }

    @AfterEach
    void clean() {
        log.info(".......................................................................");
    }

    @Test
    public void givenTwoSameEnhancements_DifferentTicketDto_ThenHavingDifferentHashCode() {
        log.info("givenTwoSameEnhancements_DifferentTicketDto_ThenHavingDifferentHashCode");

        EnhancementDto enhancementDto = new EnhancementDto();
        enhancementDto.setTitle("a title");
        enhancementDto.setDescription("description");

        enhancementDto.setPriority(Priority.MEDIUM.name());
        enhancementDto.setDuplicate(true);

        log.info("EnhancementDto hashcode: " + enhancementDto.hashCode());

        EnhancementDto anotherEnhancementDto = new EnhancementDto();
        anotherEnhancementDto.setTitle("another title");
        anotherEnhancementDto.setDescription("another description");

        anotherEnhancementDto.setPriority(Priority.MEDIUM.name());
        anotherEnhancementDto.setDuplicate(true);

        log.info("Another EnhancementDto hashcode: " + anotherEnhancementDto.hashCode());

        assertThat(enhancementDto.hashCode(), not(anotherEnhancementDto.hashCode()));
    }

    @Test
    public void givenTwoSameEnhancements_SameTicketDto_ThenHavingSameHashCode() {
        log.info("givenTwoSameEnhancements_SameTicketDto_ThenHavingSameHashCode");
        EnhancementDto enhancementDto = new EnhancementDto();
        enhancementDto.setTitle("a title");
        enhancementDto.setDescription("description");

        enhancementDto.setPriority(Priority.MEDIUM.name());
        enhancementDto.setDuplicate(true);

        log.info("EnhancementDto hashcode: " + enhancementDto.hashCode());

        EnhancementDto anotherEnhancementDto = new EnhancementDto();
        anotherEnhancementDto.setTitle("a title");
        anotherEnhancementDto.setDescription("description");

        anotherEnhancementDto.setPriority(Priority.MEDIUM.name());
        anotherEnhancementDto.setDuplicate(true);

        log.info("Another EnhancementDto hashcode: " + anotherEnhancementDto.hashCode());

        assertThat(enhancementDto.hashCode(), is(anotherEnhancementDto.hashCode()));

    }

}