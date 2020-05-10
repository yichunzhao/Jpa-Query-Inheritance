package com.ynz.jpa.repositories;

import com.ynz.jpa.entities.Application;
import com.ynz.jpa.entities.Enhancement;
import com.ynz.jpa.entities.Priority;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(SpringExtension.class)
@DataJpaTest
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

}