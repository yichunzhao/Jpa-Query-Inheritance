package com.ynz.jpa.dto;

import com.ynz.jpa.entities.Release;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class ReleaseDtoTest {

    @Test
    public void testToDomain() {
        ReleaseDto releaseDto = ReleaseDto.builder().build();
        Release release = releaseDto.toDomain();

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
        ReleaseDto releaseDto = ReleaseDto.toDto(release);

        assertThat(releaseDto, hasProperty("id", is(blankString())));
        assertThat(releaseDto, hasProperty("description", blankString()));
        assertThat(releaseDto, hasProperty("releaseDate", blankString()));
        assertThat(releaseDto, hasProperty("bugDTOs", hasSize(0)));
        assertThat(releaseDto, hasProperty("enhancementDTOs", hasSize(0)));
    }

}