package com.ynz.jpa.controllers;

import com.ynz.jpa.config.MyTestConfiguration;
import com.ynz.jpa.dto.ApplicationDto;
import com.ynz.jpa.dto.BugDto;
import com.ynz.jpa.dto.ReleaseDto;
import com.ynz.jpa.service.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * demo an Integration test on a controller. An integration test includes all layers.
 * difference between mockMvc and testRestTemplate
 * <p>
 * mockMvc is good for testing server-side logic;
 * testRestTemplate is good for testing services from a client-side.
 * So it is good to have both type of tests.
 * </p>
 *
 * <p>
 * @Sql Path Resource Semantics
 * Each path is interpreted as a Spring Resource. A plain path (for example, "schema.sql") is treated as a classpath
 * resource that is relative to the package in which the test class is defined. A path starting with a slash is treated
 * as an absolute classpath resource (for example, "/org/example/schema.sql"). A path that references a URL
 * (for example, a path prefixed with classpath:, file:, http:) is loaded by using the specified resource protocol.
 * </p>
 */

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

    @Autowired
    private ApplicationService applicationService;


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
    @Sql({"/cleanTables.sql", "/populateTestData.sql"})//@Sql happens before class level transactional.
    public void testGetApplicationById() {
        //Expected: the pre-populated data.
        String applicationName = "my-application-001";
        String owner = "ynz";
        int id = 1;

        String uri = uriBuilder.append(port).append("/trace/application/{id}").toString();

        ResponseEntity<ApplicationDto> response = testRestTemplate.getForEntity(
                uri, ApplicationDto.class, 1);
        assertThat(response.getStatusCode(), is(HttpStatus.FOUND));

        ApplicationDto applicationDto = response.getBody();
        assertThat(applicationDto, hasProperty("name", equalTo(applicationName)));
        assertThat(applicationDto, hasProperty("owner", equalTo(owner)));
        assertThat(applicationDto, hasProperty("id", equalTo(id)));
    }

    @Test
    @Sql({"/cleanTables.sql"})
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

    @Test
    void testCreateBug() {
        String rootCause = "root_cause";
        int severity = 10;

        BugDto toBeCreated = BugDto.builder().rootCause(rootCause).severity(severity).build();
        String url = uriBuilder.append(port).append("/trace/bug").toString();

        ResponseEntity<BugDto> response = testRestTemplate.postForEntity(url, toBeCreated, BugDto.class);
        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
        BugDto bugDto = response.getBody();

        assertThat(bugDto, hasProperty("rootCause", equalTo(rootCause)));
        assertThat(bugDto, hasProperty("severity", equalTo(severity)));
        assertThat(bugDto.getId(), greaterThan(0));
    }


}