package com.ynz.jpa.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ynz.jpa.converter.ApplicationConverter;
import com.ynz.jpa.dto.ApplicationDto;
import com.ynz.jpa.dto.ReleaseDto;
import com.ynz.jpa.entities.Application;
import com.ynz.jpa.entities.Bug;
import com.ynz.jpa.entities.Enhancement;
import com.ynz.jpa.entities.Release;
import com.ynz.jpa.service.IApplicationService;
import com.ynz.jpa.service.IReleaseService;
import com.ynz.jpa.service.ITicketService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit test for a Controller alone. Cutting across the service layer by mocking it.
 */

@WebMvcTest(controllers = BugTraceController.class)
@Import(ApplicationConverter.class)
class BugTraceControllerTest {

    @MockBean
    private IApplicationService applicationService;

    @MockBean
    @Qualifier("bugService")
    private ITicketService<Bug> bugService;

    @MockBean
    @Qualifier("enhancementService")
    private ITicketService<Enhancement> enhancementService;

    @MockBean
    private IReleaseService releaseService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;


    @Autowired
    private ApplicationConverter applicationConverter;


    @Test
    public void testCreateApplication() throws Exception {
        String owner = "YNZ";
        String name = "My_Application";
        int id = 250;

        ApplicationDto applicationDto = ApplicationDto.builder().name(name).owner(owner).build();

        Application application = new Application();
        application.setOwner(owner);
        application.setName(name);
        application.setId(id);

        //mock service layer.
        when(applicationService.addApplication(any(Application.class))).thenReturn(application);

        //Simulate a post method.
        mockMvc.perform(post("/trace/application")
                .content(mapper.writeValueAsString(applicationDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.owner").value(owner));

        ArgumentCaptor<Application> argument = ArgumentCaptor.forClass(Application.class);
        verify(applicationService, times(1)).addApplication(argument.capture());
        assertThat(applicationConverter.toDomain(applicationDto), is(argument.getValue()));
    }

    @Test
    public void whenApplicationIsExited_ThenReturnConflictStatusCode() throws Exception {
        String owner = "YNZ";
        String name = "My_Application";

        ApplicationDto applicationDto = ApplicationDto.builder().name(name).owner(owner).build();

        Application application = new Application();
        application.setOwner(owner);
        application.setName(name);

        //mock service layer.
        when(applicationService.addApplication(any(Application.class))).thenReturn(null);

        //Simulate a post method.
        mockMvc.perform(post("/trace/application")
                .content(mapper.writeValueAsString(applicationDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());

        ArgumentCaptor<Application> argument = ArgumentCaptor.forClass(Application.class);
        verify(applicationService, times(1)).addApplication(argument.capture());
        assertThat(applicationConverter.toDomain(applicationDto), is(argument.getValue()));
    }

    @Test
    public void whenApplicationIsExisted_GetItById() throws Exception {
        Integer id = 100;
        String owner = "YNZ";
        String name = "My_Application";

        Application application = new Application();
        application.setId(id);
        application.setOwner(owner);
        application.setName(name);

        when(applicationService.getApplicationById(any(Integer.class))).thenReturn(application);

        mockMvc.perform(get("/trace/application/{id}", id).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isFound());

        ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(applicationService, times(1)).getApplicationById(argumentCaptor.capture());
        assertThat(id, is(argumentCaptor.getValue()));
    }

    @Test
    public void testCreateRelease() throws Exception {
        int releaseId = 12;
        String releaseDate = LocalDate.of(2020, 12, 10).toString();
        String releaseDescription = "release-description";

        ReleaseDto releaseDto = ReleaseDto.builder().releaseDate(releaseDate).description(releaseDescription).build();

        Release release = new Release();
        release.setId(releaseId);
        release.setReleaseDate(LocalDate.parse(releaseDate));
        release.setDescription(releaseDescription);

        when(releaseService.addRelease(any(Release.class))).thenReturn(release);

        mockMvc.perform(post("/trace/release")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(releaseDto))).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(releaseId))
                .andExpect(jsonPath("$.releaseDate").value(releaseDate))
                .andExpect(jsonPath("$.description").value(releaseDescription));

        ArgumentCaptor<Release> argument = ArgumentCaptor.forClass(Release.class);
        verify(releaseService, times(1)).addRelease(argument.capture());
        assertThat(releaseDto.toDomain(), is(argument.getValue()));
    }


}