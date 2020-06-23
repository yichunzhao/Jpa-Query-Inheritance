package com.ynz.jpa.dto;

import com.ynz.jpa.converter.ReleaseConverter;
import com.ynz.jpa.entities.Release;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
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