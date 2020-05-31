package com.ynz.jpa.service;

import com.ynz.jpa.entities.Application;
import com.ynz.jpa.exceptions.NotFoundException;
import com.ynz.jpa.repositories.ApplicationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = NONE)
class ApplicationServiceTest {

    @Autowired
    private ApplicationService applicationService;

    @MockBean
    private ApplicationRepository mockAppRepository;

    @Test
    public void testAddApplication() {
        Application application = new Application();
        application.setName("new-application-1");
        application.setOwner("owner-001");

        //mockAppRepository.
        when(mockAppRepository.save(application)).thenReturn(application);

        Application returned = applicationService.addApplication(application);

        assertThat(returned, is(application));
    }

    @Test
    public void testGetApplicationById() {
        Application application = new Application();
        application.setName("new-application-1");
        application.setOwner("owner-001");

        int id = 123;

        when(mockAppRepository.findById(id)).thenReturn(Optional.of(application));

        Application found = applicationService.getApplicationById(id);

        assertThat(found, is(application));
    }

    @Test
    public void whenTargetApplicationNotFound_ThenThrowNotFoundException() {
        int id = 124;

        StringBuilder msg = new StringBuilder("id: ");
        msg.append(id).append(" is not found.");

        when(mockAppRepository.findById(id)).thenThrow(new NotFoundException(msg.toString()));

        Exception thrown = assertThrows(NotFoundException.class, () -> applicationService.getApplicationById(id));

        assertThat(thrown.getMessage(), is(msg.toString()));
    }

}