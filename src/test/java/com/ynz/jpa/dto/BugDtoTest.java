package com.ynz.jpa.dto;

import com.ynz.jpa.config.ConvertersForTest;
import com.ynz.jpa.converter.ApplicationConverter;
import com.ynz.jpa.converter.BugConverter;
import com.ynz.jpa.entities.Bug;
import com.ynz.jpa.model.Severity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ConvertersForTest.class})
class BugDtoTest {
    @Autowired
    private ApplicationConverter applicationConverter;

    @Autowired
    private BugConverter bugConverter;

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

        BugDto bugDto = bugConverter.toDto(bug);
        assertThat(bugDto, hasProperty("rootCause", is(nullValue())));
        assertThat(bugDto, hasProperty("severity", equalTo(0)));
        assertThat(bugDto, hasProperty("applicationDto", is(nullValue())));
        assertThat(bugDto, hasProperty("releaseDto", is(nullValue())));
    }

    @Test
    public void testToDomainExposingNullPoint() {
        BugDto bugDto = new BugDto();//using an empty ButDto to explore null point exception.
        Bug bug = bugConverter.toDomain(bugDto);

        assertThat(bug, hasProperty("rootCause", is(nullValue())));
        assertThat(bug, hasProperty("severity", equalTo(0)));
        assertThat(bug, hasProperty("application", is(nullValue())));
        assertThat(bug, hasProperty("release", is(nullValue())));
    }

}