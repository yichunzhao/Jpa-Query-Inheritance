package com.ynz.jpa.controllers;

import com.ynz.jpa.dto.ApplicationDto;
import com.ynz.jpa.dto.ReleaseDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * demo an Integration test on a controller. An integration test includes all layers.
 * difference between mockMvc and testRestTemplate
 * <p>
 * mockMvc may used for server-side
 * testRestTemplate used for testing client-side rest services.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@Slf4j
class BugTraceControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testCreateApplication() {
        String applicationName = "new_app";
        String owner = "ynz";
        ApplicationDto toBeCreated = ApplicationDto.builder().name(applicationName).owner(owner).build();

        ResponseEntity<ApplicationDto> response = testRestTemplate.postForEntity("/trace/application", toBeCreated, ApplicationDto.class);
        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));

        ApplicationDto applicationDto = response.getBody();
        assertThat(applicationDto, hasProperty("name", equalTo(applicationName)));
        assertThat(applicationDto, hasProperty("owner", equalTo(owner)));
        assertThat(applicationDto.getId(), greaterThan(0));
    }

    @Test
    void testCreateRelease() {
        String description = "release_description";
        String releaseDate = LocalDate.of(2020, 10, 12).toString();
        ReleaseDto toBeCreated = ReleaseDto.builder().description(description).releaseDate(releaseDate).build();

        ResponseEntity<ReleaseDto> response = testRestTemplate.postForEntity("/trace/release", toBeCreated, ReleaseDto.class);
        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));

        ReleaseDto releaseDto = response.getBody();
        assertThat(releaseDto, hasProperty("description", equalTo(description)));
        assertThat(releaseDto, hasProperty("releaseDate", equalTo(releaseDate)));
        assertThat(releaseDto.getId(), greaterThan(0));
    }

}