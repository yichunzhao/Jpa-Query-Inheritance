package com.ynz.jpa.dto;

import com.ynz.jpa.entities.Bug;
import com.ynz.jpa.model.Severity;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


class BugDtoTest {


    @Test
    public void testLombokBuilderOnInheritance() {
        String rootCause = "root cause due to xxx";
        int severityLevel = Severity.FOUR.scale();

        ApplicationDto applicationDto = ApplicationDto.builder().owner("ynz").name("my application").description("have a fun").build();

        BugDto bugDto = BugDto.builder().applicationDto(applicationDto).severity(severityLevel).rootCause(rootCause).build();

        assertThat(bugDto, hasProperty("rootCause", equalTo(rootCause)));
        assertThat(bugDto, hasProperty("severity", equalTo(severityLevel)));
        assertThat(bugDto, hasProperty("applicationDto", is(notNullValue())));
        assertThat(bugDto, hasProperty("releaseDto", is(nullValue())));
    }

    @Test
    public void testToDtoExposingNullPoint() {
        Bug bug = new Bug();//using an empty bug to explore null point exceptions.
        BugDto bugDto = BugDto.toDto(bug);
        assertThat(bugDto, hasProperty("rootCause", is(nullValue())));
        assertThat(bugDto, hasProperty("severity", equalTo(0)));
        assertThat(bugDto, hasProperty("applicationDto", is(nullValue())));
        assertThat(bugDto, hasProperty("releaseDto", is(nullValue())));
    }

    @Test
    public void testToDomainExposingNullPoint() {
        BugDto bugDto = new BugDto();//using an empty ButDto to explore null point exception.
        Bug bug = bugDto.toDomain();

        assertThat(bug, hasProperty("rootCause", is(nullValue())));
        assertThat(bug, hasProperty("severity", equalTo(0)));
        assertThat(bug, hasProperty("application", is(nullValue())));
        assertThat(bug, hasProperty("release", is(nullValue())));
    }


}