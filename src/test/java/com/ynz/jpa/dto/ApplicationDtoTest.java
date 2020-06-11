package com.ynz.jpa.dto;

import com.ynz.jpa.entities.Application;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class ApplicationDtoTest {

    @Test
    public void testToDomainExposingNullPoint() {
        ApplicationDto applicationDto = ApplicationDto.builder().build();
        Application application = applicationDto.toDomain();

        assertThat(application, hasProperty("id"));
        assertThat(application, hasProperty("name"));
        assertThat(application, hasProperty("owner"));
        assertThat(application, hasProperty("releases"));
        assertThat(application, hasProperty("bugs"));
        assertThat(application, hasProperty("enhancements"));
    }

    @Test
    public void testToDtoExposingNullPoint() {
        Application application = new Application();

        ApplicationDto applicationDto = ApplicationDto.toDto(application);
        assertThat(applicationDto, hasProperty("id", is(nullValue())));
        assertThat(applicationDto, hasProperty("name", is(nullValue())));
        assertThat(applicationDto, hasProperty("owner", is(nullValue())));
        assertThat(applicationDto, hasProperty("releaseDTOs", hasSize(0)));
        assertThat(applicationDto, hasProperty("bugDTOs", hasSize(0)));
        assertThat(applicationDto, hasProperty("enhancementDTOs", hasSize(0)));
    }

}