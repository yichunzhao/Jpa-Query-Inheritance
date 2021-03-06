package com.ynz.jpa.dto;

import com.ynz.jpa.config.ConvertersForTest;
import com.ynz.jpa.converter.EnhancementConverter;
import com.ynz.jpa.entities.Enhancement;
import com.ynz.jpa.model.Priority;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ConvertersForTest.class})
@Slf4j
class EnhancementDtoTest {

    @Autowired
    private EnhancementConverter enhancementConverter;

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

    @Test
    public void testToDomain() {
        String priority = Priority.HIGH.name();
        EnhancementDto enhancementDto = EnhancementDto.builder().priority(priority).build();
        assertThat(enhancementDto, hasProperty("priority", equalTo(priority)));
        assertThat(enhancementDto, hasProperty("duplicate", is(false)));
        assertThat(enhancementDto, hasProperty("releaseDto", is(nullValue())));
        assertThat(enhancementDto, hasProperty("applicationDto", is(nullValue())));
    }

    @Test
    public void testToDtoExposingNullPoint() {

        Enhancement enhancement = new Enhancement();
        EnhancementDto enhancementDto = enhancementConverter.toDto(enhancement);
        assertThat(enhancementDto, hasProperty("releaseDto", is(nullValue())));
        assertThat(enhancementDto, hasProperty("applicationDto", is(nullValue())));
        assertThat(enhancementDto, hasProperty("priority", is(nullValue())));
        assertThat(enhancementDto, hasProperty("duplicate", is(false)));
    }

}