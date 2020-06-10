package com.ynz.jpa.repositories;

import com.ynz.jpa.entities.Application;
import com.ynz.jpa.entities.Enhancement;
import com.ynz.jpa.model.Priority;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @DataJpaTest provides some standard setup needed for testing the persistence layer:
 * <p>
 * configuring H2, an in-memory database
 * setting Hibernate, Spring Data, and the DataSource
 * performing an @EntityScan
 * turning on SQL logging
 */

@DataJpaTest
@Slf4j
class ApplicationRepositoryTest {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private TestEntityManager entityManager;


    @BeforeEach
    public void populateTestData() {

    }

    @Test
    public void WhenSaveNull_ThenThrows() {
        //actually it is wrapped by a Spring exception.
        Exception expException =
                assertThrows(InvalidDataAccessApiUsageException.class, () -> {
                            applicationRepository.save(null);
                        }
                );
    }

    @Test
    public void whenFindEnhancementsByApplicationById_ThenItReturnListEnhancements() {
        Application application = new Application();
        Enhancement enhancement = new Enhancement();

        application.setName("new-application-1");
        application.setOwner("owner-001");

        enhancement.setTitle("enhancement-1");
        enhancement.setDescription("enhancement-1-description");
        enhancement.setPriority(Priority.HIGH);
        enhancement.setDuplicate(false);

        application.addEnhancement(enhancement);

        Application persisted = entityManager.persist(application);
        entityManager.flush();
        assertNotNull(persisted);

        List<Enhancement> enhancementFound = applicationRepository.getEnhancementsWithApps(persisted.getId());
        assertThat(enhancementFound, hasSize(1));
    }

    @Test
    public void whenGivenTwoDifferentApplication_ThenItReturnCountEqualTwo() {
        log.info("++++++whenGivenTwoDifferentApplication_ThenItReturnCountEqualTwo()");
        Iterable<Application> applications = applicationRepository.findAll();



        String applicationName1 = "new-application-1";
        String owner1 = "owner-001";

        Application application1 = new Application();
        application1.setName(applicationName1);
        application1.setOwner(owner1);

        String applicationName2 = "new-application-2";
        String owner2 = "owner-002";

        entityManager.persistAndFlush(application1);

        assertTrue(applicationRepository.applicationExists(applicationName1, owner1));
        assertFalse(applicationRepository.applicationExists(applicationName2, owner2));
    }

    @Test
    public void testCreateApplication() {
        //given
        String applicationName1 = "new-application-1";
        String owner1 = "owner-001";
        Application application = new Application();
        application.setName(applicationName1);
        application.setOwner(owner1);

        Application persisted = applicationRepository.save(application);
        assertNotNull(persisted);

        assertThat(persisted, hasProperty("id", greaterThan(0)));
        assertThat(persisted, hasProperty("name", equalTo(applicationName1)));
        assertThat(persisted, hasProperty("owner", equalTo(owner1)));
    }


}