package com.ynz.jpa.dto;

import com.ynz.jpa.converter.ApplicationConverter;
import com.ynz.jpa.converter.BugConverter;
import com.ynz.jpa.converter.EnhancementConverter;
import com.ynz.jpa.converter.ReleaseConverter;
import com.ynz.jpa.entities.Release;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ApplicationConverter.class, BugConverter.class, EnhancementConverter.class, ReleaseConverter.class})
class ReleaseDtoTest {
    @Autowired
    private ReleaseConverter releaseConverter;

    @Test
    public void testToDomain() {
        ReleaseDto releaseDto = ReleaseDto.builder().build();
        Release release = releaseConverter.toDomain(releaseDto);

        assertThat(release, hasProperty("id", is(nullValue())));
        assertThat(release, hasProperty("description", is(nullValue())));
        assertThat(release, hasProperty("application", is(nullValue())));
        assertThat(release, hasProperty("releaseDate", is(nullValue())));
        assertThat(release, hasProperty("bugs", hasSize(0)));
        assertThat(release, hasProperty("enhancements", hasSize(0)));
    }

    @Test
    public void testToDto() {
        Release release = new Release();
        ReleaseDto releaseDto = releaseConverter.toDto(release);

        assertThat(releaseDto, hasProperty("id", is(blankString())));
        assertThat(releaseDto, hasProperty("description", blankString()));
        assertThat(releaseDto, hasProperty("releaseDate", blankString()));
        assertThat(releaseDto, hasProperty("bugDTOs", hasSize(0)));
        assertThat(releaseDto, hasProperty("enhancementDTOs", hasSize(0)));
    }

}