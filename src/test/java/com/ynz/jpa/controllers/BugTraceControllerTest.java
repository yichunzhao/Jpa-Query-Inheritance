package com.ynz.jpa.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ynz.jpa.dto.ApplicationDto;
import com.ynz.jpa.entities.Application;
import com.ynz.jpa.entities.Bug;
import com.ynz.jpa.entities.Enhancement;
import com.ynz.jpa.service.IApplicationService;
import com.ynz.jpa.service.IReleaseService;
import com.ynz.jpa.service.ITicketService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit test for a Controller alone. Cutting across the service layer by mocking it.
 */

@ExtendWith(SpringExtension.class)
@WebMvcTest(BugTraceController.class)
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
    }

    @Test
    public void testCreateApplicationIsExited_ThenReturnConflictStatusCode() throws Exception {
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
    }


}