package com.ynz.jpa.controllers;

import com.ynz.jpa.config.MyTestConfiguration;
import com.ynz.jpa.dto.ApplicationDto;
import com.ynz.jpa.dto.ReleaseDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
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
 * mockMvc maybe used for a server-side or an unit test;
 * testRestTemplate used for testing services from a client-side.
 * </p>
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@Import(MyTestConfiguration.class)
@Slf4j
class BugTraceControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private StringBuilder uriBuilder;

    @Test
    public void testCreateApplication() {
        String applicationName = "new_app";
        String owner = "ynz";
        ApplicationDto toBeCreated = ApplicationDto.builder().name(applicationName).owner(owner).build();

        ResponseEntity<ApplicationDto> response = testRestTemplate.postForEntity(
                uriBuilder.append(port).append("/trace/application").toString(), toBeCreated, ApplicationDto.class);
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

        ResponseEntity<ReleaseDto> response = testRestTemplate.postForEntity(
                uriBuilder.append(port).append("/trace/release").toString(), toBeCreated, ReleaseDto.class);
        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));

        ReleaseDto releaseDto = response.getBody();
        assertThat(releaseDto, hasProperty("description", equalTo(description)));
        assertThat(releaseDto, hasProperty("releaseDate", equalTo(releaseDate)));
        assertThat(releaseDto.getId(), greaterThan(0));
    }

}